package edu.msg.bookland;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.log4j.Logger;

import edu.msg.bookland.repository.jdbc.ConnectionManager;
import edu.msg.bookland.rmi.UserServiceRmi;
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
			LOGGER.info("Server online!");

		} catch (RemoteException e) {
			LOGGER.error("Server not running", e);
		}
	}

}
