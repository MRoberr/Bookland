package edu.msg.bookland.server.repository.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import edu.msg.bookland.server.model.Borrowing;
import edu.msg.bookland.server.repository.BorrowingDAO;
import edu.msg.bookland.server.repository.RepositoryException;

public class HibernateBorrowingDAO implements BorrowingDAO {

	private static final Logger LOGGER = Logger.getLogger(HibernateUserDAO.class);
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public void insertBorrowing(Borrowing borrowing) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(borrowing);
			entityManager.getTransaction().commit();
			LOGGER.info("borrowing inserted");
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not insert borrowing. ", e);
			throw new RepositoryException("Could not insert borrowing. ", e);
		}

	}

	@Override
	public void deleteBorrowing(String userId, String publicationId)throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			TypedQuery<Borrowing> query = entityManager.createQuery(
					"SELECT b FROM Borrowing b WHERE b.userId in :idu " + "and b.publicationId in :idp",
					Borrowing.class);
			query.setParameter("idu", userId);
			query.setParameter("idp", publicationId);
			Borrowing borrowingDB = query.getSingleResult();
			entityManager.remove(borrowingDB);
			entityManager.getTransaction().commit();
			LOGGER.info("Deleted borrowing");
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not delete a borrowing. ", e);
			throw new RepositoryException("Could not delete a borrowing. ", e);
		}

	}

	public void updateBorrowing(Borrowing borrowing) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			TypedQuery<Borrowing> query = entityManager.createQuery(
					"SELECT b FROM Borrowing b WHERE b.userId in :idu " + "and b.publicationId in :idp",
					Borrowing.class);
			query.setParameter("idu", borrowing.getUserId());
			query.setParameter("idp", borrowing.getPublicationId());
			Borrowing borrowingDB = query.getSingleResult();
			borrowingDB.setDeadline(borrowing.getDeadline());

			entityManager.getTransaction().commit();
			LOGGER.info("Updated borrowing");
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not update a borrowing. ", e);
			throw new RepositoryException("Could not update a borrowing. ", e);
		}

	}

	

	@Override
	public Borrowing getBorrowById(String userId, String publicationId) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
