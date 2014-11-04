package com.vanstone.common.util.security;

public class SHA512PasswordEncoder extends MessageDigestPasswordEncoder
		implements PasswordEncoder {
	@Override
	protected byte[] digest(byte[] input, byte[] salt) {
		return Digests.sha512(input, salt, HASH_INTERATIONS);
	}
}