package com.restjwtauth.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Tools {

	/**
	 * Senha criptografada usando BCryptPasswordEncoder
	 * 
	 * @param password
	 * @return
	 */
	public static String encryptPassword(String password) {
		return DigestUtils.sha1Hex( password );
	}
}
