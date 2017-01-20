package edu.msg.bookland.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.common.model.BorrowingDTO;

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
	 * This method searches for a Borrowing with the specified uuid.
	 * 
	 * @param uuid
	 *            - the unique identifier of the given borrow
	 * @return returns a Borrow object
	 * @throws RemoteException
	 */
	public List<BorrowingDTO> getBorrowByUserUUID(String uuid) throws RemoteException;

	/**
	 * Deletes the borrowing in the DB. Updates the number of copies left field
	 * of the publication in the DB. Updates the users trust index, if needed in
	 * the DB.
	 * 
	 * @param borrow
	 *            - Borrowing object
	 * @return true if publication has been returned correctly, false if not
	 * @throws RemoteException
	 */
	public void returnPublication(BorrowingDTO borrow) throws RemoteException;

	/**
	 * Inserts a new borrow object, if conditions are met. Updates the number of
	 * copies left field of the publication in the DB.
	 * 
	 * @param borrow
	 *            - Borrowing object
	 * @return true if publication has been borrowed correctly, false if not
	 * @throws RemoteException
	 */
	public void borrowPublication(BorrowingDTO borrow) throws RemoteException;
}
