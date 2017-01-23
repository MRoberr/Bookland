package edu.msg.bookland.server.business_logic.basic;

import java.sql.Date;
import java.time.LocalDate;

import org.apache.log4j.Logger;

import edu.msg.bookland.server.business_logic.BorrowingBL;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.Borrowing;
import edu.msg.bookland.server.repository.BorrowingDAO;
import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.PublicationDAO;
import edu.msg.bookland.server.repository.RepositoryException;
import edu.msg.bookland.server.repository.UserDAO;

/**
 * Implementation of {@link BorrowingBL}
 * 
 * @author Sipos Terez
 *
 */
public class BasicBorrowingBL implements BorrowingBL {
	private static final Logger LOGGER = Logger.getLogger(BasicBorrowingBL.class);
	private BorrowingDAO borrowingDAO = DAOFactory.getDAOFactory().getBorrowingDAO();

	/*
	 * If no copies left or user loyaltyIndex is 0 can't borrow
	 * 
	 * @see
	 * edu.msg.bookland.server.business_logic.BorrowingBL#insertBorrowing(edu.
	 * msg.bookland.server.model.Borrowing)
	 */
	@Override
	public void insertBorrowing(Borrowing borrowing) throws BusinesLogicException {
		try {
			PublicationDAO pubDAO = DAOFactory.getDAOFactory().getPublicationDAO();
			UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
			int copiesLeft = pubDAO.getPublicationByUuid(borrowing.getPublicationId()).getCopiesLeft();
			if (copiesLeft > 0) {
				int loyaltyIndex = userDAO.getUserById(borrowing.getUserId()).getLoyaltyIndex();
				if (loyaltyIndex > 0) {
					borrowingDAO.insertBorrowing(borrowing);
					pubDAO.decreaseCopiesLeft(borrowing.getPublicationId());
				} else {
					LOGGER.error("User Loyalty index is low, can't borrow Publication");
					throw new BusinesLogicException("User Loyalty index is low, can't borrow Publication");
				}
			} else {
				LOGGER.error("No copies left, can't borrow this Publication");
				throw new BusinesLogicException("No copies left, can't borrow this Publication");
			}
		} catch (RepositoryException e) {
			LOGGER.error("Can't Borrow publication!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * Before delete need to increase copiesLeft and if return to late decrease
	 * loyaltyIndex.
	 * 
	 * @see
	 * edu.msg.bookland.server.business_logic.BorrowingBL#deleteBorrowing(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void deleteBorrowing(String userId, String publicationId) throws BusinesLogicException {
		try {
			PublicationDAO pubDAO = DAOFactory.getDAOFactory().getPublicationDAO();
			UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
			pubDAO.increaseCopiesLeft(publicationId);
			if (borrowingDAO.getBorrowById(userId, publicationId).getDeadline().before(Date.valueOf(LocalDate.now()))) {
				userDAO.decreaseLoyaltyIndex(userId);
			}
			borrowingDAO.deleteBorrowing(userId, publicationId);
		} catch (RepositoryException e) {
			LOGGER.error("Can't Borrow publication!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

}
