package edu.msg.bookland.repository;

import java.util.List;

import edu.msg.bookland.model.User;
import edu.msg.bookland.model.UserType;

public interface UserDAO {
	List<User> getAllUsers()throws RepositoryException;
	void insertUser(User user) throws RepositoryException;
	void updateUser(User user) throws RepositoryException;
	void deleteUser(User user) throws RepositoryException;
	void updateUserWithoutPassword(User user) throws RepositoryException;
	public UserType login(String userName, String password) throws RepositoryException;
	User getUserByName(String name) throws RepositoryException;
	User getUserById(String id) throws RepositoryException;
	List<User> searchUserByName(String name) throws RepositoryException;
		
}
