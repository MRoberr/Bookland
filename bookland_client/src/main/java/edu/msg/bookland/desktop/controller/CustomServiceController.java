package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.BorrowingDTO;
import edu.msg.bookland.desktop.ConnectionException;
import edu.msg.bookland.desktop.model.ConnectionModel;

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
	public void borrowPublication(BorrowingDTO borrowing) {
		try {
			ConnectionModel.BORROWING_SERVICE_RMI.borrowPublication(borrowing);
		} catch (RemoteException e) {
			LOGGER.error("No connection when searching publications.", e);
			throw new ConnectionException(e.getMessage());
		}
	}
	
	/**
	 * This method gets the users all borrows and borrowed publications.
	 * 
	 * @param uuid
	 * @return Tuple of borrowing and publication
	 */
	public List<BorrowingDTO> getPublicationsBorrowedByUser(String uuid) {
		try {
			return ConnectionModel.BORROWING_SERVICE_RMI.getBorrowByUserUUID(uuid);
		} catch (RemoteException e) {
			LOGGER.error("No connection when searching for publications borrowed by the user.", e);
			return null;
		}
	}
	
	/**
	 * Through this method returning of a borrow is achieved.
	 * 
	 * @param borrowing
	 * @return true, if return borrow succeeded
	 */
	public void returnPublication(BorrowingDTO borrowing) {
		try {
			ConnectionModel.BORROWING_SERVICE_RMI.returnPublication(borrowing);
		} catch (RemoteException e) {
			LOGGER.error("No connection when returning a borrowed publication.", e);
			throw new ConnectionException(e.getMessage());
		}
	}

}
