package edu.msg.bookland.repository;

import edu.msg.bookland.repository.jdbc.JDBCDAOFactory;

public abstract class DAOFactory {
	public static DAOFactory getDAOFactory() {
		return new JDBCDAOFactory();
	}

	public abstract UserDAO getUserDAO();
	
	public abstract AuthorDAO getAuthorDAO();
	
	public abstract BorrowingDAO getBorrowingDAO();
}