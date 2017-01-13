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
 * @return List of all Authors from DB
 * @throws RepositoryException
 */
	List<Author> getAllAuthors() throws RepositoryException;
/**
 * 
 * @param author
 * @throws RepositoryException
 */
	void insertAuthor(Author author) throws RepositoryException;

	void updateAuthor(Author author) throws RepositoryException;

	void deleteAuthor(Author author) throws RepositoryException;

	Author getAuthorByUuid(String uuId) throws RepositoryException;
}
