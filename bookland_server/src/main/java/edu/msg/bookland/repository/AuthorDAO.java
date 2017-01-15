package edu.msg.bookland.repository;

import java.util.List;

import edu.msg.bookland.model.Author;

/**
 * Defines database methods for AUTHOR Model
 * 
 * @author Jozsef Solomon
 */
public interface AuthorDAO {
	/**
	 * This method define select all for {@link Author}
	 * 
	 * @return List of all Authors from DB
	 * @throws RepositoryException
	 */
	List<Author> getAllAuthors() throws RepositoryException;

	/**
	 * This method define insert for {@link Author}
	 * 
	 * @param author
	 * @throws RepositoryException
	 */
	void insertAuthor(Author author) throws RepositoryException;

	/**
	 * This method define update for {@link Author}
	 * 
	 * @param author
	 * @throws RepositoryException
	 */
	void updateAuthor(Author author) throws RepositoryException;

	/**
	 * This method define delete for {@link Author}
	 * 
	 * @param author
	 * @throws RepositoryException
	 */
	void deleteAuthor(Author author) throws RepositoryException;

	/**
	 * This method define select with condition for {@link Author}
	 * 
	 * @param uuId-Author uuid
	 * @return Author if exist
	 * @throws RepositoryException
	 */
	Author getAuthorByUuid(String uuId) throws RepositoryException;
}
