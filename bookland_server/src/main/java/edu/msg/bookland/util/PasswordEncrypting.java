package edu.msg.bookland.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import edu.msg.bookland.repository.RepositoryException;

/**
 * Singleton class to Encrypt password
 * 
 * @author Terez Sipos
 */
public class PasswordEncrypting {
	private static final Logger LOGGER = Logger.getLogger(PasswordEncrypting.class);

	private PasswordEncrypting() {
	}

	/**
	 * This method use property provider to get algorithm and encoding to
	 * encrypt password with salt
	 * 
	 * @param password
	 * @param salt
	 * @return hashed password
	 * @throws ServiceException
	 */
	public static String encrypt(String password, String salt) throws RepositoryException {
		try {
			byte[] initialBytes = (password + salt).getBytes(PropertyProvider.getProperty("encrypt.encoding"));
			MessageDigest algorithm = MessageDigest.getInstance(PropertyProvider.getProperty("encrypt.algorithm"));
			algorithm.reset();
			algorithm.update(initialBytes);
			byte[] hashBytes = algorithm.digest();
			return new String(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("No Such Algorithm Exception", e);
			throw new RepositoryException("No Such Algorithm Exception", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Unsuported Encoding", e);
			throw new RepositoryException("Unsuported Encoding", e);
		}
	}
}
