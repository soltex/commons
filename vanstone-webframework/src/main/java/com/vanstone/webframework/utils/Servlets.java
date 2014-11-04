package com.vanstone.webframework.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author shipeng
 */
public class Servlets {
	
	private static final Log LOG = LogFactory.getLog(Servlets.class);

	/**
	 * 获得真实IP地址。在使用了反向代理时，直接用HttpServletRequest.getRemoteAddr()无法获取客户真实的IP地址。
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.length() > 0) {
			int index = ip.indexOf(",");
			if (index > 0) {
				ip = ip.substring(0, index);
			}
		}
		return ip;
	}

	public static Map<String, String> getParamMap(ServletRequest request,
			String prefix) {
		return getParamMap(request, prefix, false);
	}

	@SuppressWarnings("rawtypes")
	public static Map<String, String> getParamMap(ServletRequest request,
			String prefix, boolean keyWithPrefix) {
		Map<String, String> map = new TreeMap<String, String>();
		if (prefix == null) {
			prefix = "";
		}
		int len = prefix.length();
		String name;
		Enumeration en = request.getParameterNames();
		while (en != null && en.hasMoreElements()) {
			name = (String) en.nextElement();
			if ("".equals(prefix) || name.startsWith(prefix)) {
				if (keyWithPrefix) {
					map.put(name, request.getParameter(name));
				} else {
					map.put(name.substring(len), request.getParameter(name));
				}
			}
		}
		return map;
	}

	public static Map<String, Object> getParametersStartingWith(
			ServletRequest request, String prefix, boolean keyWithPrefix) {
		Validate.notNull(request, "Request must not be null");
		@SuppressWarnings("rawtypes")
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		int len = prefix.length();
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String name = keyWithPrefix ? paramName : paramName
						.substring(len);
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(name, values);
				} else {
					params.put(name, values[0]);
				}
			}
		}
		return params;
	}

	/**
	 * 设置禁止客户端缓存的Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

	public static void writeHtml(HttpServletResponse response, String s) {
		response.setContentType("text/html;charset=utf-8");
		setNoCacheHeader(response);
		try {
			response.getWriter().write(s);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
