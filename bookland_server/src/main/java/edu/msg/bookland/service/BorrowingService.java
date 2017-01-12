package edu.msg.bookland.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.bookland.model.Borrowing;
import edu.msg.bookland.rmi.BorrowingServiceRmi;

public class BorrowingService extends UnicastRemoteObject implements BorrowingServiceRmi {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7771473351628284744L;

	public BorrowingService() throws RemoteException {
		super();
	}

	@Override
	public List<Borrowing> getAllBorrows() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertBorrowing(Borrowing borrow) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBorrowing(Borrowing borrow) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBorrow(Borrowing borrow) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Borrowing getBorrowByUUID(String uuid) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean returnPublication(Borrowing borrow) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrowPublication(Borrowing borrow) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
