package edu.msg.bookland.service;

import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.server.service.UserService;

/**
 * Integration test of User CRUD.
 * 
 * @author Simo Zoltan
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
	private String temp;
			
	@Test
	public void test0Insert(){
		UserService userService;
		try {			
			userService = new UserService();
//			User user = Mockito.mock(User.class);
//			Mockito.when(user.getName()).thenReturn("admin");
//			Mockito.when(user.getEmail()).thenReturn("admin@email");
//			temp = user.getUUID();
//			Mockito.when(user.getUUID()).thenReturn(temp);
//			Mockito.when(user.getLoyaltyIndex()).thenReturn(10);
//			Mockito.when(user.getPassword()).thenReturn("admin");			
//			Mockito.when(user.getUserType()).thenReturn(UserType.ADMIN);		
//			userService.insertUser(user);		
			UserDTO u = new UserDTO("");
			u.setName("admin");
			u.setEmail("admin@email");
			u.setUUID(u.getUUID());
			u.setLoyaltyIndex(10);
			u.setPassword("password");
			u.setUserType(UserType.ADMIN);
			userService.insertUser(u);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1Login(){
		UserService userService;
		try {
			userService = new UserService();
			Assert.assertEquals(userService.login("admin", "admin"),UserType.ADMIN);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
