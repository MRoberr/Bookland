package edu.msg.bookland.repository.jdbc;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;

import edu.msg.bookland.common.model.AuthorDTO;
import edu.msg.bookland.common.model.BorrowingDTO;
import edu.msg.bookland.server.repository.BorrowingDAO;
import edu.msg.bookland.server.repository.RepositoryException;
import edu.msg.bookland.server.repository.hibernate.HibernateBorrowingDAO;

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
	private final BorrowingDAO borrowingDAO = new HibernateBorrowingDAO();

	private BorrowingDTO createBorrowing() {
		BorrowingDTO b = new BorrowingDTO();
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
			List<BorrowingDTO> list = borrowingDAO.getPublicationsBorrowedByUser(userId);
			assertTrue(!list.isEmpty());
			for (BorrowingDTO b : list) {
				System.out.println(b);
			}
		} catch (RepositoryException e) {
			Assert.fail("Could no get users.");
		}
	}

	/**
	 * U from CRUD. Test Borrowing update.
	 */
	@Test
	public void test2Update() {
		try {
			BorrowingDTO borrowing = createBorrowing();
			borrowing.setDeadline(java.sql.Date.valueOf("2000-01-01"));
			borrowingDAO.updateBorrowing(borrowing);
		} catch (RepositoryException e) {
			Assert.fail("Could no insert Borrowing.");
		}
	}

	/**
	 * D from CRUD. Test Borrowing delete.
	 */
	@Test
	public void test3delete() {
		try {
			borrowingDAO.deleteBorrowing(createBorrowing());
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could not delete Borrowing.");
		}
	}

}
