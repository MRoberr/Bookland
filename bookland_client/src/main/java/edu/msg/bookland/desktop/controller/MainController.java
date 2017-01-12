package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.msg.bookland.desktop.ConnectionException;
import edu.msg.bookland.desktop.model.ConnectionModel;
import edu.msg.bookland.desktop.view.CustomServiceView;
import edu.msg.bookland.desktop.view.DataAdministrationView;
import edu.msg.bookland.desktop.view.MainView;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.model.UserType;

public class MainController {
	private CustomServiceController scc = new CustomServiceController();
	private DataAdministrationController dac = new DataAdministrationController();
	private Scanner scanner = new Scanner(System.in);
	private String tempStr = "";
	final String exitString = " Try again, or type -1 for exit.";

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
			UserType login = ConnectionModel.userServiceRmi.login(getLine(), getLine());
			System.out.println(login);
			if (login.equals(UserType.READER)) {
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
			} else {
				System.out.println("Invalid user name or password, please try again!");
				consoleLogin();
			}
		} catch (RemoteException e) {
			System.out.println("Login failed. Try again.");
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
				handleAdminCustomService();
			}
		case 2:
			while (true) {
				System.out.println("=DataAdministration=");
				DataAdministrationView.menuForAdminDataA();
				handleAdminDataAdministration();
			}
		default:
			System.out.println("Invalid Command." + exitString);
			break;
		}
	}

	private void handleAdminCustomService() {
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
		case 2:
			System.out.println("Borrow publication=");
			break;
		case 3:
			System.out.println("Return publication=");
			break;
		default:
			System.out.println("Invalid Command." + exitString);
			break;
		}
	}

	private void handleAdminDataAdministration() {
		int cmd = getIntLine();
		switch (cmd) {
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
			System.out.println("Invalid Command." + exitString);
			break;
		}
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

	// Admin/Reader 1
	private void searchPublications() {
		tempStr = getLine();
		List<Publication> publications = dac.getPublications(tempStr);
		if (publications.isEmpty()) {
			System.out.println("Couldn't find any publication with title >" + tempStr + "<!");
			return;
		}
		for (Publication publication : publications) {
			System.out.println(publication.toString());
		}
	}

}
