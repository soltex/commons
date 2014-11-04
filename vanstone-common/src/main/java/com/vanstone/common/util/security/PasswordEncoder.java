package com.vanstone.common.util.security;

public interface PasswordEncoder {
	public String encodePassword(String rawPass, byte[] salt);

	public boolean isPasswordValid(String encPass, String rawPass, byte[] salt);
}
