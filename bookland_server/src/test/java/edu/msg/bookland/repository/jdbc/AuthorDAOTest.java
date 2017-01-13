package edu.msg.bookland.repository.jdbc;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.msg.bookland.model.Author;
import edu.msg.bookland.repository.AuthorDAO;
import edu.msg.bookland.repository.RepositoryException;

/**
 * Test the Author data access object for CRUD operations.
 * 
 * @author Simo Zoltan
 * @author Solomon Jozsef
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorDAOTest {
	AuthorDAO authorDAO = new JDBCAuthorDAO();

	private Author createAuthor() {
		Author a = new Author();
		a.setUUID("37a97280-bb03-4b65-b84d-7602f6b6a86a");
		a.setName("testAuthorDAO");
		return a;
	}

	/**
	 * C from CRUD. Test Author insertion.
	 */
	@Test
	public void test0InstertAuthor() {
		try {
			authorDAO.insertAuthor(createAuthor());
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could no insert user.");
		}
	}

	/**
	 * R from CRUD. Test select all Authors list is not empty.
	 */
	@Test
	public void test1GetAllAuthors() {
		try {
			List<Author> authors = authorDAO.getAllAuthors();
			assertTrue(!authors.isEmpty());
		} catch (RepositoryException e) {
			Assert.fail("Could no get users.");
		}
	}

	/**
	 * R from CRUD. Test select Author with given id.
	 */
	@Test
	public void test2getAuthorById() {
		try {
			Assert.assertEquals(authorDAO.getAuthorByUuid("37a97280-bb03-4b65-b84d-7602f6b6a86a").getName(),
					createAuthor().getName());
		} catch (RepositoryException e) {
			Assert.fail("Could not search for Author.");
		}
	}

	/**
	 * U from CRUD. Test Author update.
	 */
	@Test
	public void test3updateAuthor() {
		Author a = createAuthor();
		a.setName("testAuthor");
		try {
			authorDAO.updateAuthor(a);
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could not update Author.");
		}
	}

	/**
	 * D from CRUD. Test Author delete.
	 */
	@Test
	public void test4deleteAuthor() {
		Author a = createAuthor();
		try {
			authorDAO.deleteAuthor(a);
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could not delete Author.");
		}
	}

}
