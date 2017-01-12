package edu.msg.bookland.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.model.User;
import edu.msg.bookland.model.UserType;

public interface UserServiceRmi extends Remote {

	public static final String RMI_NAME = "User";

	public static final int RMI_PORT = 1099;
	
	public List<User> getAllUsers() throws RemoteException; 
	
	public void insertUser(User user) throws RemoteException;
	
	public void updateUser(User user) throws RemoteException;
	
	public void deleteUser(User user) throws RemoteException;
	
	public User getUserByName(String name) throws RemoteException;
	
	public User getUserByUUUID(String uuid) throws RemoteException; 
	
	public List<User> searchUser(String name) throws RemoteException;
	
	public UserType login(String name, String password) throws RemoteException;

}
