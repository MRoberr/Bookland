package edu.msg.bookland.server.business_logic;

import edu.msg.bookland.server.business_logic.basic.BasicArticleBL;
import edu.msg.bookland.server.business_logic.basic.BasicBorrowingBL;
import edu.msg.bookland.server.model.Borrowing;

/**
 * Business Logic for BorrowingService
 * 
 * @author Sipos Terez
 */
public interface BorrowingBL {
	
	/**
	 * static method to get an instance
	 * 
	 * @return BasicArticleBL instance
	 */
	public static BorrowingBL getInstance() {
		return new BasicBorrowingBL();
	}
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
