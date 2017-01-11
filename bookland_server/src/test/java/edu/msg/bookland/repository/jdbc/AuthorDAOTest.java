package edu.msg.bookland.repository.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.msg.bookland.model.Author;

public class AuthorDAOTest {
	JDBCAuthorDAO authorDAO = new JDBCAuthorDAO();

	@Test
	public void getAllAuthors() {
		List<Author> authors = authorDAO.getAllAuthors();
		assertTrue(!authors.isEmpty());
	}

}
