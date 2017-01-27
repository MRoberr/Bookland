package edu.msg.bookland.desktop.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import edu.msg.bookland.common.model.BorrowingDTO;
import edu.msg.bookland.common.model.PublicationDTO;
import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.desktop.RequestException;
import edu.msg.bookland.desktop.util.textLangProvider;
import edu.msg.bookland.desktop.view.CustomServiceView;
import edu.msg.bookland.desktop.view.DataAdministrationView;
import edu.msg.bookland.desktop.view.MainView;

/**
 * This is the console client main class for library project Bookland.
 * 
 * @author Simo Zoltan
 * @author Szocs Csilla
 *
 */
public class MainController {
	private LoginController lc = new LoginController();
	private CustomServiceController csc = new CustomServiceController();
	private DataAdministrationController dac = new DataAdministrationController();
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Temporary instance variables for communicating between functions
	 */
	private int tempInt = 0;
	private String tempStr = "";
	private List<PublicationDTO> tempPublications;
	private List<UserDTO> tempUsers;
	private List<BorrowingDTO> tempBorrowings;
	private PublicationDTO tempPublication;
	private UserDTO tempUser;
	private BorrowingDTO tempBorrowing;
	private UserType tempUserType;

	/**
	 * Constant messages
	 */
	private String exitBackString = textLangProvider.INSTANCE.getProperty("exitBackStr");
	private String exitString = textLangProvider.INSTANCE.getProperty("exitStr");

	/**
	 * Start console application with constructor
	 */
	public MainController() {
		if (chooseLanguage() == 0) {
			while (true) {
				handleLogin();
			}
		}
	}

	private String getLine() {
		String str = "";
		while (str.isEmpty()) {
			str = scanner.nextLine();
		}
		return str;
	}

	private int getIntLine() {
		int cmd = 0;
		while (cmd == 0) {
			try {
				cmd = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(exitBackString);
				scanner.next();
			}
		}
		return cmd;
	}

	/**
	 * Controller for internationalization/language choose
	 */
	private int chooseLanguage() {
		MainView.menuForLanguage();
		while (true) {
			return handleLanguageCommand();
		}
	}

	/**
	 * Controller for login
	 */
	private void handleLogin() {
		System.out.println(textLangProvider.INSTANCE.getProperty("loginEnterNameAndPass"));
		try {
			tempStr = getLine();
			if (tempStr.equals("-1")) {
				System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
				System.exit(0);
			}
			UserType login = lc.userLogin(tempStr, getLine());
			if (login.equals(UserType.READER)) {
				System.out.println("=" + textLangProvider.INSTANCE.getProperty("operationLoggedInAsReader"));
				while (true) {
					MainView.menuForUser();
					if (handleUserCommand() == -2) {
						break;
					}
				}
			} else if (login.equals(UserType.ADMIN)) {
				System.out.println("=" + textLangProvider.INSTANCE.getProperty("operationLoggedInAsAdmin"));
				while (true) {
					MainView.menuInitForAdmin();
					if (handleAdminCommand(UserType.ADMIN) == -2) {
						break;
					}
				}
			} else if (login.equals(UserType.SUPER)) {
				System.out.println("=" + textLangProvider.INSTANCE.getProperty("operationLoggedInAsSuperAdmin"));
				while (true) {
					MainView.menuInitForAdmin();
					if (handleAdminCommand(UserType.SUPER) == -2) {
						break;
					}
				}
			}
		} catch (RequestException e) {
			System.out.println(textLangProvider.INSTANCE.getProperty("errorReason") + " " + e.getMessage() + "\n");
		}
	}

	/**
	 * Controller for language change
	 */
	private int handleLanguageCommand() {
		int cmd = getIntLine();
		switch (cmd) {
		case -1:
			System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
			System.exit(0);
			return -1;
		case 1:
			textLangProvider.INSTANCE.setLocale(new Locale("En", "en"));
			System.out.println(textLangProvider.INSTANCE.getProperty("languageChoosen"));
			break;
		case 2:
			textLangProvider.INSTANCE.setLocale(new Locale("Hu", "hu"));
			System.out.println(textLangProvider.INSTANCE.getProperty("languageChoosen"));
			break;
		case 3:
			textLangProvider.INSTANCE.setLocale(new Locale("Ro", "ro"));
			System.out.println(textLangProvider.INSTANCE.getProperty("languageChoosen"));
			break;
		default:
			System.out.println(exitString);
		}
		exitString = textLangProvider.INSTANCE.getProperty("exitStr");
		exitBackString = textLangProvider.INSTANCE.getProperty("exitBackStr");
		return 0;
	}

