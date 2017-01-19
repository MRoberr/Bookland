package edu.msg.bookland.repository;

import java.util.List;

import edu.msg.bookland.common.model.BorrowingDTO;

/**
 * Defines database methods for BORROWING Model
 * 
 * @author Jozsef Solomon
 */
public interface BorrowingDAO {
	/**
	 * This method define insert for {@link BorrowingDTO}
	 * 
	 * @param borrowing
	 * @throws RepositoryException
	 */
	void insertBorrowing(BorrowingDTO borrowing) throws RepositoryException;

	/**
	 * This method define delete for {@link BorrowingDTO}
	 * 
	 * @param borrowing
	 * @throws RepositoryException
	 */
	void deleteBorrowing(BorrowingDTO borrowing) throws RepositoryException;

	/**
	 * This method define select for {@link BorrowingDTO} with conditon
	 * 
	 * @param userUuid
	 * @return list of Borrowing if exist
	 * @throws RepositoryException
	 */
	List<BorrowingDTO> getPublicationsBorrowedByUser(String userUuid) throws RepositoryException;

	void updateBorrowing(BorrowingDTO borrowing) throws RepositoryException;

}
