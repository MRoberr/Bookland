package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.BorrowingDTO;
import edu.msg.bookland.common.model.ServiceException;
import edu.msg.bookland.desktop.RequestException;
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
	 * @throws RequestException
	 *             when either connection or request at server failed
	 */
	public void borrowPublication(BorrowingDTO borrowing) {
		try {
			ConnectionModel.BORROWING_SERVICE_RMI.borrowPublication(borrowing);
		} catch (ServiceException e) {
			LOGGER.error("Server could not create borrow", e);
			throw new RequestException(e.getMessage());
		} catch (RemoteException e) {
			LOGGER.error("Connection with server failed at borrow publication.", e);
			throw new RequestException(e.getMessage());
		}
	}

	/**
	 * Through this method returning of a borrow is achieved.
	 * 
	 * @param borrowing
	 * @throws RequestException
	 *             when either connection or request at server failed
	 */
	public void returnPublication(BorrowingDTO borrowing) {
		try {
			ConnectionModel.BORROWING_SERVICE_RMI.returnPublication(borrowing);
		} catch (ServiceException e) {
			LOGGER.error("Server could not return borrow", e);
			throw new RequestException(e.getMessage());
		} catch (RemoteException e) {
			LOGGER.error("Connection with server failed at return borrowed publication.", e);
			throw new RequestException(e.getMessage());
		}
	}

}
