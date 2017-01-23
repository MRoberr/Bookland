package edu.msg.bookland.server.business_logic.basic;

import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.business_logic.UserBL;
import edu.msg.bookland.server.model.User;
import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.RepositoryException;
import edu.msg.bookland.server.repository.UserDAO;

/**
 * Implementation of {@link UserBL}
 * 
 * @author Sipos Terez
 */
public class BasicUserBL implements UserBL {
	private static final Logger LOGGER = Logger.getLogger(BasicUserBL.class);
	private UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();

	/*
	 * @see edu.msg.bookland.server.business_logic.UserBL#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() throws BusinesLogicException {
		try {
			return userDAO.getAllUsers();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all User!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.UserBL#insertUser(edu.msg.bookland
	 * .server.model.User)
	 */
	@Override
	public void insertUser(User user) throws BusinesLogicException {
		try {
			userDAO.insertUser(user);
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert User!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.UserBL#updateUser(edu.msg.bookland
	 * .server.model.User)
	 */
	@Override
	public void updateUser(User user) throws BusinesLogicException {
		try {
			userDAO.updateUser(user);
		} catch (RepositoryException e) {
			LOGGER.error("Can't update User!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * Can't delete user if have any borrowed object
	 * 
	 * @see edu.msg.bookland.server.business_logic.UserBL#deleteUser(java.lang.
	 * String)
	 */
	@Override
	public void deleteUser(String id) throws BusinesLogicException {
		try {
			User user = userDAO.getUserById(id);
			if (user.getBorrow().isEmpty()) {
				userDAO.deleteUser(id);
			} else {
				LOGGER.error("This user have borrowed Pubications, you Can't delete User!");
				throw new BusinesLogicException("This user have borrowed Pubications, you Can't delete User!");
			}
		} catch (RepositoryException e) {
			LOGGER.error("Can't delete User!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.UserBL#updateUserWithoutPassword(
	 * edu.msg.bookland.server.model.User)
	 */
	@Override
	public void updateUserWithoutPassword(User user) throws BusinesLogicException {
		try {
			userDAO.updateUserWithoutPassword(user);
		} catch (RepositoryException e) {
			LOGGER.error("Can't update User!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.UserBL#login(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public UserType login(String userName, String password) throws BusinesLogicException {
		try {
			return userDAO.login(userName, password);
		} catch (RepositoryException e) {
			LOGGER.error("Can't login!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.business_logic.UserBL#searchUserByName(java.lang.
	 * String)
	 */
	@Override
	public List<User> searchUserByName(String name) throws BusinesLogicException {
		try {
			return userDAO.searchUserByName(name);
		} catch (RepositoryException e) {
			LOGGER.error("Can't get Users!");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}
}