	/**
	 * Controller for users logged in as READER
	 */
	private int handleUserCommand() {
		int cmd = getIntLine();
		switch (cmd) {
		case -1:
			System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
			System.exit(0);
			break;
		case -2:
			return -2;
		case 1:
			searchPublications();
			break;
		case 2:
			chooseLanguage();
			break;
		default:
			System.out.println(exitBackString);
			break;
		}
		return 0;
	}

	/**
	 * Controller for users logged in as ADMIN
	 */
	private int handleAdminCommand(UserType uT) {
		int cmd = getIntLine();
		switch (cmd) {
		case -1:
			System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
			System.exit(0);
			break;
		case -2:
			return -2;
		case 1:
			while (true) {
				System.out.println("=" + textLangProvider.INSTANCE.getProperty("operationCustomService") + "=");
				CustomServiceView.menuForAdminCustomS();
				if (handleAdminCustomService() == -2) {
					break;
				}
			}
			break;
		case 2:
			while (true) {
				System.out.println("=" + textLangProvider.INSTANCE.getProperty("operationDataAdministration") + "=");
				DataAdministrationView.menuForAdminDataA();
				if (handleAdminDataAdministration(uT) == -2) {
					break;
				}
			}
			break;
		case 3:
			chooseLanguage();
			break;
		default:
			System.out.println(exitBackString);
			break;
		}
		return 0;
	}

	/**
	 * Controller for Custom Service
	 * 
	 * @return number 0 by default and -2 for implementing GO BACK/UP
	 */
	private int handleAdminCustomService() {
		int cmd = getIntLine();
		switch (cmd) {
		case -2:
			return -2;
		case -1:
			System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
			System.exit(0);
			break;
		case 1:
			System.out.println(textLangProvider.INSTANCE.getProperty("operationBorrowPublication") + "=");
			try {
				searchUsers();
				tempUser = getUserFromResult();
				if (tempUser != null) {
					searchPublications();
					tempPublication = getPublicationFromResult();
					if (tempPublication != null) {
						BorrowingDTO b = new BorrowingDTO();
						b.setUserId(tempUser.getUUID());
						b.setPublication(tempPublication);
						b.setBorrowingDate(Date.valueOf(LocalDate.now()));
						b.setDeadline(Date.valueOf(LocalDate.now().plusDays(20)));
						try {
							csc.borrowPublication(b);
							System.out.println(textLangProvider.INSTANCE.getProperty("borrowOk"));
						} catch (RequestException e) {
							System.out.println(textLangProvider.INSTANCE.getProperty("borrowNotOk"));
							System.out.println(
									textLangProvider.INSTANCE.getProperty("errorReason") + " " + e.getMessage());
						}

					}
				}
			} finally {
				tempPublication = null;
				tempPublications = null;
				tempUser = null;
				tempUsers = null;
			}
			break;
		case 2:
			System.out.println(textLangProvider.INSTANCE.getProperty("operationReturnPublication") + "=");
			try {
				searchUsers();
				tempUser = getUserFromResult();
				if (tempUser != null) {
					searchBorrowedPublications();
					tempBorrowing = getBorrowedPublicationFromResult();
					if (tempBorrowing != null) {
						try {
							csc.returnPublication(tempBorrowing);
							System.out.println(textLangProvider.INSTANCE.getProperty("returnOk"));
						} catch (RequestException e) {
							System.out.println(textLangProvider.INSTANCE.getProperty("returnNotOk"));
							System.out.println(
									textLangProvider.INSTANCE.getProperty("errorReason") + " " + e.getMessage());
						}
					}
				}
			} finally {
				tempBorrowing = null;
				tempBorrowings = null;
				tempUser = null;
				tempUsers = null;
			}
			break;
		default:
			System.out.println(exitBackString);
			break;
		}
		return 0;
	}

