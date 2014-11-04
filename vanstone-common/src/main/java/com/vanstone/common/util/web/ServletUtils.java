/**
 * 
 */
package com.vanstone.common.util.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.vanstone.common.MyAssert;

/**
 * @author shipeng
 * 
 */
public class ServletUtils {

	/**
	 * 获取Authortization信息inheader
	 * @param request
	 * @return
	 */
	public static String getServletHeaderAuthorization(HttpServletRequest request) {
		if (request == null) {
			throw new IllegalArgumentException();
		}
		String str = request.getHeader("Authorization");
		if (StringUtils.isEmpty(str) || StringUtils.isBlank(str)) {
			return null;
		}
		str = str.substring("Basic ".length());
		
		//TODO return Base64.decode(str);
		return null;
	}

	/**
	 * 获取远程ip地址
	 * @param request
	 * @return
	 */
	public static String getRemotetIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
	
	/**
	 * 获取全部Cookies
	 * @param servletRequest
	 * @return
	 */
	public static Map<String, String> getAllCookies(HttpServletRequest servletRequest) {
		MyAssert.notNull(servletRequest);
		Cookie[] cookies = servletRequest.getCookies();
		if (cookies == null || cookies.length <=0) {
			return null;
		}
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		for (Cookie cookie : cookies) {
			dataMap.put(cookie.getName(), cookie.getValue());
		}
		return dataMap;
	}
	
	/**
	 * 获取Cookie Value
	 * @param servletRequest
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest servletRequest, String cookieName) {
		MyAssert.notNull(servletRequest);
		Cookie[] cookies = servletRequest.getCookies();
		if (cookies == null || cookies.length <=0) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				return cookie.getValue();
			}
		}
		return null;
	}
	
}
