package edu.msg.bookland.repository.jdbc;

import edu.msg.bookland.repository.AuthorDAO;
import edu.msg.bookland.repository.BorrowingDAO;
import edu.msg.bookland.repository.DAOFactory;
import edu.msg.bookland.repository.UserDAO;

public class JDBCDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new JdbcUserDAO();
	}

	@Override
	public AuthorDAO getAuthorDAO() {
		return new JDBCAuthorDAO();
	}

	@Override
	public BorrowingDAO getBorrowingDAO() {
		return new JDBCBorrowingDAO();
	}

}
