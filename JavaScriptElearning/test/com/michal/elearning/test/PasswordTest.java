package com.michal.elearning.test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Assert;
import org.junit.Test;

import com.michal.elearning.utils.PasswordUtils;

public class PasswordTest {

	private static String orginalPass = "michal";
	private static String hashedPassword = "1000:1c13d46d2883ad6e2b80c3403f93fe26:6afaa670993a670f0c61ad2db998e739845f314525fbc7fdc5b96751d6268f99e858f4f6222d6d74cf093921634e35ab59334142e9564568dcc5fd811899f76b";
	private static String wrongSaltPassword = "1000:1c13d46d29945d6e2b80c3403f93fe26:6afaa670993a670f0c61ad2db998e739845f314525fbc7fdc5b96751d6268f99e858f4f6222d6d74cf093921634e35ab59334142e9564568dcc5fd811899f76b";
	private static String wrongPassword = "1000:1c13d46d2883ad6e2b80c3403f93fe26:6afaa670993a670f0c61ad2db998e739845f314525fbc7fdc5b96751d6268f99e858f4f6222d6d74c1193921634e35ab59334142e9564568dcc55d811899f76b";
	
	@Test
	public void toHash() throws NoSuchAlgorithmException, InvalidKeySpecException{
		String hashPassword = PasswordUtils.generateStorngPasswordHash(orginalPass);
		Assert.assertNotNull(hashPassword);
	}
	@Test
	public void validatePass() throws NoSuchAlgorithmException, InvalidKeySpecException{
		boolean valid = PasswordUtils.validatePassword(orginalPass, hashedPassword);
		Assert.assertTrue(valid);
	}
	@Test
	public void validatePassWrongSalt() throws NoSuchAlgorithmException, InvalidKeySpecException{
		boolean valid = PasswordUtils.validatePassword(orginalPass, wrongSaltPassword);
		Assert.assertFalse(valid);
	}
	@Test
	public void validatePassWrongPass() throws NoSuchAlgorithmException, InvalidKeySpecException{
		boolean valid = PasswordUtils.validatePassword(orginalPass, wrongPassword);
		Assert.assertFalse(valid);
	}
	@Test
	public void auth() throws NoSuchAlgorithmException, InvalidKeySpecException{
		String hashPassword = PasswordUtils.generateStorngPasswordHash(orginalPass);
		boolean valid = PasswordUtils.validatePassword(orginalPass, hashPassword);
		Assert.assertTrue(valid);
	}
	
}
