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

import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.server.repository.RepositoryException;
import edu.msg.bookland.server.repository.UserDAO;

public class HibernateUserDAO implements UserDAO {
	private static final Logger LOGGER = Logger.getLogger(HibernateUserDAO.class);
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public List<UserDTO> getAllUsers() throws RepositoryException {

		List<UserDTO> users = new ArrayList<UserDTO>();

		// JPQL
		// TypedQuery<User> query = entityManager.createQuery("Select u from
		// User u", User.class);
		// List<User> users = query.getResultList();
		// return users;

		// Criteria
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<UserDTO> criteriaQuery = criteriaBuilder.createQuery(UserDTO.class);
			
			Root<UserDTO> from = criteriaQuery.from(UserDTO.class);
			criteriaQuery.select(from);
			
			TypedQuery<UserDTO> q = entityManager.createQuery(criteriaQuery);
			users = q.getResultList();
			LOGGER.info("All user selected");
		} catch (PersistenceException e) {
			LOGGER.error("Could not query Users", e);
			throw new RepositoryException("Could not query Users", e);
		}

		return users;

	}

	@Override
	public void insertUser(UserDTO user) throws RepositoryException {
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
	public void updateUser(UserDTO user) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			UserDTO userDB = entityManager.find(UserDTO.class, user.getUUID());
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
	public void deleteUser(UserDTO user) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			UserDTO userDB = entityManager.find(UserDTO.class, user.getUUID());
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
	public void updateUserWithoutPassword(UserDTO user) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			UserDTO userDB = entityManager.find(UserDTO.class, user.getUUID());
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
		UserDTO user = null;
		try {
			TypedQuery<UserDTO> query = entityManager
					.createQuery("Select u " + "from User u where u.name LIKE :n and" + " u.password LIKE :p", UserDTO.class)
					.setParameter("n", userName).setParameter("p", password);
			user = query.getSingleResult();
			
		} catch(PersistenceException e) {
			LOGGER.error("Could not loged user. ", e);
			throw new RepositoryException("Could not loged user.", e);
		}
		return user.getUserType();
	}


	@Override
	public UserDTO getUserById(String id) throws RepositoryException {
		UserDTO user = null;
	    try {
	    	user = entityManager.find(UserDTO.class, id);
	    	LOGGER.info("User retrieved by id.");
	    } catch(PersistenceException e) {
	    	LOGGER.error("Could not retrieve User by id! ", e);
			throw new RepositoryException("Could not retrieve User by id!", e);
	    }
		
		return user;
	}

	@Override
	public List<UserDTO> searchUserByName(String name) throws RepositoryException {
		List<UserDTO> users = null;
		try  {
			TypedQuery<UserDTO> query = entityManager.createQuery("Select u " + "from User u where u.name LIKE :n", UserDTO.class)
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
			UserDTO userDB = entityManager.find(UserDTO.class, uuid);	
			userDB.setLoyaltyIndex(userDB.getLoyaltyIndex()-1);
			entityManager.getTransaction().commit();
			LOGGER.info("User's loyalty decreased.");
		} catch(PersistenceException e) {
			LOGGER.error("Could not decrease user's loyalty", e);
			throw new RepositoryException("Could not decrease user's loyalty", e);
		}
		
	}

}
