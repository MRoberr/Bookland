package edu.msg.bookland.server.busines_logic;

import java.util.List;

import edu.msg.bookland.common.model.Borrowing;

/**
 * Defines database methods for BORROWING Model
 * 
 * @author Jozsef Solomon
 */
public interface BorrowingLogic {
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
	 * @param borrowing
	 * @throws BusinesLogicException
	 */
	void deleteBorrowing(Borrowing borrowing) throws BusinesLogicException;

	/**
	 * This method define select for {@link Borrowing} with conditon
	 * 
	 * @param userUuid
	 * @return list of Borrowing if exist
	 * @throws BusinesLogicException
	 */
	List<Borrowing> getPublicationsBorrowedByUser(String userUuid) throws BusinesLogicException;

	void updateBorrowing(Borrowing borrowing) throws BusinesLogicException;

}
