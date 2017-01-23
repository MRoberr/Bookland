package edu.msg.bookland.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import org.apache.log4j.Logger;


import edu.msg.bookland.common.model.BorrowingDTO;


import edu.msg.bookland.common.model.ServiceException;

import edu.msg.bookland.common.rmi.BorrowingServiceRmi;
import edu.msg.bookland.server.business_logic.BorrowingBL;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.Borrowing;


/**
 * Implement methods of UserServiceRmi. Call methods of DAO and contains
 * Business Logic
 * 
 * @author Terez Sipos
 * @author Jozsef Solomon
 */
public class BorrowingService extends UnicastRemoteObject implements BorrowingServiceRmi {

	private static final long serialVersionUID = 7771473351628284744L;
	private static final Logger LOGGER = Logger.getLogger(BorrowingService.class);
	private BorrowingBL borrowingBL;

	/**
	 * initialize borrowingDAO
	 * 
	 * @throw ServiceException if can't get a DAO 
	 */
	public BorrowingService() throws RemoteException {
		borrowingBL = BorrowingBL.getInstance();
	}

	@Override
	public void returnPublication(BorrowingDTO borrow) throws RemoteException, ServiceException {
	
		try {
			borrowingBL.deleteBorrowing(borrow.getUserId(), borrow.getPublicationId());
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		
	}

	@Override
	public void borrowPublication(BorrowingDTO borrow) throws RemoteException, ServiceException {
		Borrowing borrowing = MappingService.DTOToBorrow(borrow);
		try {
			borrowingBL.insertBorrowing(borrowing);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		
	}

	
}