	/**
	 * Controller for Data Administration
	 * 
	 * @return number 0 by default and -2 for implementing GO BACK/UP
	 */
	private int handleAdminDataAdministration(UserType uT) {
		int cmd = getIntLine();
		switch (cmd) {
		case -2:
			return -2;
		case -1:
			System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
			System.exit(0);
			break;
		case 1:
			while (true) {
				System.out.println(textLangProvider.INSTANCE.getProperty("operationUserManagement") + "=");
				DataAdministrationView.menuForAdminDataAUsers();
				if (handleAdminDataAUserComm(uT) == -2) {
					break;
				}
			}
			break;
		case 2:
			while (true) {
				System.out.println(textLangProvider.INSTANCE.getProperty("operationAuthorManagement") + "=");
				DataAdministrationView.menuForAdminDataAAuthors();
				if (handleAdminDataAAuthorComm() == -2) {
					break;
				}
			}
			break;
		case 3:
			while (true) {
				System.out.println(textLangProvider.INSTANCE.getProperty("operationPublicationManagement") + "=");
				DataAdministrationView.menuForAdminDataAPublications();
				if (handleAdminDataAPublicationComm() == -2) {
					break;
				}
			}
			break;
		default:
			System.out.println(exitBackString);
			break;
		}
		return 0;
	}

	/**
	 * Controller for User Data Administration
	 * 
	 * @return number 0 by default and -2 for implementing GO BACK/UP
	 */
	private int handleAdminDataAUserComm(UserType uT) {
		int cmd = getIntLine();
		switch (cmd) {
		case -2:
			return -2;
		case -1:
			System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
			System.exit(0);
			break;
		case 1:
			createNewUser(uT);
			break;
		case 2:
			updateUser();
			break;
		case 3:
			deleteUser();
			break;
		case 4:
			getAllUser();
			break;
		case 5:
			searchUsers();
			break;
		default:
			System.out.println(exitBackString);
			break;
		}
		return 0;
	}

	private void getAllUser() {
		try {
			tempUsers = dac.getAllUsers();
			tempInt = 0;
			for (UserDTO u : tempUsers) {
				System.out.println(++tempInt + "-" + u.getName() + textLangProvider.INSTANCE.getProperty("nrOfBorrows")
						+ u.getBorrow().size());
			}
			System.out.println(textLangProvider.INSTANCE.getProperty("getAllUsersOk"));
		} catch (RequestException e) {
			System.out.println(textLangProvider.INSTANCE.getProperty("getAllUsersNotOk"));
			System.out.println(textLangProvider.INSTANCE.getProperty("errorReason") + " " + e.getMessage());
		} finally {
			tempUsers = null;
		}
	}

	private void updateUser() {
		try {
			searchUsers();
			tempUser = getUserFromResult();
			if (tempUser != null) {
				DataAdministrationView.menuForAdminDataAUsersUpdate();
				int cmd = getIntLine();
				switch (cmd) {
				case 1:
					System.out.println(textLangProvider.INSTANCE.getProperty("enterUserName"));
					String newName = getLine();
					tempUser.setName(newName);
					break;
				case 2:
					System.out.println(textLangProvider.INSTANCE.getProperty("enterUserEmail"));
					String newEmail = getLine();
					tempUser.setEmail(newEmail);
					break;
				case 3:
					System.out.println(textLangProvider.INSTANCE.getProperty("enterUserPassword"));
					String newPassword = getLine();
					tempUser.setPassword(newPassword);
					break;
				case 4:
					System.out.println(textLangProvider.INSTANCE.getProperty("enterUserName"));
					tempUser.setName(getLine());
					System.out.println(textLangProvider.INSTANCE.getProperty("enterUserEmail"));
					tempUser.setEmail(getLine());
					System.out.println(textLangProvider.INSTANCE.getProperty("enterUserPassword"));
					tempUser.setPassword(getLine());
					break;
				default:
					break;
				}

				try {
					dac.updateUser(tempUser);
					System.out.println(textLangProvider.INSTANCE.getProperty("updateOk"));
				} catch (RequestException e) {
					System.out.println(textLangProvider.INSTANCE.getProperty("updateNotOk"));
					System.out.println(textLangProvider.INSTANCE.getProperty("errorReason") + " " + e.getMessage());
				}
			}
		} finally {
			tempUsers = null;
			tempUser = null;
		}
	}

