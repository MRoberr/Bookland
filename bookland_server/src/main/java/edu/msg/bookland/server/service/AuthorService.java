package edu.msg.bookland.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.AuthorDTO;
import edu.msg.bookland.common.model.ServiceException;
import edu.msg.bookland.common.rmi.AuthorServiceRmi;
import edu.msg.bookland.server.business_logic.AuthorBL;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.Author;

/**
 * Implement methods of UserServiceRmi. Call methods of DAO and contains
 * Business Logic
 * 
 * @author Terez Sipos
 * @author Jozsef Solomon
 */
public class AuthorService extends UnicastRemoteObject implements AuthorServiceRmi {

	private static final long serialVersionUID = -8599068126799700304L;

	private static final Logger LOGGER = Logger.getLogger(AuthorService.class);
	private AuthorBL authorBL;

	/**
	 * initialize authorDAO
	 * 
	 * @throw ServiceException if can't get a DAO
	 */
	public AuthorService() throws RemoteException {
		authorBL = AuthorBL.getInstance();
	}

	@Override
	public List<AuthorDTO> getAllAuthors() throws RemoteException {
		List<Author> authors;
		try {
			authors = authorBL.getAllAuthors();
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return MappingService.authorsToDTO(authors);
	}

	@Override
	public void insertAuthor(AuthorDTO authorDTO) throws RemoteException {
		
		try {
			authorBL.insertAuthor(MappingService.DTOToAuthor(authorDTO));

		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateAuthor(AuthorDTO authorDTO) throws RemoteException {
		try {
			authorBL.updateAuthor(MappingService.DTOToAuthor(authorDTO));
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());	
		}
	}

	@Override
	public void deleteAuthor(String authorID) throws RemoteException {
		try {
			authorBL.deleteAuthor(authorID);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());	
		}
	}

	@Override
	public List<AuthorDTO> searchAuthor(String name) throws RemoteException {
		List<Author> authors;
		try {
			authors = authorBL.searchAuthor(name);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return MappingService.authorsToDTO(authors);
	}
}
