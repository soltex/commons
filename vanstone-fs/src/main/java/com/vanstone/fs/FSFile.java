/**
 * 
 */
package com.vanstone.fs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

import org.apache.commons.io.FilenameUtils;

import com.vanstone.common.MyAssert;

/**
 * 组合方式说明 
 * 例如 ： /data/vanstone-fsfile/common/12/12/12/name.jpg
 * fileid : /12/12/12/name.jpg
 * path : /12/12/12
 * extname : jpg
 * filename : name.jpg
 * basefilename : name 
 * physicalpath : /data/vanstone-fsfile/common/12/12/12/name.jpg
 * fileaction : http://localhost:8080/image/12/12/12/name.jpg
 */
public class FSFile implements Serializable {

	private static final long serialVersionUID = 3759431650975570887L;
	/**
	 * 文件id
	 */
	protected String fileid;
	/**
	 * 文件名称 
	 */
	protected String filename;
	/**
	 * 基本文件名称
	 */
	protected String baseFilename;
	/**
	 * 文件扩展名
	 */
	protected String extname;
	/**
	 * 文件类型
	 */
	protected FileType fileType;
	/**
	 * 文件路径
	 */
	protected String fileidpath;
	/**
	 * 文件系统类型
	 */
	protected FSType fsType;
	/**
	 * 物理文件路径
	 */
	protected String physicalFilepath;
	/**
	 * 物理路径
	 */
	protected String phycicalpath;
	
	protected FSFile(String fileid,FSType fsType) {
		MyAssert.hasText(fileid);
		MyAssert.notNull(fsType);
		
		this.fileid = fileid;
		this.fsType = fsType;
		this.filename = FilenameUtils.getName(this.fileid);
		this.baseFilename = FilenameUtils.getBaseName(this.fileid);
		this.extname = FilenameUtils.getExtension(this.fileid);
		this.fileidpath = FilenameUtils.getFullPath(fileid);
		this.fileType = FileType.getFileType(this.extname);
		
		if (this.fsType.equals(FSType.CommonType)) {
			this.physicalFilepath = Setting.getInstance().getStore() + this.fileid;
		}else if (this.fsType.equals(FSType.Temporary)){
			this.physicalFilepath = Setting.getInstance().getTmpStore() + this.fileid;
		}else if (this.fsType.equals(FSType.Constants)) {
			this.physicalFilepath = Setting.getInstance().getConstantStore() + this.fileid;
		}else{
			throw new IllegalArgumentException("Illegal FSType : " + this.fsType);
		}
		this.phycicalpath = FilenameUtils.getFullPath(this.physicalFilepath);
	}
	
	public String getFileid() {
		return fileid;
	}

	public String getFilename() {
		return filename;
	}
	
	public String getExtensionName() {
		return this.extname;
	}

	public FileType getFileType() {
		return fileType;
	}

	public File getFile() {
		return new File(this.getPhysicalFilepath());
	}
	
	public String getFullUrlPath() {
		return Setting.getInstance().getUriPrefix() + this.fileid;
	}
	
	public String getImageActionFullUrl() {
		return Setting.getInstance().getFileAction() + fileid;
	}
	
	public boolean exists() {
		return getFile().exists();
	}
	
	public String getPhysicalFilepath() {
		return this.physicalFilepath;
	}

	public String getPhycicalpath() {
		return phycicalpath;
	}

	public FileInputStream getInputStream() throws FileNotFoundException {
		return new FileInputStream(getFile());
	}
	
	public FileOutputStream getOutputStream() throws FileNotFoundException {
		return new FileOutputStream(getFile());
	}
	
	public FSType getFSType() {
		return fsType;
	}
	
	public String getFileAction() {
		return Setting.getInstance().getFileAction() + fileid;
	}

	public String getFileidpath() {
		return fileidpath;
	}
	
}
