package edu.msg.bookland;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.msg.bookland.rmi.AuthorServiceRmi;
import edu.msg.bookland.rmi.BorrowingServiceRmi;
import edu.msg.bookland.rmi.PublicationServiceRmi;
import edu.msg.bookland.rmi.UserServiceRmi;
import edu.msg.bookland.service.AuthorService;
import edu.msg.bookland.service.BorrowingService;
import edu.msg.bookland.service.PublicationService;
import edu.msg.bookland.service.UserService;

public class ServerMain {

	public static void main(String[] args) {
		initServer();
	}
	
	public static void initServer() {
		try {
			Registry registry = LocateRegistry.createRegistry(UserServiceRmi.RMI_PORT);
			
			UserService uService = new UserService();
			registry.rebind(UserServiceRmi.RMI_NAME, uService);
			AuthorService aService = new AuthorService();
			registry.rebind(AuthorServiceRmi.RMI_NAME, aService);
			BorrowingService boService = new BorrowingService();
			registry.rebind(BorrowingServiceRmi.RMI_NAME, boService);
			PublicationService pubService = new PublicationService();
			registry.rebind(PublicationServiceRmi.RMI_NAME, pubService);
			
			System.out.println("Server online!");
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