	private void deleteUser() {
		try {
			searchUsers();
			tempUser = getUserFromResult();
			if (tempUser != null) {
				try {
					dac.deleteUser(tempUser.getUUID());
					System.out.println(textLangProvider.INSTANCE.getProperty("deleteOk"));
				} catch (RequestException e) {
					System.out.println(textLangProvider.INSTANCE.getProperty("deleteNotOk"));
					System.out.println(textLangProvider.INSTANCE.getProperty("errorReason") + " " + e.getMessage());
				}
			}
		} finally {
			tempUsers = null;
			tempUser = null;
		}
	}

	private void createNewUser(UserType uT) {
		try {
			tempUser = new UserDTO();
			System.out.println(textLangProvider.INSTANCE.getProperty("enterUserName"));
			tempUser.setName(getLine());
			System.out.println(textLangProvider.INSTANCE.getProperty("enterUserEmail"));
			tempUser.setEmail(getLine());
			System.out.println(textLangProvider.INSTANCE.getProperty("enterUserPassword"));
			tempUser.setPassword(getLine());
			tempUser.setLoyaltyIndex(10);
			if (uT == UserType.SUPER) {
				tempUserType = null;
				while (tempUserType == null) {
					tempUserType = getUserTypeFromResult();
				}
				tempUser.setUserType(tempUserType);
			} else {
				tempUser.setUserType(UserType.READER);
			}
			try {
				dac.createNewUser(tempUser);
				System.out.println(textLangProvider.INSTANCE.getProperty("createOk"));
			} catch (RequestException e) {
				System.out.println(textLangProvider.INSTANCE.getProperty("createNotOk"));
				System.out.println(textLangProvider.INSTANCE.getProperty("errorReason") + " " + e.getMessage());
			}
		} finally {
			tempUser = null;
		}
	}

	/**
	 * Controller for Author Data Administration
	 * 
	 * @return number 0 by default and -2 for implementing GO BACK/UP
	 */
	private int handleAdminDataAAuthorComm() {
		int cmd = getIntLine();
		switch (cmd) {
		case -2:
			return -2;
		case -1:
			System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
			System.exit(0);
			break;
		case 1:
			break;
		default:
			System.out.println(exitBackString);
			break;
		}
		return 0;
	}

	/**
	 * Controller for Publication Data Administration
	 * 
	 * @return number 0 by default and -2 for implementing GO BACK/UP
	 */
	private int handleAdminDataAPublicationComm() {
		int cmd = getIntLine();
		switch (cmd) {
		case -2:
			return -2;
		case -1:
			System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
			System.exit(0);
			break;
		case 5:
			getAllPublication();
			break;
		default:
			System.out.println(exitBackString);
			break;
		}
		return 0;
	}

	private void getAllPublication() {
		try {
			tempPublications = dac.getAllPublication();
			tempInt = 0;
			for (PublicationDTO p : tempPublications) {
				System.out.println(++tempInt + "-" + p.getTitle());
			}
			System.out.println(textLangProvider.INSTANCE.getProperty("getAllPublicationsOk"));
		} catch (RequestException e) {
			System.out.println(textLangProvider.INSTANCE.getProperty("getAllPublicationsNotOk"));
			System.out.println(textLangProvider.INSTANCE.getProperty("errorReason") + " " + e.getMessage());
		}
	}

	/**
	 * Auxiliary for retrieving Publications by title
	 */
	private void searchPublications() {
		System.out.println(textLangProvider.INSTANCE.getProperty("enterPublicationTitle"));
		tempStr = getLine();
		while (tempStr.length() < 3) {
			System.out.println(textLangProvider.INSTANCE.getProperty("searchLenghtToShort"));
			System.out.println(textLangProvider.INSTANCE.getProperty("enterPublicationTitle"));
			tempStr = getLine();
		}
		try {
			tempPublications = dac.getPublications(tempStr);
		} catch (RequestException e) {
			System.out
					.println(textLangProvider.INSTANCE.getProperty("couldNotFindPublication") + " <" + tempStr + ">!");
			System.out.println(textLangProvider.INSTANCE.getProperty("errorReason") + " " + e.getMessage());
			return;
		}
		tempInt = 0;
		for (PublicationDTO p : tempPublications) {
			System.out.println(++tempInt + ": " + p.toString());
		}

	}

