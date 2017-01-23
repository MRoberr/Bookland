package edu.msg.bookland.server.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.server.model.User;
import edu.msg.bookland.server.model.User_;
import edu.msg.bookland.server.repository.RepositoryException;
import edu.msg.bookland.server.repository.UserDAO;

public class HibernateUserDAO implements UserDAO {
	private static final Logger LOGGER = Logger.getLogger(HibernateUserDAO.class);
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private CriteriaBuilder builder;

	public HibernateUserDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
		entityManager = entityManagerFactory.createEntityManager();
		builder = entityManager.getCriteriaBuilder();
	}

	@Override
	public List<User> getAllUsers() throws RepositoryException {
		try {
			CriteriaQuery<User> users = builder.createQuery(User.class);
			Root<User> User = users.from(User.class);
			users.select(User);
			TypedQuery<User> userQuery = entityManager.createQuery(users);
			List<User> userList = userQuery.getResultList();
			if (userList.isEmpty()) {
				LOGGER.error("Can't find any User.");
				throw new RepositoryException("Can't find any User.");
			}
			LOGGER.info("All users sellected!");
			return userList;
		} catch (PersistenceException e) {
			LOGGER.error("Could not get all users", e);
			throw new RepositoryException("Could not get all users", e);
		}
	}

	@Override
	public void insertUser(User user) throws RepositoryException {

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			LOGGER.info("user inserted");
		} catch (RollbackException e) {
			Throwable t = e.getCause();
			while ((t != null) && !(t instanceof ConstraintViolationException)) {
				t = t.getCause();
			}
			if (t instanceof ConstraintViolationException) {
				LOGGER.error("This user allready exist", e);
				throw new RepositoryException(t.getCause().getMessage(), e);
			} else {
				LOGGER.error("Could not instert this user", e.getCause());
				throw new RepositoryException("Could not instert this user", e);
			}
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
		} catch (RollbackException e) {
			Throwable t = e.getCause();
			while ((t != null) && !(t instanceof ConstraintViolationException)) {
				t = t.getCause();
			}
			if (t instanceof ConstraintViolationException) {
				LOGGER.error("This user allready exist", e);
				throw new RepositoryException(t.getCause().getMessage(), e);
			} else {
				LOGGER.error("Could not update this user", e.getCause());
				throw new RepositoryException("Could not update this user", e);
			}
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not update user. ", e);
			throw new RepositoryException("Could not update user. ", e);
		}

	}

	@Override
	public void deleteUser(String id) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			User userDB = entityManager.find(User.class, id);
			entityManager.remove(userDB);
			entityManager.getTransaction().commit();
			LOGGER.info("Deleted user");
		} catch (PersistenceException e) {
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
		} catch (RollbackException e) {
			Throwable t = e.getCause();
			while ((t != null) && !(t instanceof ConstraintViolationException)) {
				t = t.getCause();
			}
			if (t instanceof ConstraintViolationException) {
				LOGGER.error("This user allready exist", e);
				throw new RepositoryException(t.getCause().getMessage(), e);
			} else {
				LOGGER.error("Could not update this user", e.getCause());
				throw new RepositoryException("Could not update this user", e);
			}
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not update user without password. ", e);
			throw new RepositoryException("Could not update user without password. ", e);
		}

	}

	@Override
	public UserType login(String userName, String password) throws RepositoryException {
		try {
			CriteriaQuery<User> userLogin = builder.createQuery(User.class);
			Root<User> user1 = userLogin.from(User.class);

			userLogin.select(user1);
			userLogin.where(builder.and(builder.equal(user1.get(User_.name), userName),
					builder.equal(user1.get(User_.password), password)));
			TypedQuery<User> pubQuery = entityManager.createQuery(userLogin);
			User user = pubQuery.getSingleResult();
			if (user == null) {
				LOGGER.error("Invalid username or password.");
				throw new RepositoryException("Invalid username or password.");
			}
			LOGGER.info("Loged in");
			return user.getUserType();
		} catch (PersistenceException e) {
			LOGGER.error("Could not loged user. ", e);
			throw new RepositoryException("Could not loged user.", e);
		}
	}

	@Override
	public User getUserById(String id) throws RepositoryException {
		try {
			User user = entityManager.find(User.class, id);
			if (user == null) {
				LOGGER.error("Can't find user with specifield Id <" + id + ">.");
				throw new RepositoryException("Can't find user with specifield Id.");
			}
			LOGGER.info("User retrieved by id.");
			return user;
		} catch (PersistenceException e) {
			LOGGER.error("Could not retrieve User by id! ", e);
			throw new RepositoryException("Could not retrieve User by id!", e);
		}
	}

	@Override
	public List<User> searchUserByName(String name) throws RepositoryException {
		try {
			CriteriaQuery<User> searchUser = builder.createQuery(User.class);
			Root<User> user1 = searchUser.from(User.class);

			searchUser.select(user1);
			searchUser.where(builder.like(user1.get(User_.name), '%' + name + '%'));

			TypedQuery<User> pubQuery = entityManager.createQuery(searchUser);
			List<User> userList = pubQuery.getResultList();
			if (userList.isEmpty()) {
				LOGGER.error("Can't find user with specifield name <" + name + ">.");
				throw new RepositoryException("Can't find user with specifield name <" + name + ">.");
			}
			LOGGER.info("Users selected");
			return userList;
		} catch (PersistenceException e) {
			LOGGER.error("Could not retrieve user list", e);
			throw new RepositoryException("Could not retrieve user list", e);
		}
	}

	@Override
	public void decreaseLoyaltyIndex(String uuid) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			User userDB = entityManager.find(User.class, uuid);
			userDB.setLoyaltyIndex(userDB.getLoyaltyIndex() - 1);
			entityManager.getTransaction().commit();
			LOGGER.info("User's loyalty decreased.");
		} catch (PersistenceException e) {
			LOGGER.error("Could not decrease user's loyalty", e);
			throw new RepositoryException("Could not decrease user's loyalty", e);
		}
	}

	@Override
	public void increaseLoyaltyIndex(String uuid) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			User userDB = entityManager.find(User.class, uuid);
			userDB.setLoyaltyIndex(userDB.getLoyaltyIndex() + 1);
			entityManager.getTransaction().commit();
			LOGGER.info("User's loyalty Increased.");
		} catch (PersistenceException e) {
			LOGGER.error("Could not increase user's loyalty", e);
			throw new RepositoryException("Could not increase user's loyalty", e);
		}
	}

}
