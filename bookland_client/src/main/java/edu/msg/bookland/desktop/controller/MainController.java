package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;
import java.util.Scanner;

import edu.msg.bookland.desktop.ConnectionException;
import edu.msg.bookland.desktop.model.ConnectionModel;
import edu.msg.bookland.model.UserType;

public class MainController {
	private CustomServiceController scc = new CustomServiceController();
	private DataAdministrationController dac = new DataAdministrationController();
	private Scanner scanner = new Scanner(System.in);
	
	public MainController() {
		consoleLogin();
	}
	
	private void consoleLogin() {
		System.out.println("Please enter your name and password!");		
		try {
			UserType login = ConnectionModel.INSTANCE.userServiceRmi.login(scanner.nextLine(), scanner.nextLine());
			System.out.println(login);
		} catch (RemoteException e) {		
			throw new ConnectionException("Connection error.");
		}
	}		
	
}
