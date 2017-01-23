package edu.msg.bookland.server.service;

import edu.msg.bookland.common.model.UserDTO;

/**
 * Utility service for user validation
 * 
 * @author Jozsef Solomon
 *
 */
public class ValidationService {

	
	public static StringBuilder checkUser(UserDTO user) {

		StringBuilder validationMessage = new StringBuilder();
		if (!user.getName().matches("\\A[A-Z]{1}[a-z]{3,15}\\z")) {
			validationMessage.append("User's name is not appropriate\n");
		} else if (!user.getPassword().matches(
				"^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[`~!@#$%^&*()\\-_=+\\[{\\]}\\|;:'\",<.>/?]+)(?=\\S+$).{8,}$|null")) {
			validationMessage.append("User's password is not appropriate\n");
		} else if (user.getLoyaltyIndex() < 0 || user.getLoyaltyIndex() > 10) {
			validationMessage.append("User's loyaltyindex must between 1 and 10");
		} else if(user.getEmail().matches("[\\w\\-.]+@\\w+(\\.{1})[a-z]+(\\.*)[a-z]+")) {
			validationMessage.append("User's email is not correct");
		}
		return validationMessage;
	}

	public static StringBuilder checkNameAndPassword(String name, String password) {
		StringBuilder validationMessage = new StringBuilder();
		if (name == null || name.isEmpty()) {
			validationMessage.append("User's name is empty\n");
		}
		if (password == null || password.isEmpty()) {
			validationMessage.append("User's password is empty\n");
		}
		return validationMessage;
	}

}
