/**
 * 
 */
package com.vanstone.fs;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author shipeng
 */
public enum FileType {
	/**
	 * 图片文件
	 */
	Image(new String[] { "jpg", "gif", "png", "ief","jpeg", "jpe","tiff","tif","djvu","djv","wbmp","ras","pnm","pbm","pgm","ppm","rgb","xbm","xpm","xwd" }),
	/**
	 * Word文档文件
	 */
	Word(new String[] { "doc", "docx" }),
	/**
	 * 其他文件
	 */
	Other(null);

	private String[] extensionNames;

	private String mimeType;

	private FileType(String[] extensionNames) {
		this.extensionNames = extensionNames;
	}

	public String[] getExtensionNames() {
		return extensionNames;
	}
	
	public static FileType getFileType(String extensionName) {
		if (extensionName == null || "".equals(extensionName)) {
			return FileType.Other;
		}
		for (FileType tmpFileType : FileType.values()) {
			String[] tmps = tmpFileType.getExtensionNames();
			if (tmps == null) {
				break;
			}
			for (String name : tmps) {
				if (name.equalsIgnoreCase(extensionName)) {
					return tmpFileType;
				}
			}
		}
		return FileType.Other;
	}
	
	/**
	 * 是否为图片
	 * @param extName
	 * @return
	 */
	public static boolean isImage(String extName) {
		if (extName == null || extName.equals("")) {
			return false;
		}
		if (ArrayUtils.contains(Image.getExtensionNames(), extName.toLowerCase())) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(FileType.getFileType(null));
	}

	public String getMimeType() {
		return mimeType;
	}

}
