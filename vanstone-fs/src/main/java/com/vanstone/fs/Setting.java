/**
 * 
 */
package com.vanstone.fs;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author peng.shi@argylesoftware.co.uk
 */
public class Setting {
	
	private String store;
	private String tmpStore;
	private String constantStore;
	private String uriPrefix;
	private String fileAction;
	private Set<String> extensions = new HashSet<String>();
	private int maxFilesize;
	private boolean isLimitFilesize;
	
	/**
	 * File System Configuration
	 */
	private static final String FS_CONF = "/fs.properties";
	
	private static final Log _LOG = LogFactory.getLog(Setting.class);
	
	private static Object lock = new Object();
	
	private static Setting setting;
	
	private Setting() {
		_init();
	}
	
	/**
	 * @return
	 */
	public static Setting getInstance() {
		if (setting == null) {
			synchronized (lock) {
				setting = new Setting();
			}
		}
		return setting;
	}
	
	/**
	 * @param str
	 * @return
	 */
	private String _cleanPath(String str) {
		if (str.endsWith("/")){
			str = str.substring(0,str.length()-1);
		}
		return str;
	}
	
	private void _init() {
		Properties properties = new Properties();
		try {
			properties.load(Setting.class.getResourceAsStream(FS_CONF));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("fs.properties not found.");
		}
		this.store = properties.getProperty("fs.store");
		this.tmpStore = properties.getProperty("fs.tmpstore");
		this.constantStore = properties.getProperty("fs.constantstore");
		
		this.uriPrefix = properties.getProperty("fs.uriprefix");
		this.fileAction = properties.getProperty("fs.fileaction");
		
		//File extension initial
		String strFileType = properties.getProperty("fs.extensions");
		if (!StringUtils.isEmpty(strFileType) && !StringUtils.isBlank(strFileType)) {
			String[] temps = strFileType.split(",");
			for (String tmp : temps) {
				this.extensions.add(tmp.toLowerCase());
			}
		}
		//file size initial
		String tmpfsSize = properties.getProperty("fs.maxsize");
		if (StringUtils.isEmpty(tmpfsSize) || StringUtils.isBlank(tmpfsSize)) {
			this.isLimitFilesize = false;
			this.maxFilesize = -1;
		}else{
			int a = 0;
			try {
				a = Integer.parseInt(tmpfsSize);
				if (a <=0) {
					this.isLimitFilesize = false;
					this.maxFilesize = -1;
				}else{
					this.isLimitFilesize = true;
					this.maxFilesize = a;
				}
			} catch (Exception e) {
				this.isLimitFilesize = false;
				this.maxFilesize = -1;
			}
		}
		if (this.store == null || "".equals(this.store)) {
			throw new ExceptionInInitializerError();
		}
		if (this.tmpStore == null || "".equals(this.tmpStore)) {
			throw new ExceptionInInitializerError();
		}
		if (this.constantStore == null || "".equals(this.constantStore)) {
			throw new ExceptionInInitializerError();
		}
		if (this.uriPrefix == null || "".equals(this.uriPrefix)) {
			throw new ExceptionInInitializerError();
		}
		if (this.fileAction == null || "".equals(this.fileAction)) {
			throw new ExceptionInInitializerError();
		}
		this.store = _cleanPath(this.store);
		this.tmpStore = _cleanPath(this.tmpStore);
		this.constantStore = _cleanPath(this.constantStore);
		if (!new File(this.store).exists()) {
			new File(this.store).mkdirs();
			_LOG.info("Create Store Directory.");
		}
		if (!new File(this.tmpStore).exists()) {
			new File(this.tmpStore).mkdirs();
			_LOG.info("Create Tmp Store Directory");
		}
		if (!new File(this.constantStore).exists()) {
			new File(this.constantStore).mkdirs();
			_LOG.info("Create Constants Store Directory");
		}
	}
	
	/**
	 * 获取标准的Store
	 * @return
	 */
	public String getStore() {
		return store;
	}
	
	/**
	 * 获取Tmp Store地址
	 * @return
	 */
	public String getTmpStore() {
		return tmpStore;
	}
	
	/**
	 * 获取ConstantStore物理地址
	 * @return
	 */
	public String getConstantStore() {
		return constantStore;
	}
	
	/**
	 * @return
	 */
	public String getUriPrefix() {
		return uriPrefix;
	}

	/**
	 * @return
	 */
	public String getFileAction() {
		return fileAction;
	}

	/**
	 * 判断支持文件上传最大大小
	 * @return
	 */
	public int getMaxFilesize() {
		return maxFilesize;
	}

	/**
	 * 是否限定文件大小
	 * @return
	 */
	public boolean isLimitFilesize() {
		return isLimitFilesize;
	}

	/**
	 * 获取支持的文件扩展名
	 * @return
	 */
	public Set<String> getExtensions() {
		return extensions;
	}
	
	/**
	 * 判断是否支持
	 * @param fileTypeName
	 * @return
	 */
	public boolean isAllowFileType(String fileTypeName) {
		if (getExtensions().size() <=0) {
			return true;
		}
		if (this.extensions.contains(fileTypeName.toLowerCase())) {
			return true;
		}else{
			return false;
		}
	}
}
