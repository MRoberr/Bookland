package edu.msg.bookland.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.AuthorDTO;
import edu.msg.bookland.common.rmi.AuthorServiceRmi;
import edu.msg.bookland.server.repository.AuthorDAO;
import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.RepositoryException;
import edu.msg.bookland.server.repository.jdbc.JDBCUserDAO;

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

	@Override
	public List<AuthorDTO> getAllAuthors() throws RemoteException {
		try {
			return authorDAO.getAllAuthors();
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get all authors");
			return null;
		}

	}

	@Override
	public boolean insertAuthor(AuthorDTO authorDTO) throws RemoteException {
		try {
			authorDAO.insertAuthor(authorDTO);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to insert author");
			return false;
		}
	}


	@Override
	public boolean updateAuthor(AuthorDTO authorDTO) throws RemoteException {
		try {
			authorDAO.updateAuthor(authorDTO);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to update author");
			return false;
		}
	}

	@Override
	public boolean deleteAuthor(AuthorDTO authorDTO) throws RemoteException {
		// publicationService-getPublicationsByAuthorUUID
		// if return null can delete
		try {
			authorDAO.deleteAuthor(authorDTO);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to delete author");
			return false;
		}
	}

	
	@Override
	public List<AuthorDTO> searchAuthor(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
