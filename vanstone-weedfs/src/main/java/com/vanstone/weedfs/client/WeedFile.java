/**
 * 
 */
package com.vanstone.weedfs.client;


/**
 * WeedFS File
 * @author shipeng
 */
public class WeedFile {
	
	private String fileid;
	private String extName;
	
	public WeedFile() {
		
	}
	
	public WeedFile(String fileid) {
		if (fileid == null || "".equals(fileid)) {
			throw new IllegalArgumentException();
		}
		this.fileid = fileid;
	}
	
	public WeedFile(String fileid,String extName) {
		this(fileid);
		if (extName != null && !extName.equals("")) {
			this.extName = extName;
		}
	}
	
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public String getExtName() {
		return extName;
	}

	/**
	 * 获取文件id
	 * @return
	 */
	public String getFileid() {
		return fileid;
	}
	
	/**
	 * 获取无扩展名的文件id
	 * @return
	 */
	public String getFileidWithoutExt() {
		return fileid.split(",")[0];
	}
	
}
