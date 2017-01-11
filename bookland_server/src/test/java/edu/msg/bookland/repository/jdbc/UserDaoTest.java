package edu.msg.bookland.repository.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.msg.bookland.model.User;

public class UserDaoTest {
	private UserDAO user = new UserDAO();
	
	@Test
	public void selectAllUserTest(){
		List<User> selectAll = user.getAllUsers();
		System.out.println("get all users " + selectAll);
		assertEquals("select * from library_users", selectAll);
	}
}
