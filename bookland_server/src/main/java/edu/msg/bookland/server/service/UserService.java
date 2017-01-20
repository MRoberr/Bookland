package edu.msg.bookland.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.BorrowingDTO;
import edu.msg.bookland.common.model.ServiceException;
import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.common.rmi.UserServiceRmi;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.Borrowing;
import edu.msg.bookland.server.model.User;
import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.RepositoryException;
import edu.msg.bookland.server.repository.UserDAO;
import edu.msg.bookland.server.util.PasswordEncrypting;

/**
 * Implement methods of UserServiceRmi. Call methods of DAO and contains
 * Business Logic
 * 
 * @author Jozsef Solomon
 * @author Terez Sipos
 */
public class UserService extends UnicastRemoteObject implements UserServiceRmi {

	private static final long serialVersionUID = -2602148302307548346L;

	private static final Logger LOGGER = Logger.getLogger(UserService.class);
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
		List<User> users = null;
		List<UserDTO> usersDTO;
		try {
			// a listat Businessbol kapom!!!
			users = userDAO.getAllUsers();
			LOGGER.info("Sucessfully received all users");
		} catch (BusinesLogicException e) { // Exception kicserelni
			LOGGER.error("Failed to get all users");
			throw new ServiceException("Failed to get all users");
		}
		if (users != null && users.size() > 0) {
			usersDTO = new ArrayList<>();
			for (User u : users) {
				UserDTO userDTO = new UserDTO();
				userDTO.setName(u.getName());
				userDTO.setEmail(u.getEmail());
				userDTO.setLoyaltyIndex(u.getLoyaltyIndex());
				userDTO.setUUID(u.getUUID());
				userDTO.setUserType(u.getUserType());
				List<Borrowing> borrowings = u.getBorrow();
				List<BorrowingDTO> borrowingsDTO = new ArrayList<>();

				for (Borrowing b : borrowings) {
					BorrowingDTO borrowingDTO = new BorrowingDTO();
					borrowingDTO.setUserId(b.getUserId());
					borrowingDTO.setPublicationId(b.getPublicationId());
					borrowingDTO.setBorrowingDate(b.getBorrowingDate());
					borrowingDTO.setDeadline(b.getDeadline());
					borrowingsDTO.add(borrowingDTO);
				}
				userDTO.setBorrow(borrowingsDTO);
			}

			return usersDTO;
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public void insertUser(UserDTO userDTO) throws RemoteException {
		User user = new User();
		user.setUUID(userDTO.getUUID());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setLoyaltyIndex(userDTO.getLoyaltyIndex());
		user.setEmail(userDTO.getEmail());
		user.setUserType(userDTO.getUserType());

		try {
			userDAO.insertUser(user);
		} catch (BusinesLogicException e) {
			LOGGER.error("Failed to insert user");
			throw new ServiceException("Failed to insert user");

		}
	}

	@Override
	public void updateUser(UserDTO userDTO) throws RemoteException {
		User user = new User();
		user.setUUID(userDTO.getUUID());
		user.setName(userDTO.getName());
		user.setLoyaltyIndex(userDTO.getLoyaltyIndex());
		user.setEmail(userDTO.getEmail());
		user.setUserType(userDTO.getUserType());

		try {
			userDAO.updateUser(user);
			LOGGER.info("Updated user");
		} catch (BusinesLogicException e) {
			LOGGER.error("Failed to update user");
			throw new ServiceException("Failed to update user");
		}
	}

	@Override
	public void deleteUser(String userID) throws RemoteException {

		try {
			// userLogic.deleteUser(userID);
			LOGGER.info("Deleted user with id");
		} catch (BusinesLogicException e) {
			LOGGER.error("Failed to delete user");
			throw new ServiceException("Failed to delete user");
		}
	}

	@Override
	public List<UserDTO> searchUser(String name) throws RemoteException {
		List<UserDTO> usersDTO;
		List<User> users = null;
		try {
			users = userDAO.searchUserByName(name);
		} catch (BusinesLogicException e) {
			LOGGER.error("Failed to get user");
		}

		if (users != null && users.size() > 0) {
			usersDTO = new ArrayList<>();
			for (User u : users) {
				UserDTO userDTO = new UserDTO();
				userDTO.setName(u.getName());
				userDTO.setEmail(u.getEmail());
				userDTO.setLoyaltyIndex(u.getLoyaltyIndex());
				userDTO.setUUID(u.getUUID());
				userDTO.setUserType(u.getUserType());
				List<Borrowing> borrowings = u.getBorrow();
				List<BorrowingDTO> borrowingsDTO = new ArrayList<>();

				for (Borrowing b : borrowings) {
					BorrowingDTO borrowingDTO = new BorrowingDTO();
					borrowingDTO.setUserId(b.getUserId());
					borrowingDTO.setPublicationId(b.getPublicationId());
					borrowingDTO.setBorrowingDate(b.getBorrowingDate());
					borrowingDTO.setDeadline(b.getDeadline());
					borrowingsDTO.add(borrowingDTO);
				}
				userDTO.setBorrow(borrowingsDTO);
			}

			return usersDTO;
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public UserType login(String name, String password) throws RemoteException {
		try {
			return userDAO.login(name, password);
		} catch (BusinesLogicException e) {
			LOGGER.error("Invalid login");
			throw new ServiceException("Invalid login");
			
		}
	}

}
