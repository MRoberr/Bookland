package edu.msg.bookland.repository.jdbc;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.RepositoryException;
import edu.msg.bookland.server.repository.UserDAO;
import edu.msg.bookland.server.util.PasswordEncrypting;

/**
 * 
 * Test the User data access object for CRUD operations.
 * 
 * @author Simo Zoltan
 * @author Sipos Terezke
 * @author Szocs Csilla
 *
 */

public class UserDaoTest {

	private static UserDAO userDao = DAOFactory.getDAOFactory().getUserDAO();
	private static UserDTO u = new UserDTO();
	private static UserDTO u3 = new UserDTO();
	private static UserDTO uu = new UserDTO();

	// run once, initialize database
//	@BeforeClass
//	public static void runOnceBeforeClass() {
//		// user for update, getByName
//		u.setName("asdAdmin");
//		u.setEmail("asdAdmin@email");
//		u.setLoyaltyIndex(10);
//		u.setUUID(u.getName());
//		u.setPassword(PasswordEncrypting.encrypt("password", "user"));
//		u.setUserType(UserType.ADMIN);
//		userDao.insertUser(u);
//		// user for insert
//		uu.setName("testasdDAO");
//		uu.setEmail("testasdDAO@email");
//		uu.setLoyaltyIndex(10);
//		uu.setUUID(uu.getName());
//		uu.setPassword(PasswordEncrypting.encrypt("password", "salt"));
//		uu.setUserType(UserType.READER);
//		// user for delete
//		u3.setName("testasd");
//		u3.setEmail("testasd@email");
//		u3.setLoyaltyIndex(10);
//		u3.setUUID(u3.getName());
//		u3.setPassword(PasswordEncrypting.encrypt("user", "user"));
//		u3.setUserType(UserType.READER);
//		userDao.insertUser(u3);
//
//	}
//
//	// Run once, cleanup
//	@AfterClass
//	public static void runOnceAfterClass() {
//		// delete user for update and first user
//		userDao.deleteUser(u);
//		userDao.deleteUser(uu);
//	}
//
//	/**
//	 * C from CRUD. Test User insertion.
//	 */
//	@Test
//	public void testInsertUser() {
//		uu.setEmail("testUserasdDAOOO@email");
//		userDao.insertUser(uu);
//		assertTrue(true);
//	}
//
//	@Test
//	public void testInsertUserFail() {
//		uu.setEmail("testAdminasd@email");
//		try {
//			userDao.insertUser(uu);
//		} catch (RepositoryException e) {
//			assertTrue(true);
//		}
//	}
//
//	/**
//	 * R from CRUD. Test select all Users list is not empty.
//	 */
//	@Test
//	public void testSelectAllUser() {
//		List<User> users = userDao.getAllUsers();
//		assertTrue(!users.isEmpty());
//		for (User u : users) {
//			System.out.println(u);
//		}
//	}

	/**
	 * R from CRUD. Test select Users with given name.
	 */
	@Test
	public void testSearchUserByName() {
		List<UserDTO> users = userDao.searchUserByName("r");	
		assertTrue(!users.isEmpty());
	}

//	@Test
//	public void testSearchUserByNameFail() {
//		List<User> users = userDao.searchUserByName("NothingToFind");
//		assertTrue(users.isEmpty());
//	}
//
//	/**
//	 * R from CRUD. Test select User with given id.
//	 */
//	@Test
//	public void testGetUserById() {
//		User u4=userDao.getUserById(u.getUUID());
//		System.out.println(u4.getEmail());
//		assertTrue(u4.getEmail().equals("testAdmin@email"));
//		
//	}
//
//	@Test
//	public void testGetUserByIdFail() {
//		try {
//			userDao.getUserById("12345").getName();
//		} catch (RepositoryException e) {
//			assertTrue(true);
//		}
//	}
//
//	/**
//	 * U from CRUD. Test User update.
//	 */
//	@Test
//	public void testUpdateUser() {
//		uu.setEmail("testUserDAO123");
//		userDao.updateUser(uu);
//		assertTrue(true);
//	}
//
//	@Test
//	public void testUpdateUserFaild() {
//		uu.setName("testUser");
//		try {
//			userDao.updateUser(uu);
//		} catch (RepositoryException e) {
//			assertTrue(true);
//		}
//	}
//
//	/**
//	 * D from CRUD. Test User delete.
//	 */
//	@Test
//	public void testDeleteUser() {
//		try {
//			userDao.deleteUser(u);
//			assertTrue(true);
//		} catch (RepositoryException e) {
//			Assert.fail("Could not delete User.");
//		}
//	}
//
//	@Test
//	public void testDeleteUserFail() {
//		String uuid = u.getUUID();
//		u.setUUID("nothing");
//		try {
//			userDao.deleteUser(u);
//		} catch (RepositoryException e) {
//			u.setUUID(uuid);
//			assertTrue(true);
//		}
//	}

}
