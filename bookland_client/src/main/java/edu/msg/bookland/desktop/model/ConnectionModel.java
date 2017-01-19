package edu.msg.bookland.desktop.model;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.rmi.AuthorServiceRmi;
import edu.msg.bookland.common.rmi.BorrowingServiceRmi;
import edu.msg.bookland.common.rmi.PublicationServiceRmi;
import edu.msg.bookland.common.rmi.UserServiceRmi;
import edu.msg.bookland.desktop.ConnectionException;

/**
 * 
 * This class has all the static final RMI interfaces for communicating with the
 * server.
 * 
 * @author Simo Zoltan
 *
 */
public class ConnectionModel {

	private static final Logger LOGGER = Logger.getLogger(ConnectionModel.class);
	private static final Registry REGISTRY;
	public static final AuthorServiceRmi AUTHOR_SERVICE_RMI;
	public static final BorrowingServiceRmi BORROWING_SERVICE_RMI;
	public static final PublicationServiceRmi PUBLICATION_SERVICE_RMI;
	public static final UserServiceRmi USER_SERVICE_RMI;

	private ConnectionModel() {
	}

	static {
		try {
			REGISTRY = LocateRegistry.getRegistry("localhost", UserServiceRmi.RMI_PORT);
			AUTHOR_SERVICE_RMI = (AuthorServiceRmi) REGISTRY.lookup(AuthorServiceRmi.RMI_NAME);
			BORROWING_SERVICE_RMI = (BorrowingServiceRmi) REGISTRY.lookup(BorrowingServiceRmi.RMI_NAME);
			PUBLICATION_SERVICE_RMI = (PublicationServiceRmi) REGISTRY.lookup(PublicationServiceRmi.RMI_NAME);
			USER_SERVICE_RMI = (UserServiceRmi) REGISTRY.lookup(UserServiceRmi.RMI_NAME);
		} catch (RemoteException | NotBoundException e) {
			LOGGER.error("RMI connection error.");
			throw new ConnectionException("RMI connection error.");
		}
	}
}
