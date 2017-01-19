package edu.msg.bookland.repository;

import java.util.List;

import edu.msg.bookland.common.model.AuthorDTO;

/**
 * Defines database methods for AUTHOR Model
 * 
 * @author Jozsef Solomon
 */
public interface AuthorDAO {
	/**
	 * This method define select all for {@link AuthorDTO}
	 * 
	 * @return List of all Authors from DB
	 * @throws RepositoryException
	 */
	List<AuthorDTO> getAllAuthors() throws RepositoryException;

	/**
	 * This method define insert for {@link AuthorDTO}
	 * 
	 * @param author
	 * @throws RepositoryException
	 */
	void insertAuthor(AuthorDTO author) throws RepositoryException;

	/**
	 * This method define update for {@link AuthorDTO}
	 * 
	 * @param author
	 * @throws RepositoryException
	 */
	void updateAuthor(AuthorDTO author) throws RepositoryException;

	/**
	 * This method define delete for {@link AuthorDTO}
	 * 
	 * @param author
	 * @throws RepositoryException
	 */
	void deleteAuthor(AuthorDTO author) throws RepositoryException;

	/**
	 * This method define select with condition for {@link AuthorDTO}
	 * 
	 * @param uuId-Author uuid
	 * @return Author if exist
	 * @throws RepositoryException
	 */
	AuthorDTO getAuthorByUuid(String uuId) throws RepositoryException;
}
