package edu.msg.bookland.server.busines_logic;

import java.util.List;

import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.common.model.UserType;

/**
 * Defines database methods for User Model
 * 
 * @author Csilla Szocs
 * @author Terez Sipos
 */
public interface UserDAO {
	/**
	 * This method define select all for {@link UserDTO}
	 * 
	 * @return list of all users
	 * @throws BusinesLogicException
	 */
	List<UserDTO> getAllUsers() throws BusinesLogicException;

	/**
	 * This method define insert for {@link UserDTO}
	 * 
	 * @param user
	 * @throws BusinesLogicException
	 */
	void insertUser(UserDTO user) throws BusinesLogicException;

	/**
	 * This method define update for {@link UserDTO}
	 * 
	 * @param user
	 * @throws BusinesLogicException
	 */
	void updateUser(UserDTO user) throws BusinesLogicException;

	/**
	 * This method define delete for {@link UserDTO}
	 * 
	 * @param user
	 * @throws BusinesLogicException
	 */
	void deleteUser(UserDTO user) throws BusinesLogicException;

	/**
	 * This method define update without password {@link UserDTO}
	 * 
	 * @param user
	 * @throws BusinesLogicException
	 */
	void updateUserWithoutPassword(UserDTO user) throws BusinesLogicException;

	/**
	 * This method define select with condition of username and hashed password
	 * for {@link UserDTO}
	 * 
	 * @param userName
	 * @param password
	 * @return the type of user if exist
	 * @throws BusinesLogicException
	 */
	public UserType login(String userName, String password) throws BusinesLogicException;


	/**
	 * This method define select with condition of userId for {@link UserDTO}
	 * 
	 * @param id
	 * @return User if exist
	 * @throws BusinesLogicException
	 */
	UserDTO getUserById(String id) throws BusinesLogicException;

	/**
	 * This method define select with condition of contains username for
	 * {@link UserDTO}
	 * 
	 * @param name
	 * @return list of users
	 * @throws BusinesLogicException
	 */
	List<UserDTO> searchUserByName(String name) throws BusinesLogicException;

	void setUserLoyaltyIndex(String uuid) throws BusinesLogicException;
}
