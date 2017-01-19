package edu.msg.bookland.repository;

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
	 * @throws RepositoryException
	 */
	List<UserDTO> getAllUsers() throws RepositoryException;

	/**
	 * This method define insert for {@link UserDTO}
	 * 
	 * @param user
	 * @throws RepositoryException
	 */
	void insertUser(UserDTO user) throws RepositoryException;

	/**
	 * This method define update for {@link UserDTO}
	 * 
	 * @param user
	 * @throws RepositoryException
	 */
	void updateUser(UserDTO user) throws RepositoryException;

	/**
	 * This method define delete for {@link UserDTO}
	 * 
	 * @param user
	 * @throws RepositoryException
	 */
	void deleteUser(UserDTO user) throws RepositoryException;

	/**
	 * This method define update without password {@link UserDTO}
	 * 
	 * @param user
	 * @throws RepositoryException
	 */
	void updateUserWithoutPassword(UserDTO user) throws RepositoryException;

	/**
	 * This method define select with condition of username and hashed password
	 * for {@link UserDTO}
	 * 
	 * @param userName
	 * @param password
	 * @return the type of user if exist
	 * @throws RepositoryException
	 */
	public UserType login(String userName, String password) throws RepositoryException;


	/**
	 * This method define select with condition of userId for {@link UserDTO}
	 * 
	 * @param id
	 * @return User if exist
	 * @throws RepositoryException
	 */
	UserDTO getUserById(String id) throws RepositoryException;

	/**
	 * This method define select with condition of contains username for
	 * {@link UserDTO}
	 * 
	 * @param name
	 * @return list of users
	 * @throws RepositoryException
	 */
	List<UserDTO> searchUserByName(String name) throws RepositoryException;

	void setUserLoyaltyIndex(String uuid) throws RepositoryException;
}