	/**
	 * Auxiliary for retrieving a User's Borrowings
	 */
	private void searchBorrowedPublications() {
		tempBorrowings = tempUser.getBorrow();
		if (tempBorrowings == null) {
			System.out.println(textLangProvider.INSTANCE.getProperty("couldNotFindBorrowedPublication") + " <"
					+ tempUser.getName() + ">!");
			return;
		} else if (tempBorrowings.size() == 0) {
			tempBorrowings = null;
			return;
		}
		tempInt = 0;
		for (BorrowingDTO b : tempBorrowings) {
			System.out.println(++tempInt + ": " + b.getPublication().getTitle().toString());
			b.setUserId(tempUser.getUUID());
		}
	}

	/**
	 * Auxiliary for retrieving Users by name
	 */
	private void searchUsers() {
		System.out.println(textLangProvider.INSTANCE.getProperty("enterUserNameForSearch"));
		tempStr = getLine();
		while (tempStr.length() < 3) {
			System.out.println(textLangProvider.INSTANCE.getProperty("searchLenghtToShort"));
			System.out.println(textLangProvider.INSTANCE.getProperty("enterUserNameForSearch"));
			tempStr = getLine();
		}
		try {
			tempUsers = dac.getUsers(tempStr);
		} catch (RequestException e) {
			System.out.println(textLangProvider.INSTANCE.getProperty("couldNotFindUsers") + " <" + tempStr + ">!");
			System.out.println(textLangProvider.INSTANCE.getProperty("errorReason") + " " + e.getMessage());
			return;
		}
		tempInt = 0;
		for (UserDTO u : tempUsers) {
			System.out.println(++tempInt + ": " + u.toString() + textLangProvider.INSTANCE.getProperty("nrOfBorrows")
					+ u.getBorrow().size());
		}
	}

	/**
	 * Controller for Publication selection from retrieved list
	 * 
	 * @return selected Publication
	 */
	private PublicationDTO getPublicationFromResult() {
		if (tempPublications == null || tempPublications.isEmpty()) {
			return null;
		} else {
			System.out.println(textLangProvider.INSTANCE.getProperty("selectNrFromList"));
			int cmd = getIntLine();
			if (cmd == -1) {
				System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
				System.exit(0);
				return null;
			} else if ((cmd <= tempPublications.size()) && (cmd > 0)) {
				return tempPublications.get(--cmd);
			} else {
				System.out.println(exitString);
				return null;
			}
		}
	}

	/**
	 * Controller for Borrowing selection from retrieved list
	 * 
	 * @return selected Borrowing
	 */
	private BorrowingDTO getBorrowedPublicationFromResult() {
		if (tempBorrowings == null || tempBorrowings.isEmpty()) {
			return null;
		} else {
			System.out.println(textLangProvider.INSTANCE.getProperty("selectNrFromList"));
			int cmd = getIntLine();
			if (cmd == -1) {
				System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
				System.exit(0);
				return null;
			} else if ((cmd <= tempBorrowings.size()) && (cmd > 0)) {
				return tempBorrowings.get(--cmd);
			} else {
				System.out.println(exitString);
				return null;
			}
		}
	}

	/**
	 * Controller for User selection from retrieved list
	 * 
	 * @return selected User
	 */
	private UserDTO getUserFromResult() {
		if (tempUsers == null || tempUsers.isEmpty()) {
			return null;
		} else {
			System.out.println(textLangProvider.INSTANCE.getProperty("selectNrFromList"));
			int cmd = getIntLine();
			if (cmd == -1) {
				System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
				System.exit(0);
				return null;
			} else if ((cmd <= tempUsers.size()) && (cmd > 0)) {
				return tempUsers.get(--cmd);
			} else {
				System.out.println(exitString);
				return null;
			}
		}
	}

	/**
	 * Controller for User Type selection when Super user creates new user.
	 * 
	 * @return selected User
	 */
	private UserType getUserTypeFromResult() {
		DataAdministrationView.menuForAdminDataAUsersSuperCreate();
		System.out.println(textLangProvider.INSTANCE.getProperty("selectNrFromList"));
		int cmd = getIntLine();
		if (cmd == -1) {
			System.out.println(textLangProvider.INSTANCE.getProperty("exitProg"));
			System.exit(0);
			return null;
		} else if (cmd == 1) {
			return UserType.READER;
		} else if (cmd == 2) {
			return UserType.ADMIN;
		} else {
			System.out.println(exitString);
			return null;
		}
	}
}
