package edu.msg.bookland.server.business_logic;

import java.util.List;

import edu.msg.bookland.server.business_logic.basic.BasicAuthorBL;
import edu.msg.bookland.server.model.Author;

/**
 * Business Logic for AuthorService
 * 
 * @author Sipos Terez
 */
public interface ArticleBL {

	/**
	 * static method to get an instance
	 * 
	 * @return BasicAuthorBL instance
	 */
	public static AuthorBL getInstance() {
		return new BasicAuthorBL();
	}

	/**
	 * This method define select all for {@link Author}
	 * 
	 * @return List of all Authors from DB
	 * @throws BusinesLogicException
	 */
	List<Author> getAllAuthors() throws BusinesLogicException;

	/**
	 * This method define insert for {@link Author}
	 * 
	 * @param author
	 * @throws BusinesLogicException
	 */
	void insertAuthor(Author author) throws BusinesLogicException;

	/**
	 * This method define update for {@link Author}
	 * 
	 * @param author
	 * @throws BusinesLogicException
	 */
	void updateAuthor(Author author) throws BusinesLogicException;

	/**
	 * This method define delete for {@link Author}
	 * 
	 * @param author
	 *            id
	 * @throws BusinesLogicException
	 */
	void deleteAuthor(String id) throws BusinesLogicException;

	/**
	 * This method define select with condition for {@link Author}
	 * 
	 * @param Author
	 *            name
	 * @return Author List if exist
	 * @throws BusinesLogicException
	 */
	List<Author> searchAuthor(String name) throws BusinesLogicException;
}
