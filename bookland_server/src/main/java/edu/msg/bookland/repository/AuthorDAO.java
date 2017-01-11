package edu.msg.bookland.repository;

import java.util.List;

import edu.msg.bookland.model.Author;

public interface AuthorDAO {
	
	List<Author> getAllAuthors();

	Author insertUser(Author author);

	void updateAuthor(Author author);

	void deleteAuthor(Author author);
	
	Author getAuthorByUuid(String uuId);
}
