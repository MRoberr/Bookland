package edu.msg.bookland.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.common.model.BorrowingDTO;
import edu.msg.bookland.common.model.ServiceException;

/**
 * RMI interface for Borrow CRUD operations.
 * 
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */
public interface BorrowingServiceRmi extends Remote {

	public static final String RMI_NAME = "Borrowing";

	/**
	 * Deletes the borrowing in the DB. Updates the number of copies left field
	 * of the publication in the DB. Updates the users trust index, if needed in
	 * the DB.
	 * 
	 * @param borrow
	 *            - Borrowing object
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when Server could not finish request
	 */
	public void returnPublication(BorrowingDTO borrow) throws RemoteException, ServiceException;

	/**
	 * Inserts a new borrow object, if conditions are met. Updates the number of
	 * copies left field of the publication in the DB.
	 * 
	 * @param borrow
	 *            - Borrowing object
	 * @return true if publication has been borrowed correctly, false if not
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when Server could not finish request
	 */
	public void borrowPublication(BorrowingDTO borrow) throws RemoteException, ServiceException;
}
