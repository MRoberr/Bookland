package edu.msg.bookland.desktop.model;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.msg.bookland.desktop.ConnectionException;
import edu.msg.bookland.rmi.AuthorServiceRmi;
import edu.msg.bookland.rmi.BorrowingServiceRmi;
import edu.msg.bookland.rmi.PublicationServiceRmi;
import edu.msg.bookland.rmi.UserServiceRmi;

public enum ConnectionModel {
	INSTANCE;

	private static Registry registry;
	public static AuthorServiceRmi authorServiceRmi;
	public static BorrowingServiceRmi borrowingServiceRmi;
	public static PublicationServiceRmi publicationServiceRmi;
	public static UserServiceRmi userServiceRmi;

	static {
		try {
			registry = LocateRegistry.getRegistry("localhost", UserServiceRmi.RMI_PORT);
			authorServiceRmi = (AuthorServiceRmi) registry.lookup(AuthorServiceRmi.RMI_NAME);
			borrowingServiceRmi = (BorrowingServiceRmi) registry.lookup(BorrowingServiceRmi.RMI_NAME);
			userServiceRmi = (UserServiceRmi) registry.lookup(UserServiceRmi.RMI_NAME);
			publicationServiceRmi = (PublicationServiceRmi) registry.lookup(PublicationServiceRmi.RMI_NAME);
		} catch (RemoteException | NotBoundException e) {
			//throw new ConnectionException("Connection error.");
			System.out.println(e.getMessage());
		}

	}
}
