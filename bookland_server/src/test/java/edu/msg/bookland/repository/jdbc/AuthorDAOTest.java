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

	@Test
	public void instertAuthor() {
		Author author = new Author();
		author.setUUID("12345");
		author.setName("Pista");
		authorDAO.insertAuthor(author);

	}

	@Test
	public void updateAuthor() {
		Author author = new Author();
		author.setUUID("12345");
		author.setName("Zolika");
		authorDAO.updateAuthor(author);
	}

	@Test
	public void deleteAuthor() {
		Author author = new Author();
		author.setUUID("12345");
		author.setName("Zolika");
		authorDAO.deleteAuthor(author);
	}

	@Test
	public void getAuthorById() {
		Author author = new Author();
		author.setUUID("123456");
		author.setName("Zolika");
		authorDAO.insertAuthor(author);
		Author author2 = authorDAO.getAuthorByUuid(author.getUUID());
		assertTrue(author.getName().equals(author2.getName()));

	}

}
