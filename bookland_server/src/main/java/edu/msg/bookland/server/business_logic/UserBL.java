package edu.msg.bookland.server.business_logic;

import java.util.List;

import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.server.business_logic.basic.BasicUserBL;
import edu.msg.bookland.server.model.User;

/**
 * Business Logic for UserService
 * 
 * @author Sipos Terez
 */
public interface UserBL {
	
	/**
	 * static method to get an instance
	 * 
	 * @return BasicUserBL instance
	 */
	public static UserBL getInstance(){
		return  new BasicUserBL();
	} 
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
	 * @param id of User
	 * @throws BusinesLogicException
	 */
	void deleteUser(String id) throws BusinesLogicException;

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
	 * This method define select with condition of contains username for
	 * {@link User}
	 * 
	 * @param name
	 * @return list of users
	 * @throws BusinesLogicException
	 */
	List<User> searchUserByName(String name) throws BusinesLogicException;

}
