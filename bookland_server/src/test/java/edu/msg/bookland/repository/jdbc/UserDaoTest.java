package edu.msg.bookland.repository.jdbc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.msg.bookland.model.User;
import edu.msg.bookland.model.UserType;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.util.PasswordEncrypting;

public class UserDaoTest {
	private JdbcUserDAO userDao = new JdbcUserDAO();

	@Test
	public void selectAllUserTest() {
		List<User> users = userDao.getAllUsers();
		System.out.println("get all users " + users);
		assertTrue(!users.isEmpty());
	}


	@Test
	public void insertUserTest() {
		try {
			userDao.insertUser(createUser());
		} catch (RepositoryException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void updateUserTest() {
		User u = createUser();
		u.setName("test12");
		u.setUUID("37a97280-bb03-4b65-b84d-7602f6b6a86f");
		try {
			userDao.updateUser(u);
		} catch (RepositoryException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void deleteUserTest() {
		User u = createUser();
		u.setName("test12");
		u.setUUID("37a97280-bb03-4b65-b84d-7602f6b6a86f");
		try {
			userDao.deleteUser(u);
		} catch (RepositoryException e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void getUserByNameTest(){
		try{
			
			System.out.println(userDao.getUserByName("Terez"));
		}catch (RepositoryException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void getUserByIdTest(){
		try{
			
			System.out.println(userDao.getUserById("123"));
		}catch (RepositoryException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void searchUserByNameTest(){
		List<User>userList = new ArrayList<User>();
		try{
			System.out.println(userDao.searchUserByName("jo"));
		}catch (RepositoryException e) {
			System.out.println(e.getMessage());
		}
	}
	private User createUser() {
		User u = new User();
		u.setName("test34766685");
		u.setEmail("test378666645@email");
		u.setLoyaltyIndex(10);
		u.setPassword(PasswordEncrypting.encrypt("password", "salt"));
		u.setUserType(UserType.READER);
		return u;
	}
}
