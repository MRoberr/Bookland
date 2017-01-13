package edu.msg.bookland.repository.jdbc;

import edu.msg.bookland.repository.AuthorDAO;
import edu.msg.bookland.repository.BorrowingDAO;
import edu.msg.bookland.repository.DAOFactory;
import edu.msg.bookland.repository.UserDAO;

/**
 * Implements methods of {@link DAOFactory} every method return a JDBC specific
 * DAO
 * 
 * @author Jozsef Solomon
 */
public class JDBCDAOFactory extends DAOFactory {
	/*
	 * @see edu.msg.bookland.repository.DAOFactory#getUserDAO()
	 */
	@Override
	public UserDAO getUserDAO() {
		return new JdbcUserDAO();
	}
/*
 * @see edu.msg.bookland.repository.DAOFactory#getAuthorDAO()
 */
	@Override
	public AuthorDAO getAuthorDAO() {
		return new JDBCAuthorDAO();
	}
/*
 * @see edu.msg.bookland.repository.DAOFactory#getBorrowingDAO()
 */
	@Override
	public BorrowingDAO getBorrowingDAO() {
		return new JDBCBorrowingDAO();
	}

}
