package edu.msg.bookland.repository.jdbc;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.msg.bookland.model.User;
import edu.msg.bookland.model.UserType;
import edu.msg.bookland.repository.DAOFactory;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.repository.UserDAO;
import edu.msg.bookland.util.PasswordEncrypting;

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
<<<<<<< HEAD
=======
	private UserDAO userDao = new JDBCUserDAO();
>>>>>>> branch 'develop' of https://github.com/MRoberr/Bookland.git

<<<<<<< HEAD
	private static UserDAO userDao = DAOFactory.getDAOFactory().getUserDAO();
	private static User u = new User();
	private static User u3 = new User();
	private static User uu = new User();

	// run once, initialize database
	@BeforeClass
	public static void runOnceBeforeClass() {
		// user for update, getByName
		u.setName("testAdmin");
		u.setEmail("testAdmin@email");
=======
	private User createUser() {
		User u = new User("");
		u.setName("Robi");
		u.setEmail("alma@fa.com");
>>>>>>> branch 'develop' of https://github.com/MRoberr/Bookland.git
		u.setLoyaltyIndex(10);
<<<<<<< HEAD
		u.setUUID(u.getName());
		u.setPassword(PasswordEncrypting.encrypt("password", "user"));
		u.setUserType(UserType.ADMIN);
		userDao.insertUser(u);
		// user for insert
		uu.setName("testUserDAO");
		uu.setEmail("testUserDAO@email");
		uu.setLoyaltyIndex(10);
		uu.setUUID(uu.getName());
		uu.setPassword(PasswordEncrypting.encrypt("password", "salt"));
		uu.setUserType(UserType.READER);
		// user for delete
		u3.setName("testUser");
		u3.setEmail("testUser@email");
		u3.setLoyaltyIndex(10);
		u3.setUUID(u3.getName());
		u3.setPassword(PasswordEncrypting.encrypt("user", "user"));
		u3.setUserType(UserType.READER);
		userDao.insertUser(u3);

	}

	// Run once, cleanup
	@AfterClass
	public static void runOnceAfterClass() {
		// delete user for update and first user
		userDao.deleteUser(u);
		userDao.deleteUser(uu);
=======
		u.setUUID("321");
		u.setPassword(PasswordEncrypting.encrypt("password", "salt"));
		u.setUserType(UserType.READER);
		return u;
>>>>>>> branch 'develop' of https://github.com/MRoberr/Bookland.git
	}

	/**
	 * C from CRUD. Test User insertion.
	 */
	@Test
	public void testInsertUser() {
		uu.setEmail("testUserDAOOO@email");
		userDao.insertUser(uu);
		assertTrue(true);
	}

	@Test
	public void testInsertUserFail() {
		uu.setEmail("testAdmin@email");
		try {
			userDao.insertUser(uu);
		} catch (RepositoryException e) {
			assertTrue(true);
		}
	}

	/**
	 * R from CRUD. Test select all Users list is not empty.
	 */
	@Test
	public void testSelectAllUser() {
		List<User> users = userDao.getAllUsers();
		assertTrue(!users.isEmpty());
		for (User u : users) {
			System.out.println(u);
		}
	}

	/**
	 * R from CRUD. Test select Users with given name.
	 */
	@Test
	public void testSearchUserByName() {
		List<User> users = userDao.searchUserByName("testAdmin");
		assertTrue(!users.isEmpty());
	}

<<<<<<< HEAD
	@Test
	public void testSearchUserByNameFail() {
		List<User> users = userDao.searchUserByName("NothingToFind");
		assertTrue(users.isEmpty());
=======
	/**
	 * R from CRUD. Test select User with given full name.
	 */
	@Test
	public void test3GetUserByName() {
		try {
			System.out.println(userDao.searchUserByName("Terez"));
			Assert.assertEquals(createUser().getUUID(), userDao.searchUserByName("Terez").get(0).getUUID());
		} catch (RepositoryException e) {
			Assert.fail("Could no search for user.");
		}
>>>>>>> branch 'develop' of https://github.com/MRoberr/Bookland.git
	}

	/**
	 * R from CRUD. Test select User with given id.
	 */
	@Test
	public void testGetUserById() {
		User u4=userDao.getUserById(u.getUUID());
		System.out.println(u4.getEmail());
		assertTrue(u4.getEmail().equals("testAdmin@email"));
		
	}

	@Test
	public void testGetUserByIdFail() {
		try {
<<<<<<< HEAD
			userDao.getUserById("12345").getName();
=======
			Assert.assertEquals(userDao.getUserById("fasfasfgas").getName(),
					createUser().getName());
>>>>>>> branch 'develop' of https://github.com/MRoberr/Bookland.git
		} catch (RepositoryException e) {
			assertTrue(true);
		}
	}

	/**
	 * U from CRUD. Test User update.
	 */
	@Test
	public void testUpdateUser() {
		uu.setEmail("testUserDAO123");
		userDao.updateUser(uu);
		assertTrue(true);
	}

	@Test
	public void testUpdateUserFaild() {
		uu.setName("testUser");
		try {
			userDao.updateUser(uu);
		} catch (RepositoryException e) {
			assertTrue(true);
		}
	}
<<<<<<< HEAD

	/**
	 * D from CRUD. Test User delete.
	 */
	@Test
	public void testDeleteUser() {
		try {
			userDao.deleteUser(u);
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could not delete User.");
		}
	}

	@Test
	public void testDeleteUserFail() {
		String uuid = u.getUUID();
		u.setUUID("nothing");
		try {
			userDao.deleteUser(u);
		} catch (RepositoryException e) {
			u.setUUID(uuid);
			assertTrue(true);
		}
	}

=======
>>>>>>> branch 'develop' of https://github.com/MRoberr/Bookland.git
}

