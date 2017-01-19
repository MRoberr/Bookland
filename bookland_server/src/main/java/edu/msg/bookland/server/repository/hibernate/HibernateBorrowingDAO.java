package edu.msg.bookland.server.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.BorrowingDTO;
import edu.msg.bookland.server.repository.BorrowingDAO;
import edu.msg.bookland.server.repository.RepositoryException;

public class HibernateBorrowingDAO implements BorrowingDAO {

	private static final Logger LOGGER = Logger.getLogger(HibernateUserDAO.class);
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public void insertBorrowing(BorrowingDTO borrowing) throws RepositoryException {
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
	public void deleteBorrowing(BorrowingDTO borrowing) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			TypedQuery<BorrowingDTO> query = entityManager.createQuery(
					"SELECT b FROM Borrowing b WHERE b.userId in :idu " + "and b.publicationId in :idp",
					BorrowingDTO.class);
			query.setParameter("idu", borrowing.getUserId());
			query.setParameter("idp", borrowing.getPublicationId());
			BorrowingDTO borrowingDB = query.getSingleResult();
			entityManager.remove(borrowingDB);
			entityManager.getTransaction().commit();
			LOGGER.info("Deleted borrowing");
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not delete a borrowing. ", e);
			throw new RepositoryException("Could not delete a borrowing. ", e);
		}

	}

	@Override
	public List<BorrowingDTO> getPublicationsBorrowedByUser(String userUuid) throws RepositoryException {
		List<BorrowingDTO> borrowingList;
		try {

			TypedQuery<BorrowingDTO> query = entityManager.createQuery("SELECT b FROM Borrowing b WHERE b.userId in :idu ",
					BorrowingDTO.class);
			query.setParameter("idu", userUuid);
			borrowingList = query.getResultList();

			LOGGER.info("Retrieved borrowings by userID");
		} catch (PersistenceException e) {
			LOGGER.error("Could not retrieve borrowings by userID.", e);
			throw new RepositoryException("Could not retrieve borrowings by userID.", e);
		}
		return borrowingList;
	}

	@Override
	public void updateBorrowing(BorrowingDTO borrowing) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			TypedQuery<BorrowingDTO> query = entityManager.createQuery(
					"SELECT b FROM Borrowing b WHERE b.userId in :idu " + "and b.publicationId in :idp",
					BorrowingDTO.class);
			query.setParameter("idu", borrowing.getUserId());
			query.setParameter("idp", borrowing.getPublicationId());
			BorrowingDTO borrowingDB = query.getSingleResult();
			borrowingDB.setDeadline(borrowing.getDeadline());
			
			entityManager.getTransaction().commit();
			LOGGER.info("Updated borrowing");
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not update a borrowing. ", e);
			throw new RepositoryException("Could not update a borrowing. ", e);
		}

	}

}
