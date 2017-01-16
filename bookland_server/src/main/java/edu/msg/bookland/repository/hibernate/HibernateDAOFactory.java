package edu.msg.bookland.repository.hibernate;

import edu.msg.bookland.repository.AuthorDAO;
import edu.msg.bookland.repository.BorrowingDAO;
import edu.msg.bookland.repository.DAOFactory;
import edu.msg.bookland.repository.PublicationDAO;
import edu.msg.bookland.repository.UserDAO;

public class HibernateDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthorDAO getAuthorDAO() {
		return new HibernateAuthorDAO();
	}

	@Override
	public BorrowingDAO getBorrowingDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublicationDAO getPublicationDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
