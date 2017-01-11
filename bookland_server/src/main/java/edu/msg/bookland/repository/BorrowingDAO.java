package edu.msg.bookland.repository;

import edu.msg.bookland.model.Borrowing;

public interface BorrowingDAO {
		
	void insertBorrowing(Borrowing borrowing) throws RepositoryException;
	
	void deleteBorrowing(Borrowing borrowing) throws RepositoryException;
	
}
