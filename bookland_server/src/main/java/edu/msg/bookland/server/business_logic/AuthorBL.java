package edu.msg.bookland.server.business_logic;

import java.util.List;

import edu.msg.bookland.server.model.Author;

/**
 * Defines database methods for AUTHOR Model
 * 
 * @author Jozsef Solomon
 */
public interface AuthorBL {
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
	 * @throws BusinesLogicException
	 */
	void deleteAuthor(Author author) throws BusinesLogicException;

	/**
	 * This method define select with condition for {@link Author}
	 * 
	 * @param uuId-Author uuid
	 * @return Author if exist
	 * @throws BusinesLogicException
	 */
	List<Author> searchAuthor(String name) throws BusinesLogicException;
}
