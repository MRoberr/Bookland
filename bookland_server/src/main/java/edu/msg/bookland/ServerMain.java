package edu.msg.bookland;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.msg.bookland.rmi.AuthorServiceRmi;
import edu.msg.bookland.rmi.BorrowingServiceRmi;
import edu.msg.bookland.rmi.PublicationServiceRmi;
import org.apache.log4j.Logger;

import edu.msg.bookland.repository.jdbc.ConnectionManager;
import edu.msg.bookland.rmi.UserServiceRmi;
import edu.msg.bookland.service.AuthorService;
import edu.msg.bookland.service.BorrowingService;
import edu.msg.bookland.service.PublicationService;
import edu.msg.bookland.service.UserService;

/**
 * Entry point for running Server
 * 
 * @author Jozsef Solomon
 */
public class ServerMain {
	private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);

	public static void main(String[] args) {
		initServer();
	}

	/**
	 * Registry to all RMI methods.
	 */
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
			LOGGER.info("Server online!");

		} catch (RemoteException e) {
			LOGGER.error("Server not running", e);
		}
	}

}