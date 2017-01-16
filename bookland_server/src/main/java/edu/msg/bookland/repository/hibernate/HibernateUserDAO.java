package edu.msg.bookland.repository.hibernate;

import java.sql.SQLException;
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

import edu.msg.bookland.model.User;
import edu.msg.bookland.model.UserType;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.repository.UserDAO;
import edu.msg.bookland.repository.jdbc.JDBCUserDAO;

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
			LOGGER.error("Could not inserd users. ", e);
			throw new RepositoryException("Could not inser users. ", e);
		}
		

	}

	@Override
	public void updateUser(User user) throws RepositoryException {
		entityManager.getTransaction().begin();
		// entityManager.refresh(entity);

		User user2 = entityManager.find(User.class, user.getUUID());
		user2.setEmail(user.getEmail());
		user2.setLoyaltyIndex(user.getLoyaltyIndex());
		user2.setName(user.getName());
		user2.setUserType(user.getUserType());
		user2.setPassword(user.getPassword());
		entityManager.getTransaction().commit();

	}

	@Override
	public void deleteUser(User user) throws RepositoryException {
		entityManager.getTransaction().begin();
		User user2 = entityManager.find(User.class, user.getUUID());
		entityManager.remove(user2);
		entityManager.getTransaction().commit();

	}

	@Override
	public void updateUserWithoutPassword(User user) throws RepositoryException {
		entityManager.getTransaction().begin();
		User user2 = entityManager.find(User.class, user.getUUID());
		user2.setEmail(user.getEmail());
		user2.setLoyaltyIndex(user.getLoyaltyIndex());
		user2.setName(user.getName());
		user2.setUserType(user.getUserType());
		entityManager.getTransaction().commit();

	}

	@Override
	public UserType login(String userName, String password) throws RepositoryException {
		TypedQuery<User> query = entityManager
				.createQuery("Select u " + "from User u where u.name LIKE :n and" + " u.password LIKE :p", User.class)
				.setParameter("n", userName).setParameter("p", password);
		User user = query.getSingleResult();
		return user.getUserType();
	}

	@Override
	public User getUserByName(String name) throws RepositoryException {
		TypedQuery<User> query = entityManager.createQuery("Select u " + "from User u where u.name LIKE :n", User.class)
				.setParameter("n", name);
		User user = query.getSingleResult();
		return user;

	}

	@Override
	public User getUserById(String id) throws RepositoryException {
		User user = entityManager.find(User.class, id);
		return user;
	}

	@Override
	public List<User> searchUserByName(String name) throws RepositoryException {
		TypedQuery<User> query = entityManager.createQuery("Select u " + "from User u where u.name LIKE :n", User.class)
				.setParameter("n", "%" + name + "%");
		List<User> users = query.getResultList();
		return users;
	}

}
