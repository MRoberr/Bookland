package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.desktop.model.ConnectionModel;
import edu.msg.bookland.model.Borrowing;
import edu.msg.bookland.model.Publication;

/**
 * This is the controller class for borrowing and returning publication
 * requests.
 * 
 * @author Simo Zoltan
 *
 */
public class CustomServiceController {
	private static final Logger LOGGER = Logger.getLogger(CustomServiceController.class);
	
	/**
	 * This method creates a new borrowing.
	 * 
	 * @param borrowing
	 * @return true, if borrow successful
	 */
	public boolean borrowPublication(Borrowing borrowing) {
		try {
			return ConnectionModel.BORROWING_SERVICE_RMI.borrowPublication(borrowing);
		} catch (RemoteException e) {
			LOGGER.error("No connection when searching publications.");
			return false;
		}
	}
	
//	public List<Tuple<Publication, Borrowing> getPublicationsBorrowedByUser(String uuid) {
//		try {
//			return ConnectionModel.BORROWING_SERVICE_RMI.getBorrowByUserUUID(uuid);
//		} catch (RemoteException e) {
//			LOGGER.error("No connection when searching publications borrowed by the user.");
//			return null;
//		}
//	}

}
