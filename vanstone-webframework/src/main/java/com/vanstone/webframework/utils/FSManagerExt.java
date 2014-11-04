/**
 * 
 */
package com.vanstone.webframework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.vanstone.business.MyAssert4Business;
import com.vanstone.fs.FSException;
import com.vanstone.fs.FSFile;
import com.vanstone.fs.FSManager;
import com.vanstone.fs.FSType;
import com.vanstone.fs.Setting;

/**
 * @author shipeng
 *
 */
public class FSManagerExt extends FSManager {
	
	private static class FSManagerExtInstance {
		private static FSManagerExt instance = new FSManagerExt();
	}
	
	/**
	 * 获取单例
	 * @return
	 */
	public static FSManagerExt getInstance() {
		return FSManagerExtInstance.instance;
	}
	
	private void _validateFS(MultipartFile multipartFile) throws FSException {
		if (Setting.getInstance().isLimitFilesize() && multipartFile.getSize() > Setting.getInstance().getMaxFilesize()) {
			throw new FSException(FSException.ILLEGAL_FILE_SIZE);
		}
		if (!Setting.getInstance().isAllowFileType(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))) {
			throw new FSException(FSException.ILLEGAL_FILE_TYPE);
		}
	}
	
	/**
	 * 上传文件并指定文件名称
	 * 
	 * @param multipartFile
	 * @param newFilename
	 * @return
	 */
	public FSFile uploadBySpring(MultipartFile multipartFile, String newFilename, FSType fsType)
			throws FSException {
		MyAssert4Business.notNull(multipartFile);
		MyAssert4Business.notNull(fsType);
		MyAssert4Business.hasText(newFilename);

		_validateFS(multipartFile);

		String directory = _buildDirectoryString(fsType);
		
		String fullDirectory = null;
		if(fsType.equals(FSType.CommonType)) {
			fullDirectory =  Setting.getInstance().getStore() + directory;
		}else if (fsType.equals(FSType.Temporary)) {
			fullDirectory = Setting.getInstance().getTmpStore() + directory;
		}else {
			fullDirectory = Setting.getInstance().getConstantStore();
		}
		
		if (!new File(fullDirectory).exists()) {
			new File(fullDirectory).mkdirs();
		}
		String uploadFilename = multipartFile.getOriginalFilename();
		String extensionName = FilenameUtils.getExtension(uploadFilename);
		newFilename = newFilename + "." + extensionName;
		String fileId = directory + newFilename;
		InputStream is;
		try {
			is = multipartFile.getInputStream();
			byte[] buffer = new byte[1024];
			int n = 0;
			File file = new File(fullDirectory + newFilename);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			while ((n = is.read(buffer, 0, buffer.length)) != -1) {
				fos.write(buffer, 0, n);
			}
			is.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		return this.getFsFile(fileId, fsType);
	}
	
	/**
	 * 上传文件
	 * 
	 * @param multipartFile
	 * @return
	 */
	public FSFile uploadBySpring(MultipartFile multipartFile, FSType fsType) throws FSException {
		MyAssert4Business.notNull(multipartFile);
		MyAssert4Business.notNull(fsType);

		_validateFS(multipartFile);

		String directory = _buildDirectoryString(fsType);
		String fullDirectory = null;
		if(fsType.equals(FSType.CommonType)) {
			fullDirectory =  Setting.getInstance().getStore() + directory;
		}else if (fsType.equals(FSType.Temporary)) {
			fullDirectory = Setting.getInstance().getTmpStore() + directory;
		}else {
			fullDirectory = Setting.getInstance().getConstantStore();
		}
		if (!new File(fullDirectory).exists()) {
			new File(fullDirectory).mkdirs();
		}
		String uploadFilename = multipartFile.getOriginalFilename();
		String extensionName = FilenameUtils.getExtension(uploadFilename);
		String newFilename = UUID.randomUUID().toString().replaceAll("-", "") + "." + extensionName;
		String fileId = directory + newFilename;
		InputStream is;
		try {
			is = multipartFile.getInputStream();
			byte[] buffer = new byte[1024];
			int n = 0;
			File file = new File(fullDirectory + newFilename);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			while ((n = is.read(buffer, 0, buffer.length)) != -1) {
				fos.write(buffer, 0, n);
			}
			is.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		return this.getFsFile(fileId, fsType);
	}

	/**
	 * @param directoryType
	 * @return
	 */
	private String _buildDirectoryString(FSType fsType) {
		String directory = null;
		if (fsType.equals(FSType.CommonType)) {
			directory = this.getDirectoryCreator().build();
		} else if (fsType.equals(FSType.Temporary)) {
			directory = "/";
		} else {
			directory = "/";
		}
		return directory;
	}
}
