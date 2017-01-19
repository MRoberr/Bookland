package edu.msg.bookland.server.repository;

import edu.msg.bookland.server.repository.hibernate.HibernateDAOFactory;

/**
 * Abstract DAO defines getter for all DAO
 * 
 * @author Jozsef Solomon
 * @author Terez Sipos
 *
 */
public abstract class DAOFactory {
	/**
	 * Implement getter for {@link DAOFactory}
	 * 
	 * @return one specific DAO Factory
	 */
	public static DAOFactory getDAOFactory() {
		return new HibernateDAOFactory();
	}

	/**
	 * Define getter for {@link UserDAO}
	 * 
	 * @return UserDAO
	 */
	public abstract UserDAO getUserDAO();

	/**
	 * Define getter for {@link AuthorDAO}
	 * 
	 * @return AuthorDAO
	 */
	public abstract AuthorDAO getAuthorDAO();

	/**
	 * Define getter for {@link BorrowingDAO}
	 * 
	 * @return BorrowingDAO
	 */
	public abstract BorrowingDAO getBorrowingDAO();

	/**
	 * Define getter for {@link PublicationDAO}
	 * 
	 * @return PublicationDAO
	 */
	public abstract PublicationDAO getPublicationDAO();

}