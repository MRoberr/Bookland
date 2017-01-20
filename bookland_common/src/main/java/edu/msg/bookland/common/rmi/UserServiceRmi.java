package edu.msg.bookland.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.common.model.ServiceException;
import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.common.model.UserType;

/**
 * RMI interface for User CRUD operations and login.
 * 
 * @author Simo Zoltan
 *
 */
public interface UserServiceRmi extends Remote {

	public static final String RMI_NAME = "User";
	public static final int RMI_PORT = 10099;
	
	/**
	 * This function gets all the Users from DB.
	 * 
	 * @return
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when Server could not finish request
	 */
	public List<UserDTO> getAllUsers() throws RemoteException, ServiceException; 
	
	/**
	 * This method inserts a User into DB.
	 * 
	 * @param user
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when Server could not finish request
	 */
	public void insertUser(UserDTO user) throws RemoteException, ServiceException;
	
	/**
	 * This method updates the User by its id.
	 * 
	 * @param user
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when Server could not finish request
	 */
	public void updateUser(UserDTO user) throws RemoteException, ServiceException;
	
	/**
	 * This method deletes the User by its id.
	 * 
	 * @param user
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when Server could not finish request
	 */
	public void deleteUser(UserDTO user) throws RemoteException, ServiceException;
			
	/**
	 * This method searches for all Users with the specified name.
	 * 
	 * @param name
	 * @return list of users found
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when Server could not finish request
	 */
	public List<UserDTO> searchUser(String name) throws RemoteException, ServiceException;
	
	/**
	 * This method checks for the given Username and password pair in the DB.
	 * When the check is correct, then the login is successful.
	 * 
	 * @param name
	 * @param password
	 * @return the UserType of the logging User
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when login failed
	 */
	public UserType login(String name, String password) throws RemoteException, ServiceException;

}
