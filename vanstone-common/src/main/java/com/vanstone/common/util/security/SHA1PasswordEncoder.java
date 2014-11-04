package com.vanstone.common.util.security;

public class SHA1PasswordEncoder extends MessageDigestPasswordEncoder {
	@Override
	protected byte[] digest(byte[] input, byte[] salt) {
		return Digests.sha1(input, salt, HASH_INTERATIONS);
	}
}
