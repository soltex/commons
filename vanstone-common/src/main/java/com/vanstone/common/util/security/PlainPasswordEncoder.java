package com.vanstone.common.util.security;

import org.apache.commons.lang.StringUtils;

public class PlainPasswordEncoder {
	public String encodePassword(String rawPass, String salt) {
		if (StringUtils.isNotBlank(rawPass)) {
			return rawPass;
		} else {
			return null;
		}
	}

	public boolean isPasswordValid(String encPass, String rawPass, String salt) {
		if (StringUtils.isBlank(encPass) && StringUtils.isBlank(rawPass)) {
			return true;
		}
		return StringUtils.equals(encPass, rawPass);
	}
}
