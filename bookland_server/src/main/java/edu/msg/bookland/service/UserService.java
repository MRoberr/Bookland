package edu.msg.bookland.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.model.Publication;
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
		userDAO = DAOFactory.getDAOFactory().getUserDAO();
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() throws RemoteException {
		try {
			return userDAO.getAllUsers();
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get all users");
			return null;
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#insertUser(edu.msg.bookland.model.User)
	 */
	@Override
	public boolean insertUser(User user) throws RemoteException {
		try {
			user.setPassword(PasswordEncrypting.encrypt(user.getPassword(), "user"));
			userDAO.insertUser(user);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to insert user");
			return false;
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#updateUser(edu.msg.bookland.model.User)
	 */
	@Override
	public boolean updateUser(User user) throws RemoteException {
		try {
			user.setPassword(PasswordEncrypting.encrypt(user.getPassword(), "user"));
			userDAO.updateUser(user);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to update user");
			return false;
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#deleteUser(edu.msg.bookland.model.User)
	 */
	@Override
	public boolean deleteUser(User user) throws RemoteException {
		BorrowingService borrow=new BorrowingService();
		List<Publication> userPubs=borrow.getBorrowByUserUUID(user.getUUID());
		if(userPubs==null) return false;
		try {
			userDAO.deleteUser(user);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to delete user");
			return false;
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#getUserByUUUID(java.lang.String)
	 */
	@Override
	public User getUserByUUUID(String uuid) throws RemoteException {
		try {
			return userDAO.getUserById(uuid);
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get user");
			return null;
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#searchUser(java.lang.String)
	 */
	@Override
	public List<User> searchUser(String name) throws RemoteException {
		try {
			return userDAO.searchUserByName(name);
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get user");
			return null;
		}
	}

	/**
	 * @see edu.msg.bookland.rmi.UserServiceRmi#login(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public UserType login(String name, String password) throws RemoteException {
		try {
			String pass = PasswordEncrypting.encrypt(password, "user");			
			return userDAO.login(name, pass);
		} catch (RepositoryException e) {
			LOGGER.error("Invalid login");
			return null;
		}
	}
	
	/**
	 * This method decreases the loyalty index of the user specified by uuid 
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean setUserLoyaltyIndex(String uuid){
		return false;
		
	}

}
