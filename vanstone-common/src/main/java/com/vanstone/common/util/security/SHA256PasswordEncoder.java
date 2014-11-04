package com.vanstone.common.util.security;

public class SHA256PasswordEncoder extends MessageDigestPasswordEncoder
		implements PasswordEncoder {
	@Override
	protected byte[] digest(byte[] input, byte[] salt) {
		return Digests.sha256(input, salt, HASH_INTERATIONS);
	}
}