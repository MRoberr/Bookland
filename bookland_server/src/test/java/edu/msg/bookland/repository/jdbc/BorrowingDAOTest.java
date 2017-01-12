package edu.msg.bookland.repository.jdbc;

import java.util.List;

import org.junit.Test;

import edu.msg.bookland.model.Borrowing;

public class BorrowingDAOTest {
	JDBCBorrowingDAO borrowingDAO = new JDBCBorrowingDAO();

	@Test
	public void insert() {
		Borrowing borrowing = new Borrowing();
		borrowing.setUserId("123");
		borrowing.setPublicationId("1234");
		borrowing.setBorrowingDate(java.sql.Date.valueOf("2016-02-20"));
		borrowing.setDeadline(java.sql.Date.valueOf("2016-02-21"));
		borrowingDAO.insertBorrowing(borrowing);
	}

	@Test
	public void delete() {
		Borrowing borrowing = new Borrowing();
		borrowing.setUserId("123");
		borrowing.setPublicationId("1234");
		borrowingDAO.deleteBorrowing(borrowing);
	}

	@Test
	public void getPublicationsById() {
		List<Borrowing> list = borrowingDAO.getPublicationsBorrowedByUser("123");
		for(Borrowing b : list) {
			System.out.println(b);
		}
	}

}
