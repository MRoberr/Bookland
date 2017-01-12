package edu.msg.bookland.service;

import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.bookland.model.UserType;

public class UserServiceTest {
	
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
