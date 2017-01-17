package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import edu.msg.bookland.desktop.model.ConnectionModel;
import edu.msg.bookland.desktop.view.CustomServiceView;
import edu.msg.bookland.desktop.view.DataAdministrationView;
import edu.msg.bookland.desktop.view.MainView;
import edu.msg.bookland.model.Borrowing;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.model.Tuple;
import edu.msg.bookland.model.User;
import edu.msg.bookland.model.UserType;

public class MainController {
	private static final Logger LOGGER = Logger.getLogger(MainController.class);
	private CustomServiceController csc = new CustomServiceController();
	private DataAdministrationController dac = new DataAdministrationController();
	private Scanner scanner = new Scanner(System.in);
	private int tempInt = 0;
	private String tempStr = "";
	private List<Publication> tempPublications;
	private List<User> tempUsers;
	private List<Tuple> tempTuples;
	private Publication tempPublication;
	private User tempUser;
	private Tuple tempTuple;
	private final String exitBackString = " Try again or (-1 exit) (-2 back).";
	private final String exitString = " Try again or (-1 exit).";

	public MainController() {
		while (true) {
			consoleLogin();
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
				System.out.println("Incorrect command." + exitString);
				scanner.next();
			}
		}
		return cmd;
	}

	private void consoleLogin() {
		System.out.println("Please enter your name and password!");
		try {
			UserType login = ConnectionModel.USER_SERVICE_RMI.login(getLine(), getLine());
			if (login == null) {
				System.out.println("Invalid user name or password, try again!\n");
			} else if (login.equals(UserType.READER)) {
				System.out.println("Logged in as user=");
				while (true) {
					MainView.menuForUser();
					handleUserCommand();
				}
			} else if (login.equals(UserType.ADMIN)) {
				System.out.println("Logged in as admin=");
				while (true) {
					MainView.menuInitForAdmin();
					handleAdminCommand();
				}
			}
		} catch (RemoteException e) {
			System.out.println("Connection error, login failed.");
		}
	}

	private void handleAdminCommand() {
		int cmd = getIntLine();
		switch (cmd) {
		case -1:
			System.out.println("Exit.");
			System.exit(0);
			break;
		case 1:
			while (true) {
				System.out.println("=CustomService=");
				CustomServiceView.menuForAdminCustomS();
				if (handleAdminCustomService() == -2) {
					break;
				}
			}
			break;
		case 2:
			while (true) {
				System.out.println("=DataAdministration=");
				DataAdministrationView.menuForAdminDataA();
				if (handleAdminDataAdministration() == -2) {
					break;
				}
			}
			break;
		default:
			System.out.println("Invalid Command." + exitString);
			break;
		}
	}

	private int handleAdminCustomService() {
		int cmd = getIntLine();
		switch (cmd) {
		case -2:
			return -2;
		case -1:
			System.out.println("Exit.");
			System.exit(0);
			break;
		case 1:
			System.out.println("Borrow publication=");
			try {
				System.out.println("Enter user name!");
				searchUsers();
				tempUser = getUserFromResult();
				if (tempUser != null) {
					System.out.println("Enter publication title!");
					searchPublications();
					tempPublication = getPublicationFromResult();
					if (tempPublication != null) {
						Borrowing borrowing = new Borrowing();
						borrowing.setUserId(tempUser.getUUID());
						borrowing.setPublicationId(tempPublication.getUUID());
						if (csc.borrowPublication(borrowing)) {
							System.out.println("Borrowing successful!");
						} else {
							System.out.println("Borrowing not successful!");
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
			System.out.println("Return publication=");
			try {
				System.out.println("Enter user name!");
				searchUsers();
				tempUser = getUserFromResult();
				if (tempUser != null) {
					searchBorrowedPublications(tempUser.getUUID());
					tempTuple = getBorrowedPublicationFromResult();
					if (tempTuple != null) {
						Borrowing borrowing = tempTuple.getBorrow();
						if (csc.returnPublication(borrowing)) {
							System.out.println(
									"Returning of <" + tempTuple.getPublication().getTitle() + "> successful!");
						} else {
							System.out.println(
									"Returning of <" + tempTuple.getPublication().getTitle() + "> not successful!");
						}
					}
				}
			} finally {
				tempTuple = null;
				tempTuples = null;
				tempUser = null;
				tempUsers = null;
			}
			break;
		default:
			System.out.println("Invalid Command." + exitBackString);
			break;
		}
		return 0;
	}

	private int handleAdminDataAdministration() {
		int cmd = getIntLine();
		switch (cmd) {
		case -2:
			return -2;
		case -1:
			System.out.println("Exit.");
			System.exit(0);
			break;
		case 1:
			System.out.println("");
			break;
		case 2:
			System.out.println("");
			break;
		default:
			System.out.println("Invalid Command." + exitBackString);
			break;
		}
		return 0;
	}

	private void handleUserCommand() {
		int cmd = getIntLine();
		switch (cmd) {
		case -1:
			System.out.println("Exit.");
			System.exit(0);
			break;
		case 1:
			System.out.println("Enter publication title!");
			searchPublications();
			break;
		default:
			System.out.println("Invalid Command." + exitString);
			break;
		}
	}

	private void searchPublications() {
		tempStr = getLine();
		tempPublications = dac.getPublications(tempStr);
		if ((tempPublications == null) || (tempPublications.isEmpty())) {
			System.out.println("Couldn't find any publication with title <" + tempStr + ">!");
			return;
		}
		tempInt = 0;
		for (Publication p : tempPublications) {
			System.out.println(++tempInt + ": " + p.toString());
		}
	}

	private void searchBorrowedPublications(String uuid) {
		tempTuples = csc.getPublicationsBorrowedByUser(uuid);
		if ((tempTuples == null) || (tempTuples.isEmpty())) {
			System.out.println("Couldn't find any publication for user!");
			return;
		}
		tempInt = 0;
		for (Tuple t : tempTuples) {
			System.out.println(++tempInt + ": " + t.toString());
		}
	}

	private void searchUsers() {
		tempStr = getLine();
		tempUsers = dac.getUsers(tempStr);
		if ((tempUsers == null) || (tempUsers.isEmpty())) {
			System.out.println("Couldn't find any user with name <" + tempStr + ">!");
			return;
		}
		tempInt = 0;
		for (User u : tempUsers) {
			System.out.println(++tempInt + ": " + u.toString());
		}
	}

	private Publication getPublicationFromResult() {
		if (tempPublications == null) {
			return null;
		} else {
			System.out.println("Select number from the list above.");
			int cmd = getIntLine();
			if (cmd == -1) {
				System.out.println("Exit.");
				System.exit(0);
				return null;
			} else if ((cmd <= tempPublications.size()) && (cmd > 0)) {
				return tempPublications.get(--cmd);
			} else {
				System.out.println("Invalid Command." + exitString);
				return null;
			}
		}
	}

	private Tuple getBorrowedPublicationFromResult() {
		if (tempTuples == null) {
			return null;
		} else if ((tempTuples.size()>0) && ((tempTuples.get(0).getBorrow() == null) || (tempTuples.get(0).getPublication() == null)) ){
			return null;
		} else {
			System.out.println("Select number from the list above.");
			int cmd = getIntLine();
			if (cmd == -1) {
				System.out.println("Exit.");
				System.exit(0);
				return null;
			} else if ((cmd <= tempTuples.size()) && (cmd > 0)) {
				return tempTuples.get(--cmd);
			} else {
				System.out.println("Invalid Command." + exitString);
				return null;
			}
		}
	}

	private User getUserFromResult() {
		if (tempUsers == null) {
			return null;
		} else {
			System.out.println("Select number from the list above.");
			int cmd = getIntLine();
			if (cmd == -1) {
				System.out.println("Exit.");
				System.exit(0);
				return null;
			} else if ((cmd <= tempUsers.size()) && (cmd > 0)) {
				return tempUsers.get(--cmd);
			} else {
				System.out.println("Invalid Command." + exitString);
				return null;
			}
		}
	}

}
