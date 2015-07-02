/**
 * 
 */
package com.vanstone.fs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.vanstone.common.MyAssert;
import com.vanstone.fs.local.DirectoryCreator;
import com.vanstone.fs.local.impl.DefaultDirectoryCreatorImpl;

/**
 * @author shipeng
 */
public class FSManager {
	
	private static class FsManagerInstance {
		private static final FSManager instance = new FSManager();
	}

	/**
	 * 默认DirectoryCreator
	 */
	private DirectoryCreator directoryCreator = new DefaultDirectoryCreatorImpl();

	protected FSManager() {
	}
	
	/**
	 * 获取FileManager实例
	 * @return
	 */
	public static FSManager getInstance() {
		return FsManagerInstance.instance;
	}
	
	/**
	 * 根据filename新建FSFile
	 * @param fileid
	 * @param directoryType
	 * @return
	 */
	public FSFile newFSFile(String filename,FSType fsType) {
		MyAssert.hasText(filename);
		MyAssert.notNull(fsType);
		
		FSFile fsFile = null;
		String fileid = null;
		//创建普通文件类型
		if (fsType.equals(FSType.CommonType)) {
			String dir = this.getDirectoryCreator().build();
			if (!new File(Setting.getInstance().getStore() + dir).exists()) {
				new File(Setting.getInstance().getStore() + dir).mkdirs();
			}
			fileid = dir + filename;
			fsFile = new FSFile(fileid, FSType.CommonType);
		}
		//创建临时文件类型
		if (fsType.equals(FSType.Temporary)) {
			fileid = !filename.startsWith("/") ? "/" + filename : filename;
			fsFile = new FSFile(fileid, FSType.Temporary);
		}
		//创建常量文件类型
		if (fsType.equals(FSType.Constants)) {
			fileid = !filename.startsWith("/") ? "/" + filename : filename;
			fsFile = new FSFile(fileid, FSType.Constants);
		}
		return fsFile;
	}
	
	/**
	 * 新建FSFile
	 * @param filenameWithoutExt
	 * @param extname
	 * @param fsType
	 * @return
	 */
	public FSFile newFSFile(String filenameWithoutExt,String extname,FSType fsType) {
		MyAssert.hasText(filenameWithoutExt);
		MyAssert.hasText(extname);
		return newFSFile(filenameWithoutExt + "." + extname, fsType);
	}
	
	/**
	 * 新建并且创建FSFile
	 * @param fsType
	 * @return
	 */
	public FSFile newAndCreateFSFile(String filename, FSType fsType) {
		FSFile fsFile = newFSFile(filename, fsType);
		String parentPath = fsFile.getPhycicalpath();
		if (!new File(parentPath).exists()) {
			//创建子目录
			new File(parentPath).mkdirs();
		}
		//创建文件
		try {
            fsFile.getFile().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
		return fsFile;
	}
	
	/**
	 * 新建并创建FSFile
	 * @param filenameWithoutExt
	 * @param extname
	 * @param fsType
	 * @return
	 */
	public FSFile newAndCreateFSFile(String filenameWithoutExt,String extname, FSType fsType) {
		MyAssert.hasText(filenameWithoutExt);
		MyAssert.hasText(extname);
		return newAndCreateFSFile(filenameWithoutExt + "." + extname, fsType);
	}
	
	public FSFile copy(File file, FSType fsType) {
		MyAssert.notNull(file);
		FSFile fsFile = newAndCreateFSFile(UUID.randomUUID().toString(), FilenameUtils.getExtension(file.getName()), fsType);
		try {
			IOUtils.copy(new FileInputStream(file), fsFile.getOutputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fsFile;
	}
	
	/**
	 * 删除FSFile
	 * @param fsFile
	 */
	public void deleteFile(FSFile fsFile) {
		MyAssert.notNull(fsFile);
		if (fsFile.exists()) {
			fsFile.getFile().delete();
		}
	}
	
	/**
	 * 删除文件FSFile
	 * @param fileid
	 * @param fsType
	 */
	public void deleteFSFile(String fileid, FSType fsType) {
		MyAssert.hasText(fileid);
		MyAssert.notNull(fsType);
		FSFile fsFile = new FSFile(fileid, fsType);
		this.deleteFile(fsFile);
	}
	
	/**
	 * 获取临时目录中的全部文件
	 * @return
	 */
	public Collection<FSFile> getTmpFiles() {
		String[] fileIds = new File(Setting.getInstance().getTmpStore()).list();
		if (fileIds != null && fileIds.length > 0) {
			Collection<FSFile> files = new ArrayList<FSFile>();
			for (String fileId : fileIds) {
				FSFile fsFile = this.newFSFile(fileId, FSType.Temporary);
				files.add(fsFile);
			}
			return files;
		}
		return null;
	}
	
	/**
	 * 获取常量文件目录全部文件
	 * @return
	 */
	public Collection<FSFile> getConstantFiles() {
		String[] fileIds = new File(Setting.getInstance().getConstantStore()).list();
		if (fileIds != null && fileIds.length > 0) {
			Collection<FSFile> files = new ArrayList<FSFile>();
			for (String fileId : fileIds) {
				FSFile fsFile = this.newFSFile(fileId, FSType.Constants);
				files.add(fsFile);
			}
			return files;
		}
		return null;
	}
	
	/**
	 * 返回清理数量
	 * @return
	 */
	public int clearTmpFSFiles() {
		int count = 0;
		Collection<FSFile> fsFiles = this.getTmpFiles();
		if (fsFiles == null || fsFiles.size() <=0) {
			return 0;
		}
		for (FSFile fsFile : fsFiles) {
			try {
				this.deleteFile(fsFile);
				count ++;
            } catch (Exception e) {
            	e.printStackTrace();
            }
		}
		return count;
	}
	
	/**
	 * 下载文件
	 * @param fsFile
	 * @param servletResponse
	 */
	public void downloadFSFile(FSFile fsFile,HttpServletResponse servletResponse,String downloadDisplayName) {
		MyAssert.notNull(fsFile);
		MyAssert.notNull(servletResponse);
		MyAssert.hasText(downloadDisplayName);
		
		servletResponse.setContentType(ContentType.parseContentType(fsFile.getExtensionName()).getMimeType());  
		servletResponse.setHeader("Content-disposition", "attachment; filename=" + downloadDisplayName);
		servletResponse.setHeader("Content-Length", String.valueOf(fsFile.getFile().length()));
		
		BufferedInputStream bis;
		try {
			bis = new BufferedInputStream(fsFile.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(servletResponse.getOutputStream());  
			byte[] buff = new byte[2048];  
			int bytesRead;  
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
			    bos.write(buff, 0, bytesRead);  
			}  
			bis.close();  
			bos.close();  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public FSFile getFsFile(String fileid,FSType fsType) {
		return new FSFile(fileid, fsType);
	}
	
	public void setDirectoryCreator(DirectoryCreator directoryCreator) {
		if (directoryCreator == null) {
			return;
		}
		this.directoryCreator = directoryCreator;
	}

	public DirectoryCreator getDirectoryCreator() {
		return directoryCreator;
	}
}
