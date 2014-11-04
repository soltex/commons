/**
 * 
 */
package com.vanstone.fs.local.impl;

import java.util.Date;

import com.vanstone.fs.local.DirectoryCreator;


/**
 * 日期目录 /2012/12/12/
 * @author shipeng
 */
public class DefaultDirectoryCreatorImpl implements DirectoryCreator{
	
	/* (non-Javadoc)
	 * @see cn.com.comon.comoncare.framework.fs.DirectoryCreator#directory()
	 */
	@Override
	public String build() {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("/yyyy/MM/dd/");
		return format.format(new Date());
	}
	
}
