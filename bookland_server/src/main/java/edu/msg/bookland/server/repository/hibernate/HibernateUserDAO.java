package edu.msg.bookland.server.repository.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import edu.msg.bookland.server.model.User;
import edu.msg.bookland.server.model.UserType;
import edu.msg.bookland.server.repository.RepositoryException;
import edu.msg.bookland.server.repository.UserDAO;

public class HibernateUserDAO implements UserDAO {
	private static final Logger LOGGER = Logger.getLogger(HibernateUserDAO.class);
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public List<User> getAllUsers() throws RepositoryException {

		List<User> users = new ArrayList<User>();

		// JPQL
		// TypedQuery<User> query = entityManager.createQuery("Select u from
		// User u", User.class);
		// List<User> users = query.getResultList();
		// return users;

		// Criteria
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			
			Root<User> from = criteriaQuery.from(User.class);
			criteriaQuery.select(from);
			
			TypedQuery<User> q = entityManager.createQuery(criteriaQuery);
			users = q.getResultList();
			LOGGER.info("All user selected");
		} catch (PersistenceException e) {
			LOGGER.error("Could not query Users", e);
			throw new RepositoryException("Could not query Users", e);
		}

		return users;

	}

	@Override
	public void insertUser(User user) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			LOGGER.info("user inserted");
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not insert user.", e);
			throw new RepositoryException("Could not insert user.", e);
		}
		

	}

	@Override
	public void updateUser(User user) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			User userDB = entityManager.find(User.class, user.getUUID());
			userDB.setEmail(user.getEmail());
			userDB.setLoyaltyIndex(user.getLoyaltyIndex());
			userDB.setName(user.getName());
			userDB.setUserType(user.getUserType());
			userDB.setPassword(user.getPassword());
			entityManager.getTransaction().commit();
			LOGGER.info("user updated");
		} catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not update user. ", e);
			throw new RepositoryException("Could not update user. ", e);
		}
		

	}

	@Override
	public void deleteUser(User user) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			User userDB = entityManager.find(User.class, user.getUUID());
			entityManager.remove(userDB);
			entityManager.getTransaction().commit();
			LOGGER.info("Deleted user");
		} catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not delete a user. ", e);
			throw new RepositoryException("Could not delete a user. ", e);
		}
		

	}

	@Override
	public void updateUserWithoutPassword(User user) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			User userDB = entityManager.find(User.class, user.getUUID());
			userDB.setEmail(user.getEmail());
			userDB.setLoyaltyIndex(user.getLoyaltyIndex());
			userDB.setName(user.getName());
			userDB.setUserType(user.getUserType());
			entityManager.getTransaction().commit();
			LOGGER.info("Updated user without password");
		} catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not update user without password. ", e);
			throw new RepositoryException("Could not update user without password. ", e);
		}
		
	}

	@Override
	public UserType login(String userName, String password) throws RepositoryException {
		User user = null;
		try {
			TypedQuery<User> query = entityManager
					.createQuery("Select u " + "from User u where u.name LIKE :n and" + " u.password LIKE :p", User.class)
					.setParameter("n", userName).setParameter("p", password);
			user = query.getSingleResult();
			
		} catch(PersistenceException e) {
			LOGGER.error("Could not loged user. ", e);
			throw new RepositoryException("Could not loged user.", e);
		}
		return user.getUserType();
	}


	@Override
	public User getUserById(String id) throws RepositoryException {
		User user = null;
	    try {
	    	user = entityManager.find(User.class, id);
	    	LOGGER.info("User retrieved by id.");
	    } catch(PersistenceException e) {
	    	LOGGER.error("Could not retrieve User by id! ", e);
			throw new RepositoryException("Could not retrieve User by id!", e);
	    }
		
		return user;
	}

	@Override
	public List<User> searchUserByName(String name) throws RepositoryException {
		List<User> users = null;
		try  {
			TypedQuery<User> query = entityManager.createQuery("Select u " + "from User u where u.name LIKE :n", User.class)
					.setParameter("n", "%" + name + "%");
			users = query.getResultList();
			LOGGER.info("Retrieved user list"+users.size());
			} catch(PersistenceException e) {
			LOGGER.error("Could not retrieve user list", e);
			throw new RepositoryException("Could not retrieve user list", e);
		}
		
		return users;
	}

	@Override
	public void setUserLoyaltyIndex(String uuid) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			User userDB = entityManager.find(User.class, uuid);	
			userDB.setLoyaltyIndex(userDB.getLoyaltyIndex()-1);
			entityManager.getTransaction().commit();
			LOGGER.info("User's loyalty decreased.");
		} catch(PersistenceException e) {
			LOGGER.error("Could not decrease user's loyalty", e);
			throw new RepositoryException("Could not decrease user's loyalty", e);
		}
		
	}

}
