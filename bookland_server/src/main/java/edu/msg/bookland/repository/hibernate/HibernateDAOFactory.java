package edu.msg.bookland.repository.hibernate;

import edu.msg.bookland.repository.AuthorDAO;
import edu.msg.bookland.repository.BorrowingDAO;
import edu.msg.bookland.repository.DAOFactory;
import edu.msg.bookland.repository.PublicationDAO;
import edu.msg.bookland.repository.UserDAO;

public class HibernateDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new HibernateUserDAO();
	}

	@Override
	public AuthorDAO getAuthorDAO() {
		return new HibernateAuthorDAO();
	}

	@Override
	public BorrowingDAO getBorrowingDAO() {
		return new HibernateBorrowingDAO();
	}

	@Override
	public PublicationDAO getPublicationDAO() {
		return new HibernatePublicationDAO();
	}

}
