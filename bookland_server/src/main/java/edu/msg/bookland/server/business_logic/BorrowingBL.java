package edu.msg.bookland.server.business_logic;

import edu.msg.bookland.server.model.Borrowing;

/**
 * Business Logic for BorrowingService
 * 
 * @author Sipos Terez
 */
public interface BorrowingBL {
	/**
	 * This method define insert for {@link Borrowing}
	 * 
	 * @param borrowing
	 * @throws BusinesLogicException
	 */
	void insertBorrowing(Borrowing borrowing) throws BusinesLogicException;

	/**
	 * This method define delete for {@link Borrowing}
	 * 
	 * @param user uuid
	 * @param publication uuid
	 * @throws BusinesLogicException
	 */
	void deleteBorrowing(String userId, String publicationId) throws BusinesLogicException;

	
	//void updateBorrowing(Borrowing borrowing) throws BusinesLogicException;

}
