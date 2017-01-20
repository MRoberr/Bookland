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

	@Override
	public List<User> getAllUsers() throws BusinesLogicException {
		try {
			return userDAO.getAllUsers();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all User!");
			throw new BusinesLogicException("Can't get all User!", e);
		}
	}

	@Override
	public void insertUser(User user) throws BusinesLogicException {
		try {
			userDAO.insertUser(user);
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert User!");
			throw new BusinesLogicException("Can't insert User!", e);
		}
	}

	@Override
	public void updateUser(User user) throws BusinesLogicException {
		try {
			userDAO.updateUser(user);
		} catch (RepositoryException e) {
			LOGGER.error("Can't update User!");
			throw new BusinesLogicException("Can't update User!", e);
		}
	}

	/**
	 * Can't delete user if have any borrowed object
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
			throw new BusinesLogicException("Can't delete User!", e);
		}
	}

	@Override
	public void updateUserWithoutPassword(User user) throws BusinesLogicException {
		try {
			userDAO.updateUserWithoutPassword(user);
		} catch (RepositoryException e) {
			LOGGER.error("Can't update User!");
			throw new BusinesLogicException("Can't update User!", e);
		}
	}

	@Override
	public UserType login(String userName, String password) throws BusinesLogicException {
		try {
			return userDAO.login(userName, password);
		} catch (RepositoryException e) {
			LOGGER.error("Can't login!");
			throw new BusinesLogicException("Can't login!", e);
		}
	}

	@Override
	public List<User> searchUserByName(String name) throws BusinesLogicException {
		try {
			return userDAO.searchUserByName(name);
		} catch (RepositoryException e) {
			LOGGER.error("Can't get Users!");
			throw new BusinesLogicException("Can't get Users!", e);
		}
	}
}
