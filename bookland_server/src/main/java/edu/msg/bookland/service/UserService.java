package edu.msg.bookland.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.model.User;
import edu.msg.bookland.model.UserType;
import edu.msg.bookland.repository.DAOFactory;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.repository.UserDAO;
import edu.msg.bookland.repository.jdbc.JDBCUserDAO;
import edu.msg.bookland.rmi.UserServiceRmi;
import edu.msg.bookland.util.PasswordEncrypting;

/**
 * Implement methods of UserServiceRmi. Call methods of DAO and contains
 * Business Logic
 * 
 * @author Terez Sipos
 */
public class UserService extends UnicastRemoteObject implements UserServiceRmi {

	private static final long serialVersionUID = -2602148302307548346L;

	private static final Logger LOGGER = Logger.getLogger(JDBCUserDAO.class);
	private UserDAO userDAO;

	/**
	 * initialize UserDAO
	 * 
	 * @throw ServiceException if can't get a DAO
	 */
	public UserService() throws RemoteException {
		try {
			userDAO = DAOFactory.getDAOFactory().getUserDAO();
		} catch (RepositoryException e) {
			LOGGER.error("Failed to create a user service");
			throw new ServiceException("Failed to create a user service", e);
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() throws RemoteException {
		List<User> users;
		try {
			users = userDAO.getAllUsers();
			return users;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get all users");
			throw new ServiceException("Failed to get all users", e);
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#insertUser(edu.msg.bookland.model.User)
	 */
	@Override
	public void insertUser(User user) throws RemoteException {
		user.setPassword(PasswordEncrypting.encrypt(user.getPassword(), "user"));
		try {
			userDAO.insertUser(user);
		} catch (RepositoryException e) {
			LOGGER.error("Failed to insert user");
			throw new ServiceException("Failed to insert user", e);
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#updateUser(edu.msg.bookland.model.User)
	 */
	@Override
	public void updateUser(User user) throws RemoteException {
		user.setPassword(PasswordEncrypting.encrypt(user.getPassword(), "user"));
		try {
			userDAO.updateUser(user);
		} catch (RepositoryException e) {
			LOGGER.error("Failed to update user");
			throw new ServiceException("Failed to update user", e);
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#deleteUser(edu.msg.bookland.model.User)
	 */
	@Override
	public void deleteUser(User user) throws RemoteException {
		try {
			userDAO.deleteUser(user);
		} catch (RepositoryException e) {
			LOGGER.error("Failed to delete user");
			throw new ServiceException("Failed to delete user", e);
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#getUserByName(java.lang.String)
	 */
	@Override
	public User getUserByName(String name) throws RemoteException {
		User user;
		try {
			user = userDAO.getUserByName(name);
			return user;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get user");
			throw new ServiceException("Failed to get user", e);
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#getUserByUUUID(java.lang.String)
	 */
	@Override
	public User getUserByUUUID(String uuid) throws RemoteException {
		User user;
		try {
			user = userDAO.getUserById(uuid);
			return user;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get user");
			throw new ServiceException("Failed to get user", e);
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#searchUser(java.lang.String)
	 */
	@Override
	public List<User> searchUser(String name) throws RemoteException {
		List<User> users;
		try {
			users = userDAO.searchUserByName(name);
			return users;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get user");
			throw new ServiceException("Failed to get user", e);
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#login(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public UserType login(String name, String password) throws RemoteException {
		UserType type;
		String pass = PasswordEncrypting.encrypt(password, "user");
		try {
			type = userDAO.login(name, pass);
			return type;
		} catch (RepositoryException e) {
			LOGGER.error("Invalid login");
			throw new ServiceException("Invatid login", e);
		}
	}

}
