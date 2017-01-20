package edu.msg.bookland.server.repository.hibernate;

import edu.msg.bookland.server.repository.ArticleDAO;
import edu.msg.bookland.server.repository.AuthorDAO;
import edu.msg.bookland.server.repository.BorrowingDAO;
import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.PublicationDAO;
import edu.msg.bookland.server.repository.UserDAO;

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

	@Override
	public ArticleDAO getArticleDAO() {
		return new HibernateArticleDAO();
	}

}
