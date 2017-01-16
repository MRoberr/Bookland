package edu.msg.bookland.repository;

import java.util.List;

import edu.msg.bookland.model.Borrowing;

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

	/**
	 * This method define select for {@link Borrowing} with conditon
	 * 
	 * @param userUuid
	 * @return list of Borrowing if exist
	 * @throws RepositoryException
	 */
	List<Borrowing> getPublicationsBorrowedByUser(String userUuid) throws RepositoryException;

	void updateBorrowing(Borrowing borrowing) throws RepositoryException;

}
