package com.vanstone.common.util.security;

import org.apache.commons.lang.StringUtils;

public abstract class MessageDigestPasswordEncoder implements PasswordEncoder {
	public static final int HASH_INTERATIONS = 1024;

	public String encodePassword(String rawPass, byte[] salt) {
		if (StringUtils.isBlank(rawPass)) {
			return null;
		}
		//byte[] hashPassword = digest(Cryptos.utf8encode(rawPass), salt);
		//return Encodes.encodeHex(hashPassword);
		return null;
	}

	public boolean isPasswordValid(String encPass, String rawPass, byte[] salt) {
		if (StringUtils.isBlank(encPass) && StringUtils.isBlank(rawPass)) {
			return true;
		}
		return StringUtils.equals(encPass, encodePassword(rawPass, salt));
	}

	protected abstract byte[] digest(byte[] input, byte[] salt);
}
