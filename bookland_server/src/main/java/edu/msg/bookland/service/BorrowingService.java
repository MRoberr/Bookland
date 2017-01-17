package edu.msg.bookland.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.model.Borrowing;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.model.Tuple;
import edu.msg.bookland.repository.BorrowingDAO;
import edu.msg.bookland.repository.DAOFactory;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.repository.jdbc.JDBCUserDAO;
import edu.msg.bookland.rmi.BorrowingServiceRmi;

/**
 * Implement methods of UserServiceRmi. Call methods of DAO and contains
 * Business Logic
 * 
 * @author Terez Sipos
 * @author Jozsef Solomon
 */
public class BorrowingService extends UnicastRemoteObject implements BorrowingServiceRmi {

	private static final long serialVersionUID = 7771473351628284744L;
	private static final Logger LOGGER = Logger.getLogger(JDBCUserDAO.class);
	private BorrowingDAO borrowingDAO;

	/**
	 * initialize borrowingDAO
	 * 
	 * @throw ServiceException if can't get a DAO
	 */
	public BorrowingService() throws RemoteException {
		borrowingDAO = DAOFactory.getDAOFactory().getBorrowingDAO();
	}

	/**
	 * Inserts the given {@link Borrowing} into database.
	 * @param borrow 
	 * the {@link Borrowing} object
	 * 
	 * @return false if insert fails otherwise true
	 * 
	 * @throws RemoteException 
	 */
	public boolean insertBorrowing(Borrowing borrow) throws RemoteException {
		try {
			borrowingDAO.insertBorrowing(borrow);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to insert borrowing");
			return false;
		}
	}

	/**
	 * Update the given {@link Borrowing} into database.
	 * @param borrow 
	 * the {@link Borrowing} object
	 * 
	 * @return false if update fails otherwise true
	 * 
	 * @throws RemoteException 
	 */
	public boolean updateBorrowing(Borrowing borrow) throws RemoteException {
		try {
			// borrowingDAO.up(borrow);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to insert borrowing");
			return false;
		}
	}

	/**
	 * Inserts the given {@link Borrowing} into database.
	 * @param borrow 
	 * the {@link Borrowing} object
	 * 
	 * @return false if delete fails otherwise true
	 * 
	 * @throws RemoteException 
	 */
	public boolean deleteBorrow(Borrowing borrow) throws RemoteException {
		try {
			borrowingDAO.deleteBorrowing(borrow);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to insert borrowing");
			return false;
		}
	}

	@Override
	public List<Tuple> getBorrowByUserUUID(String uuid) throws RemoteException {
		List<Tuple> borrowedPublications=new ArrayList<>();
		List<Borrowing> borrowList;
		try {
			borrowList = borrowingDAO.getPublicationsBorrowedByUser(uuid);
			PublicationService pubService=new PublicationService();
			for(Borrowing borrow:borrowList){
				Publication p=pubService.getPublicationByUuid(borrow.getPublicationId());
				borrowedPublications.add(new Tuple(borrow, p));
			}
			return borrowedPublications;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to insert borrowing");
			return null;
		}
	}

	@Override
	public boolean returnPublication(Borrowing borrow) throws RemoteException {
		//to do
		try {
			borrowingDAO.deleteBorrowing(borrow);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to insert borrowing");
			return false;
		}
	}

	@Override
	public boolean borrowPublication(Borrowing borrow) throws RemoteException {
		//to do
		try {
			borrowingDAO.insertBorrowing(borrow);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to insert borrowing");
			return false;
		}
	}

}
