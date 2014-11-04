/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: ResourceDetailsService.java 764 2009-12-27 19:13:59Z calvinxiu $
 */
package com.vanstone.common.util.security.springsecurity;

import java.util.LinkedHashMap;

/**
 * RequestMap生成接口,由用户自行实现从数据库或其它地方查询URL-授权关系定义.
 * 
 * @author calvin
 */
public interface ResourceDetailsService {

	/**
	 * 返回带顺序的URL-授权关系Map, key为受保护的URL, value为能访问该URL的授权名称列表,以','分隔.
	 */
	public LinkedHashMap<String, String> getRequestMap() throws Exception; //NOSONAR
}
