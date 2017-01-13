package edu.msg.bookland.repository.jdbc;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.msg.bookland.model.Author;
import edu.msg.bookland.model.Borrowing;
import edu.msg.bookland.repository.BorrowingDAO;
import edu.msg.bookland.repository.RepositoryException;

/**
 * Test the Borrowing data access object for CRUD operations.
 * 
 * @author Simo Zoltan
 * @author Solomon Jozsef
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BorrowingDAOTest {
	private final String userId = "123";
	private final String publicationId = "1234";
	private final BorrowingDAO borrowingDAO = new JDBCBorrowingDAO();

	private Borrowing createBorrowing() {
		Borrowing b = new Borrowing();
		b.setUserId(userId);
		b.setPublicationId(publicationId);
		b.setBorrowingDate(java.sql.Date.valueOf("2016-02-20"));
		b.setDeadline(java.sql.Date.valueOf("2016-02-21"));
		return b;
	}

	/**
	 * C from CRUD. Test Author insertion.
	 */
	@Test
	public void test0insert() {
		try {
			borrowingDAO.insertBorrowing(createBorrowing());
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could no insert Borrowing.");
		}
	}

	/**
	 * R from CRUD. Test select all Borrowings for a users id. Check if list is
	 * not empty.
	 */
	@Test
	public void test1getPublicationsById() {
		try {
			List<Borrowing> list = borrowingDAO.getPublicationsBorrowedByUser(userId);
			assertTrue(!list.isEmpty());
		} catch (RepositoryException e) {
			Assert.fail("Could no get users.");
		}
	}

	/**
	 * D from CRUD. Test Borrowing delete.
	 */
	@Test
	public void test2delete() {
		try {
			borrowingDAO.deleteBorrowing(createBorrowing());
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could not delete Borrowing.");
		}
	}
	
	@Test
	public void testUpdate() {
		Borrowing borrowing = new Borrowing();
		borrowing.setUserId("123");
		borrowing.setPublicationId("1234");
		borrowing.setDeadline(java.sql.Date.valueOf("2000-01-01"));
		borrowingDAO.updateBorrowing(borrowing);
		
	}

}
