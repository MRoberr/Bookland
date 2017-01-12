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
import edu.msg.bookland.repository.jdbc.JdbcUserDAO;
import edu.msg.bookland.rmi.UserServiceRmi;
import edu.msg.bookland.util.PasswordEncrypting;

public class UserService extends UnicastRemoteObject implements UserServiceRmi {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2602148302307548346L;
	
	private static final Logger LOGGER = Logger.getLogger(JdbcUserDAO.class);
	private UserDAO userDAO;

	public UserService() throws RemoteException {
		super();
		try {
			userDAO = DAOFactory.getDAOFactory().getUserDAO();
		} catch (RepositoryException e) {
			LOGGER.error("Failed to create a user service");
			throw new ServiceException("Failed to create a user service", e);
		}
	}

	@Override
	public List<User> getAllUsers() throws RemoteException {
		List<User> users;
		try{
			users=userDAO.getAllUsers();
			return users;
		}catch (RepositoryException e) {
			LOGGER.error("Failed to get all users");
			throw new ServiceException("Failed to get all users", e);
		}
	}

	@Override
	public void insertUser(User user) throws RemoteException {
		user.setPassword(PasswordEncrypting.encrypt(user.getPassword(), "user"));
		try{
			userDAO.insertUser(user);
		}catch (RepositoryException e) {
			LOGGER.error("Failed to insert user");
			throw new ServiceException("Failed to insert user", e);
		}
	}

	@Override
	public void updateUser(User user) throws RemoteException {
		user.setPassword(PasswordEncrypting.encrypt(user.getPassword(), "user"));
		try{
			userDAO.updateUser(user);
		}catch (RepositoryException e) {
			LOGGER.error("Failed to update user");
			throw new ServiceException("Failed to update user", e);
		}
	}

	@Override
	public void deleteUser(User user) throws RemoteException {
		try{
			userDAO.deleteUser(user);
		}catch (RepositoryException e) {
			LOGGER.error("Failed to delete user");
			throw new ServiceException("Failed to delete user", e);
		}
	}

	@Override
	public User getUserByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUUUID(String uuid) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> searchUser(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserType login(String name, String password) throws RemoteException {
		UserType type;
		String pass = PasswordEncrypting.encrypt(password, "user");
		try{
			type=userDAO.login(name, pass);
			return type;
		}catch (RepositoryException e) {
			LOGGER.error("Invalid login");
			throw new ServiceException("Invatid login", e);
		}
	}

}
