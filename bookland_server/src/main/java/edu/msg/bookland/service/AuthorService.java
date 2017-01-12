package edu.msg.bookland.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.bookland.model.Author;
import edu.msg.bookland.rmi.AuthorServiceRmi;

public class AuthorService extends UnicastRemoteObject implements AuthorServiceRmi {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8599068126799700304L;

	public AuthorService() throws RemoteException {
		super();
	}

	@Override
	public List<Author> getAllAuthors() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertAuthor(Author author) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAuthor(Author author) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAuthor(Author author) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Author getAuthorByUUID(String uuid) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
