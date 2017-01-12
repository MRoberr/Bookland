package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

import edu.msg.bookland.desktop.ConnectionException;
import edu.msg.bookland.desktop.model.ConnectionModel;
import edu.msg.bookland.desktop.view.DataAdministrationView;
import edu.msg.bookland.desktop.view.MainView;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.model.UserType;

public class MainController {
	private CustomServiceController scc = new CustomServiceController();
	private DataAdministrationController dac = new DataAdministrationController();
	private Scanner scanner = new Scanner(System.in);
	private String tempStr = "";

	public MainController() {
		consoleLogin();
	}

	private void consoleLogin() {
		System.out.println("Please enter your name and password!");		
		try {
			
			UserType login = ConnectionModel.INSTANCE.userServiceRmi.login(scanner.nextLine(), scanner.nextLine());
			System.out.println(login);
			if (login.equals(UserType.READER)) {
				System.out.println("Logged in as user...");
				MainView.menuForUser();
				while (true) {
					handleUserCommand();
				}
			} else if (login.equals(UserType.ADMIN)) {
				System.out.println("Logged in as admin...");
				MainView.menuInitForAdmin();
				while (true) {
					handleAdminCommand();		
					//handleAdminDA(); handleAdminCS();
				}
			} else {
				System.out.println("Invalid user name or password, please try again!");
				consoleLogin();
			}
		} catch (RemoteException e) {		
			throw new ConnectionException("Connection error.");
		}
	}

	private void handleAdminCommand() {
		int cmd = scanner.nextInt();
		switch (cmd) {
	
		}
	}

	private void handleUserCommand() {
		System.out.println("Enter title!");
		searchPublications();
	}
	
	//Admin/Reader 1 
	private void searchPublications() {
		tempStr = scanner.nextLine(); 
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
