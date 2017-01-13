package edu.msg.bookland.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.model.Borrowing;

/**
 * RMI interface for Borrow CRUD operations.
 * 
 * @author Simo Zoltan
 *
 */
public interface BorrowingServiceRmi extends Remote {
	
	public static final String RMI_NAME = "Borrowing";
	public static final int RMI_PORT = 1099;
	
	/**
	 * This function gets all the Borrows from DB.
	 * 
	 * @return list of Borrow objects
	 * @throws RemoteException
	 */	
	public List<Borrowing> getAllBorrows() throws RemoteException;
	
	/**
	 * This method inserts a Borrow into DB.
	 * 
	 * @param borrow - Borrowing object
	 * @return true if the insert was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean insertBorrowing(Borrowing borrow) throws RemoteException;
	
	/**
	 * This method updates the Borrow by its id.
	 * 
	 * @param borrow - Borrowing object
	 * @return true if the insert was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean updateBorrowing(Borrowing borrow) throws RemoteException;
	
	/**
	 * This method deletes the Borrow by its id.
	 * 
	 * @param borrow - Borrowing object
	 * @return true if the delete was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean deleteBorrow(Borrowing borrow) throws RemoteException;
	
	/**
	 * This method searches for a Borrowing with the specified uuid.
	 *  
	 * @param uuid - the unique identifier of the given borrow
	 * @return returns a Borrow object
	 * @throws RemoteException
	 */
	public Borrowing getBorrowByUUID(String uuid) throws RemoteException; 
	
	/**
	 * Deletes the borrowing in the DB.
	 * Updates the number of copies left field of the publication in the DB.
	 * Updates the users trust index, if needed in the DB.
	 * 
	 * @param borrow - Borrowing object
	 * @return true if publication has been returned correctly, false if not
	 * @throws RemoteException
	 */
	public boolean returnPublication (Borrowing borrow) throws RemoteException;
	
	/**
	 * Inserts a new borrow object, if conditions are met.
	 * Updates the number of copies left field of the publication in the DB.
	 * 
	 * @param borrow - Borrowing object
	 * @return true if publication has been borrowed correctly, false if not
	 * @throws RemoteException
	 */
	public boolean borrowPublication (Borrowing borrow) throws RemoteException;
}
