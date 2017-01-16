package edu.msg.bookland.repository.jdbc;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.msg.bookland.model.User;
import edu.msg.bookland.model.UserType;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.repository.UserDAO;
import edu.msg.bookland.repository.hibernate.HibernateUserDAO;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {
	private UserDAO userDao = new JDBCUserDAO();

	private User createUser() {
		User u = new User("");
		u.setName("Robi");
		u.setEmail("alma@fa.com");
		u.setLoyaltyIndex(10);
		u.setUUID("321");
		u.setPassword(PasswordEncrypting.encrypt("password", "salt"));
		u.setUserType(UserType.READER);
		return u;
	}

	/**
	 * C from CRUD. Test User insertion.
	 */
	@Test
	public void test0InsertUser() {
		try {
			userDao.insertUser(createUser());
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could no insert user.");
		}
	}

	/**
	 * R from CRUD. Test select all Users list is not empty.
	 */
	@Test
	public void test1SelectAllUser() {
		try {
			List<User> users = userDao.getAllUsers();
			assertTrue(!users.isEmpty());
			for(User u:users) {
				System.out.println(u);
			}
		} catch (RepositoryException e) {
			Assert.fail("Could no get users.");
		}
	}

	/**
	 * R from CRUD. Test select Users with given name.
	 */
	@Test
	public void test2SearchUserByName() {
		try {
			List<User> users = userDao.searchUserByName("UserDAO");
			assertTrue(!users.isEmpty());
			Assert.assertEquals(createUser().getUUID(), userDao.searchUserByName("UserDAO").get(0).getUUID());
		} catch (RepositoryException e) {
			Assert.fail("Could no search for users.");
		}
	}

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
	}

	/**
	 * R from CRUD. Test select User with given id.
	 */
	@Test
	public void test4GetUserById() {
		try {
			Assert.assertEquals(userDao.getUserById("fasfasfgas").getName(),
					createUser().getName());
		} catch (RepositoryException e) {
			Assert.fail("Could not search for User.");
		}
	}

	/**
	 * U from CRUD. Test User update.
	 */
	@Test
	public void test5UpdateUser() {
		User u = createUser();
		u.setName("testUser");
		try {
			userDao.updateUser(u);
			assertTrue(true);
		} catch (RepositoryException e) {
			Assert.fail("Could not update User.");
		}
	}
}

