package edu.msg.bookland.server.service;

import edu.msg.bookland.common.model.UserDTO;

public class ValidationService {

	public static StringBuilder checkUser(UserDTO user) {

		StringBuilder validationMessage = new StringBuilder();
		if (!user.getName().matches("\\A[A-Z]{1}[a-z]{3-15}\\z")) {
			validationMessage.append("User's name is not appropriate\n");
		} else if (!user.getPassword().matches(
				"^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[`~!@#$%^&*()\\-_=+\\[{\\]}\\|;:'\",<.>/?]+)(?=\\S+$).{8,}$|null")) {
			validationMessage.append("User's password is not appropriate\n");
		} else if (user.getLoyaltyIndex() < 0 || user.getLoyaltyIndex() > 10) {
			validationMessage.append("User's index must be greater than 0 and less than 10\n");
		}
		return validationMessage;
	}

	public static StringBuilder checkNameAndPassword(String name, String password) {
		StringBuilder validationMessage = new StringBuilder();
		if (!name.matches("\\A[A-Z]{1}[a-z]{3-15}\\z")) {
			validationMessage.append("User's name is not appropriate\n");
		}
		if (!password.matches(
				"^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[`~!@#$%^&*()\\-_=+\\[{\\]}\\|;:'\",<.>/?]+)(?=\\S+$).{8,}$")) {
			validationMessage.append("User's password is not appropriate\n");
		}
		return validationMessage;
	}

}
