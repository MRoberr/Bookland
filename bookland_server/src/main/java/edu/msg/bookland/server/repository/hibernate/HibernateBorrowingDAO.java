package edu.msg.bookland.server.repository.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import edu.msg.bookland.server.model.Borrowing;
import edu.msg.bookland.server.model.Borrowing_;
import edu.msg.bookland.server.model.UserPublicationId;
import edu.msg.bookland.server.repository.BorrowingDAO;
import edu.msg.bookland.server.repository.RepositoryException;

public class HibernateBorrowingDAO implements BorrowingDAO {

	private static final Logger LOGGER = Logger.getLogger(HibernateUserDAO.class);
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private CriteriaBuilder builder;
	
	public HibernateBorrowingDAO() {

		builder = entityManager.getCriteriaBuilder();
	}

	@Override
	public void insertBorrowing(Borrowing borrowing) throws RepositoryException {
		try {
			entityManager.clear();
			entityManager.getTransaction().begin();
			entityManager.merge(borrowing);
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
			entityManager.clear();
			entityManager.getTransaction().begin();
			
			CriteriaDelete<Borrowing> delete = builder.createCriteriaDelete(Borrowing.class);
			
			Root<Borrowing> borrowRoot = delete.from(Borrowing.class);
			
			UserPublicationId pk = new UserPublicationId();
			pk.SetPublicationId(publicationId);
			pk.setUserId(userId);
			
			delete.where(builder.equal(borrowRoot.get(Borrowing_.userPublicationId), pk));
			
			entityManager.createQuery(delete).executeUpdate();
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
			entityManager.clear();
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
		
		try{
			entityManager.clear();
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Borrowing> borrow = builder.createQuery(Borrowing.class);
	
			Root<Borrowing> borrowRoot = borrow.from(Borrowing.class);
	
			UserPublicationId pk = new UserPublicationId();
			pk.SetPublicationId(publicationId);
			pk.setUserId(userId);
	
			borrow.select(borrowRoot);
			borrow.where(builder.and(builder.equal(borrowRoot.get(Borrowing_.userPublicationId), pk)));
			TypedQuery<Borrowing> borrowQuery = entityManager.createQuery(borrow);
			
			LOGGER.info("Borrow get was successful");
			
		return borrowQuery.getSingleResult();
		
		} catch(PersistenceException e) {
			
			LOGGER.error("Failed to get borrow by id", e);
			throw new RepositoryException("Failed to get borrow by id", e);
		}
	}
}
