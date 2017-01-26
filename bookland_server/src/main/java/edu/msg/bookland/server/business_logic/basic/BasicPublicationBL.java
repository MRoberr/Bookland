package edu.msg.bookland.server.business_logic.basic;

import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.business_logic.PublicationBL;
import edu.msg.bookland.server.model.Book;
import edu.msg.bookland.server.model.Magazine;
import edu.msg.bookland.server.model.Newspaper;
import edu.msg.bookland.server.model.Publication;
import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.PublicationDAO;
import edu.msg.bookland.server.repository.RepositoryException;

/**
 * Implementation of {@link PublicationBL}
 * 
 * @author Sipos Terez
 *
 */
public class BasicPublicationBL implements PublicationBL {
	private static final Logger LOGGER = Logger.getLogger(BasicPublicationBL.class);
	private PublicationDAO publicationDAO = DAOFactory.getDAOFactory().getPublicationDAO();

	/*
	 * @see edu.msg.bookland.server.business_logic.PublicationBL#getAllBooks()
	 */
	@Override
	public List<Book> getAllBooks() throws BusinesLogicException {
		try {
			return publicationDAO.getAllBooks();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all Books!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#getAllMagazines()
	 */
	@Override
	public List<Magazine> getAllMagazines() throws BusinesLogicException {
		try {
			return publicationDAO.getAllMagazines();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all Magazines!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#getAllNewspapers()
	 */
	@Override
	public List<Newspaper> getAllNewspapers() throws BusinesLogicException {
		try {
			return publicationDAO.getAllNewspapers();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all Newspapers!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#getAllPublications()
	 */
	@Override
	public List<Publication> getAllPublications() throws BusinesLogicException {
		try {
			return publicationDAO.getAllPublications();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all Publications!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#insertBook(edu.msg.
	 * bookland.server.model.Book)
	 */
	@Override
	public void insertBook(Book book) throws BusinesLogicException {
		try {
			publicationDAO.insertBook(book);
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert Book!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#insertMagazine(edu.
	 * msg.bookland.server.model.Magazine)
	 */
	@Override
	public void insertMagazine(Magazine magazine) throws BusinesLogicException {
		try {
			publicationDAO.insertMagazine(magazine);
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert Magazine!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#insertNewspaper(edu.
	 * msg.bookland.server.model.Newspaper)
	 */
	@Override
	public void insertNewspaper(Newspaper newspaper) throws BusinesLogicException {
		try {
			publicationDAO.insertNewspaper(newspaper);
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert Newspaper!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#updateBook(edu.msg.
	 * bookland.server.model.Book)
	 */
	@Override
	public void updateBook(Book book) throws BusinesLogicException {
		try {
			publicationDAO.updatePublication(book);
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Book!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#updateMagazine(edu.
	 * msg.bookland.server.model.Magazine)
	 */
	@Override
	public void updateMagazine(Magazine magazine) throws BusinesLogicException {
		try {
			publicationDAO.updatePublication(magazine);
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Magazine!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#updateNewspaper(edu.
	 * msg.bookland.server.model.Newspaper)
	 */
	@Override
	public void updateNewspaper(Newspaper newspaper) throws BusinesLogicException {
		try {
			publicationDAO.updatePublication(newspaper);
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Newspaper!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * Can't delete Publication if it is borrowed by someone.
	 * 
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#deletePublication(
	 * java.lang.String)
	 */
	@Override
	public void deletePublication(String id) throws BusinesLogicException {
		try {
//			if (publicationDAO.getPublicationByUuid(id).getBorrow().isEmpty()) {
				publicationDAO.deletePublication(id);
//			} else {
//				LOGGER.error("This publication is borrowed by someone, you Can't delete Publication!");
//				throw new BusinesLogicException(
//						"This publication is borrowed by someone, you Can't delete Publication!");
//			}
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Book!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#searchPublication(
	 * java.lang.String)
	 */
	@Override
	public List<Publication> searchPublication(String title) throws BusinesLogicException {
		try {
			return publicationDAO.searchPublication(title);
		} catch (RepositoryException e) {
			LOGGER.error("Can't get Publication!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#searchBook(java.lang
	 * .String)
	 */
	@Override
	public List<Book> searchBook(String title) throws BusinesLogicException {
		try {
			return publicationDAO.searchBook(title);
		} catch (RepositoryException e) {
			LOGGER.error("Can't get Book!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#searchMagazine(java.
	 * lang.String)
	 */
	@Override
	public List<Magazine> searchMagazine(String title) throws BusinesLogicException {
		try {
			return publicationDAO.searchMagazine(title);
		} catch (RepositoryException e) {
			LOGGER.error("Can't get Magazine!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.PublicationBL#searchNewspaper(java
	 * .lang.String)
	 */
	@Override
	public List<Newspaper> searchNewspaper(String title) throws BusinesLogicException {
		try {
			return publicationDAO.searchNewspaper(title);
		} catch (RepositoryException e) {
			LOGGER.error("Can't get Newspaper!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

}
