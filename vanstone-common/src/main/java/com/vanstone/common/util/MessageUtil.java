package com.vanstone.common.util;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

public class MessageUtil {

	/**
	 * 参数使用{0}开始设定
	 * @param pattern
	 * @param args
	 * @return
	 */
	public static String pattern2String(String pattern, Object[] args) {
		if (StringUtils.isEmpty(pattern)) {
			throw new java.lang.IllegalArgumentException();
		}
		return MessageFormat.format(pattern, args);
	}

	public static void main(String[] args) {
		String[] params = new String[]{"皮皮","123456%……（","192.168.1.103"};
		String pattern = "系统登陆用户--用户名:{0}--密码:{1}--IP地址:{2}";
		System.out.println(pattern2String(pattern, params));
	}
}
