package edu.msg.bookland.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.common.rmi.UserServiceRmi;
import edu.msg.bookland.repository.DAOFactory;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.repository.UserDAO;
import edu.msg.bookland.repository.jdbc.JDBCUserDAO;
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

	@Override
	public List<UserDTO> getAllUsers() throws RemoteException {
		try {
			return userDAO.getAllUsers();
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get all users");
			return null;
		}
	}

	@Override
	public boolean insertUser(UserDTO user) throws RemoteException {
		try {
			user.setPassword(PasswordEncrypting.encrypt(user.getPassword(), "user"));
			userDAO.insertUser(user);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to insert user");
			return false;
		}
	}

	@Override
	public boolean updateUser(UserDTO user) throws RemoteException {
		try {
			user.setPassword(PasswordEncrypting.encrypt(user.getPassword(), "user"));
			userDAO.updateUser(user);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to update user");
			return false;
		}
	}

	@Override
	public boolean deleteUser(UserDTO user) throws RemoteException {
		BorrowingService borrow = new BorrowingService();
		try {
			List<Tuple> userPubs = borrow.getBorrowByUserUUID(user.getUUID());
			if (userPubs == null)
				return false;
			userDAO.deleteUser(user);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to delete user");
			return false;
		}
	}

	@Override
	public UserDTO getUserByUUUID(String uuid) throws RemoteException {
		try {
			return userDAO.getUserById(uuid);
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get user");
			return null;
		}
	}

	@Override
	public List<UserDTO> searchUser(String name) throws RemoteException {
		List<UserDTO> usersList=new ArrayList<>();
		try {
			List<UserDTO>users= userDAO.searchUserByName(name);
			for(UserDTO u:users){
				usersList.add(new UserDTO(u));
			}
			return usersList;
		} catch (RepositoryException e) {
			LOGGER.error("Failed to get user");
			return null;
		}
	}

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
	public boolean setUserLoyaltyIndex(String uuid) {
		return false;

	}

}
