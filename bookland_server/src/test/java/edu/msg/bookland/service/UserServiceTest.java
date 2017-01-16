package edu.msg.bookland.service;

import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.bookland.model.User;
import edu.msg.bookland.model.UserType;
import edu.msg.bookland.util.PasswordEncrypting;

public class UserServiceTest {
	
	@Test
	public void insertTest(){
		UserService userService;
		try {
			userService = new UserService();
			User u = new User("");
			u.setName("admin");
			u.setEmail("admin@email");
			u.setLoyaltyIndex(10);
			u.setPassword(PasswordEncrypting.encrypt("password", "user"));
			u.setUserType(UserType.ADMIN);
			userService.insertUser(u);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void loginTest(){
		UserService userService;
		try {
			userService = new UserService();
			Assert.assertEquals(userService.login("admin", "admin"),UserType.ADMIN);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
