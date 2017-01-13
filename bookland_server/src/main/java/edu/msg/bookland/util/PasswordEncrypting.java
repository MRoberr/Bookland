package edu.msg.bookland.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import edu.msg.bookland.service.ServiceException;

public class PasswordEncrypting {
	private static final Logger LOGGER = Logger.getLogger(PasswordEncrypting.class);
	
	private PasswordEncrypting(){
		
	}

	public static String encrypt(String password, String salt) throws ServiceException{
		try {
			byte[] initialBytes = (password + salt).getBytes(PropertyProvider.getProperty("encrypt.encoding"));
			MessageDigest algorithm = MessageDigest.getInstance(PropertyProvider.getProperty("encrypt.algorithm"));
			algorithm.reset();
			algorithm.update(initialBytes);
			byte[] hashBytes = algorithm.digest();
			return new String(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("No Such Algorithm Exception", e);
			throw new ServiceException("No Such Algorithm Exception", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Unsuported Encoding", e);
			throw new ServiceException("Unsuported Encoding", e);
		}
	}
}
