package edu.msg.bookland.server.repository;

import java.util.List;

import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.server.model.*;

/**
 * Defines database methods for User Model
 * 
 * @author Csilla Szocs
 * @author Terez Sipos
 */
public interface UserDAO {
	/**
	 * This method define select all for {@link User}
	 * 
	 * @return list of all users
	 * @throws RepositoryException
	 */
	List<User> getAllUsers() throws RepositoryException;

	/**
	 * This method define insert for {@link User}
	 * 
	 * @param user
	 * @throws RepositoryException
	 */
	void insertUser(User user) throws RepositoryException;

	/**
	 * This method define update for {@link User}
	 * 
	 * @param user
	 * @throws RepositoryException
	 */
	void updateUser(User user) throws RepositoryException;

	/**
	 * This method define delete for {@link User}
	 * 
	 * @param user
	 * @throws RepositoryException
	 */
	void deleteUser(String id) throws RepositoryException;

	/**
	 * This method define update without password {@link User}
	 * 
	 * @param user
	 * @throws RepositoryException
	 */
	void updateUserWithoutPassword(User user) throws RepositoryException;

	/**
	 * This method define select with condition of username and hashed password
	 * for {@link User}
	 * 
	 * @param userName
	 * @param password
	 * @return the type of user if exist
	 * @throws RepositoryException
	 */
	public UserType login(String userName, String password) throws RepositoryException;


	/**
	 * This method define select with condition of userId for {@link User}
	 * 
	 * @param id
	 * @return User if exist
	 * @throws RepositoryException
	 */
	User getUserById(String id) throws RepositoryException;

	/**
	 * This method define select with condition of contains username for
	 * {@link User}
	 * 
	 * @param name
	 * @return list of users
	 * @throws RepositoryException
	 */
	List<User> searchUserByName(String name) throws RepositoryException;

	void decreaseLoyaltyIndex(String uuid) throws RepositoryException;
	void increaseLoyaltyIndex(String uuid) throws RepositoryException;
}
