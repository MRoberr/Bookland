package edu.msg.bookland.server.repository;

import edu.msg.bookland.common.model.Borrowing;

/**
 * Defines database methods for BORROWING Model
 * 
 * @author Jozsef Solomon
 */
public interface BorrowingDAO {
	/**
	 * This method define insert for {@link Borrowing}
	 * 
	 * @param borrowing
	 * @throws RepositoryException
	 */
	void insertBorrowing(Borrowing borrowing) throws RepositoryException;

	/**
	 * This method define delete for {@link Borrowing}
	 * 
	 * @param borrowing
	 * @throws RepositoryException
	 */
	void deleteBorrowing(Borrowing borrowing) throws RepositoryException;

	
	
	void updateBorrowing(Borrowing borrowing) throws RepositoryException;

}
