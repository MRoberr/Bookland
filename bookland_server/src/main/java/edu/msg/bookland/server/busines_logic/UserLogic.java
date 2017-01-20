package edu.msg.bookland.server.busines_logic;

import java.util.List;

import edu.msg.bookland.server.model.User;
import edu.msg.bookland.server.model.UserType;


/**
 * Defines database methods for User Model
 * 
 * @author Csilla Szocs
 * @author Terez Sipos
 */
public interface UserLogic {
	/**
	 * This method define select all for {@link User}
	 * 
	 * @return list of all users
	 * @throws BusinesLogicException
	 */
	List<User> getAllUsers() throws BusinesLogicException;

	/**
	 * This method define insert for {@link User}
	 * 
	 * @param user
	 * @throws BusinesLogicException
	 */
	void insertUser(User user) throws BusinesLogicException;

	/**
	 * This method define update for {@link User}
	 * 
	 * @param user
	 * @throws BusinesLogicException
	 */
	void updateUser(User user) throws BusinesLogicException;

	/**
	 * This method define delete for {@link User}
	 * 
	 * @param user
	 * @throws BusinesLogicException
	 */
	void deleteUser(User user) throws BusinesLogicException;

	/**
	 * This method define update without password {@link User}
	 * 
	 * @param user
	 * @throws BusinesLogicException
	 */
	void updateUserWithoutPassword(User user) throws BusinesLogicException;

	/**
	 * This method define select with condition of username and hashed password
	 * for {@link User}
	 * 
	 * @param userName
	 * @param password
	 * @return the type of user if exist
	 * @throws BusinesLogicException
	 */
	public UserType login(String userName, String password) throws BusinesLogicException;


	/**
	 * This method define select with condition of userId for {@link User}
	 * 
	 * @param id
	 * @return User if exist
	 * @throws BusinesLogicException
	 */
	User getUserById(String id) throws BusinesLogicException;

	/**
	 * This method define select with condition of contains username for
	 * {@link User}
	 * 
	 * @param name
	 * @return list of users
	 * @throws BusinesLogicException
	 */
	List<User> searchUserByName(String name) throws BusinesLogicException;

	void setUserLoyaltyIndex(String uuid) throws BusinesLogicException;
}
