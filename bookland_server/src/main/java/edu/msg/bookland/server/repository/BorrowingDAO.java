package edu.msg.bookland.server.repository;

import edu.msg.bookland.server.model.Borrowing;

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
	void deleteBorrowing(String userId, String publicationId) throws RepositoryException;

	
	Borrowing getBorrowById(String userId, String publicationId) throws RepositoryException;
	//void updateBorrowing(Borrowing borrowing) throws RepositoryException;

}
