package edu.msg.bookland.repository;

import java.util.List;

import edu.msg.bookland.model.Author;

public interface AuthorDAO {
	
	List<Author> getAllAuthors() throws RepositoryException;

	Author insertAuthor(Author author) throws RepositoryException;

	void updateAuthor(Author author) throws RepositoryException;

	void deleteAuthor(Author author) throws RepositoryException;
	
	Author getAuthorByUuid(String uuId) throws RepositoryException;
}
