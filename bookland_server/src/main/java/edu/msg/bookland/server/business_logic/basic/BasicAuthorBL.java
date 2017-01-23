package edu.msg.bookland.server.business_logic.basic;

import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.server.business_logic.AuthorBL;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.Author;
import edu.msg.bookland.server.repository.AuthorDAO;
import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.RepositoryException;

/**
 * Implementation of {@link AuthorBL}
 * 
 * @author Sipos Terez
 *
 */
public class BasicAuthorBL implements AuthorBL {
	private static final Logger LOGGER = Logger.getLogger(BasicAuthorBL.class);
	private AuthorDAO authorDAO = DAOFactory.getDAOFactory().getAuthorDAO();

	/*
	 * @see edu.msg.bookland.server.business_logic.AuthorBL#getAllAuthors()
	 */
	@Override
	public List<Author> getAllAuthors() throws BusinesLogicException {
		try {
			return authorDAO.getAllAuthors();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all Authors!");
			throw new BusinesLogicException("Can't get all Authors!", e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.AuthorBL#insertAuthor(edu.msg.
	 * bookland.server.model.Author)
	 */
	@Override
	public void insertAuthor(Author author) throws BusinesLogicException {
		try {
			authorDAO.insertAuthor(author);
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert Author!");
			throw new BusinesLogicException("Can't insert Author!", e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.AuthorBL#updateAuthor(edu.msg.
	 * bookland.server.model.Author)
	 */
	@Override
	public void updateAuthor(Author author) throws BusinesLogicException {
		try {
			authorDAO.updateAuthor(author);
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Author!");
			throw new BusinesLogicException("Can't update Author!", e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.AuthorBL#searchAuthor(java.lang.
	 * String)
	 */
	@Override
	public List<Author> searchAuthor(String name) throws BusinesLogicException {
		try {
			return authorDAO.searchAuthor(name);
		} catch (RepositoryException e) {
			LOGGER.error("Can't get Authors!");
			throw new BusinesLogicException("Can't get Authors!", e);
		}
	}

	/*
	 * Can't delete Author if have Publication
	 * 
	 * @see
	 * edu.msg.bookland.server.business_logic.AuthorBL#deleteAuthor(java.lang.
	 * String)
	 */
	@Override
	public void deleteAuthor(String id) throws BusinesLogicException {
		try {
			if (authorDAO.getAuthorByUuid(id).getPublicationAuthors().isEmpty()) {
				authorDAO.deleteAuthor(id);
			} else {
				LOGGER.error("This author have Pubications, you Can't delete Author!");
				throw new BusinesLogicException("This author have Pubications, you Can't delete Author!");
			}
		} catch (RepositoryException e) {
			LOGGER.error("Can't delete Author!");
			throw new BusinesLogicException("Can't delete Author!", e);
		}
	}

}
