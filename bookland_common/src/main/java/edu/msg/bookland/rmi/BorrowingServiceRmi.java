package edu.msg.bookland.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.model.Borrowing;

public interface BorrowingServiceRmi extends Remote {
	
	public static final String RMI_NAME = "Borrowing";

	public static final int RMI_PORT = 1099;
	
	/**
	 * get all borrow objects from persistence layer
	 * @return list of Borrow objects
	 * @throws RemoteException
	 */
	
	public List<Borrowing> getAllBorrows() throws RemoteException;
	
	/**
	 * inserts a borrowing object into persistence layer
	 * @param borrow - Borrowing object
	 * @return true if the insert was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean insertBorrowing(Borrowing borrow) throws RemoteException;
	
	/**
	 * update one borrowing objects values in the persistence layer
	 * @param borrow - Borrowing object
	 * @return true if the insert was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean updateBorrowing(Borrowing borrow) throws RemoteException;
	
	/**
	 * delete borrow object from persistence layer
	 * @param borrow - Borrowing object
	 * @return true if the delete was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean deleteBorrow(Borrowing borrow) throws RemoteException;
	
	/**
	 * returns one borrow entity by its UUID
	 * @param uuid - the unique identifier of the given borrow
	 * @return returns a Borrow object
	 * @throws RemoteException
	 */
	public Borrowing getBorrowByUUID(String uuid) throws RemoteException; 
	
	/**
	 * Changes the return date of the borrow object in the persistence layer. 
	 * Updates the number of copies left field of the publication in the persistence layer
	 * Updates if the users trust index must be changed and updates it if necessary
	 * @param borrow - Borrowing object
	 * @return true if publication has been returned correctly, false if not
	 * @throws RemoteException
	 */
	public boolean returnPublication (Borrowing borrow) throws RemoteException;
	
	/**
	 * Inserts a new borrow object, if conditions are met.
	 * Updates the number of copies left field of the publication in the persistence layer
	 * @param borrow - Borrowing object
	 * @return true if publication has been borrowed correctly, false if not
	 * @throws RemoteException
	 */
	public boolean borrowPublication (Borrowing borrow) throws RemoteException;
}
