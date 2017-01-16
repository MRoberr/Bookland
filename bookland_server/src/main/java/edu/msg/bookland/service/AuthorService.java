package edu.msg.bookland.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.model.Author;
import edu.msg.bookland.repository.AuthorDAO;
import edu.msg.bookland.repository.DAOFactory;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.repository.jdbc.JDBCUserDAO;
import edu.msg.bookland.rmi.AuthorServiceRmi;

/**
 * Implement methods of UserServiceRmi. Call methods of DAO and contains
 * Business Logic
 * 
 * @author Terez Sipos
 * @author Jozsef Solomon
 */
public class AuthorService extends UnicastRemoteObject implements AuthorServiceRmi {

	private static final long serialVersionUID = -8599068126799700304L;

	private static final Logger LOGGER = Logger.getLogger(JDBCUserDAO.class);
	private AuthorDAO authorDAO;

	/**
	 * initialize authorDAO
	 * 
	 * @throw ServiceException if can't get a DAO
	 */
	public AuthorService() throws RemoteException {
		authorDAO = DAOFactory.getDAOFactory().getAuthorDAO();

	}

	/*
	 * @see edu.msg.bookland.rmi.AuthorServiceRmi#getAllAuthors()
	 */
	@Override
	public List<Author> getAllAuthors() throws RemoteException {
		try {
			return authorDAO.getAllAuthors();
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get all authors");
			return null;
		}

	}

	/*
	 * @see
	 * edu.msg.bookland.rmi.AuthorServiceRmi#insertAuthor(edu.msg.bookland.model
	 * .Author)
	 */
	@Override
	public boolean insertAuthor(Author author) throws RemoteException {
		try {
			authorDAO.insertAuthor(author);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to insert author");
			return false;
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.rmi.AuthorServiceRmi#updateAuthor(edu.msg.bookland.model
	 * .Author)
	 */
	@Override
	public boolean updateAuthor(Author author) throws RemoteException {
		try {
			authorDAO.updateAuthor(author);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to update author");
			return false;
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.rmi.AuthorServiceRmi#deleteAuthor(edu.msg.bookland.model
	 * .Author)
	 */
	@Override
	public boolean deleteAuthor(Author author) throws RemoteException {
		// publicationService-getPublicationsByAuthorUUID
		// if return null can delete
		try {
			authorDAO.deleteAuthor(author);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to delete author");
			return false;
		}
	}

	/*
	 * @see edu.msg.bookland.rmi.AuthorServiceRmi#searchAuthor(java.lang.String)
	 */
	@Override
	public List<Author> searchAuthor(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
