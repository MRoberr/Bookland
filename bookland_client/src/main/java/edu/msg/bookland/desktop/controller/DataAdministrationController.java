package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.BookDTO;
import edu.msg.bookland.common.model.PublicationDTO;
import edu.msg.bookland.common.model.ServiceException;
import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.desktop.RequestException;
import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.desktop.model.ConnectionModel;

/**
 * This is the controller class for different data administration requests.
 * 
 * @author Simo Zoltan
 * @author Szocs Csilla
 *
 */
public class DataAdministrationController {
	private static final Logger LOGGER = Logger.getLogger(DataAdministrationController.class);

	/**
	 * This method returns the list of publications with specified title.
	 * 
	 * @param title
	 * @return Publication list
	 * @throws RequestException
	 *             when either connection or request at server failed
	 */
	public List<PublicationDTO> getPublications(String title) {
		try {
			return ConnectionModel.PUBLICATION_SERVICE_RMI.searchPublicationsByRegexp(title);
		} catch (ServiceException e) {
			LOGGER.error("Server could not search publications.", e);
			throw new RequestException(e.getMessage());
		} catch (RemoteException e) {
			LOGGER.error("Connection with server failed at publication search.", e);
			throw new RequestException(e.getMessage());
		}
	}

	/**
	 * This method returns list of users found with the given name.
	 * 
	 * @param title
	 * @return User list
	 * @throws RequestException
	 *             when either connection or request at server failed
	 */
	public List<UserDTO> getUsers(String name) {
		try {
			List<UserDTO> list = ConnectionModel.USER_SERVICE_RMI.searchUser(name);
			return list;
		} catch (ServiceException e) {
			LOGGER.error("Server could not search users.", e);
			throw new RequestException(e.getMessage());
		} catch (RemoteException e) {
			LOGGER.error("Connection with server failed at user search.", e);
			throw new RequestException(e.getMessage());
		}
	}
	/**
	 * This method creates a new user
	 * @param user
	 */
	public void createNewUser(UserDTO user) {
		
		try {
			 ConnectionModel.USER_SERVICE_RMI.insertUser(user);
		} catch (ServiceException e) {
			LOGGER.error("Server could not create new  users.", e);
			throw new RequestException(e.getMessage());
		} catch (RemoteException e) {
			LOGGER.error("Connection with server failed at create new  user.", e);
			throw new RequestException(e.getMessage());
		}
	
	}
	/**
	 * This method updates a user 
	 * @param user
	 */
	public void updateUser(UserDTO user){
		try {
			ConnectionModel.USER_SERVICE_RMI.updateUser(user);
		}  catch (ServiceException e) {
			LOGGER.error("Server could not update users.", e);
			throw new RequestException(e.getMessage());
		} catch (RemoteException e) {
			LOGGER.error("Connection with server failed at user update.", e);
			throw new RequestException(e.getMessage());
		}
	}
	/**
	 * This method deletes a user by user id
	 * @param userID
	 */
	public void deleteUser(String userID){
		try {
			ConnectionModel.USER_SERVICE_RMI.deleteUser(userID);;
		}  catch (ServiceException e) {
			LOGGER.error("Server could not delete users.", e);
			throw new RequestException(e.getMessage());
		} catch (RemoteException e) {
			LOGGER.error("Connection with server failed at user delete.", e);
			throw new RequestException(e.getMessage());
		}
	}
	public List<UserDTO>getAllUsers(){
		try {
			return ConnectionModel.USER_SERVICE_RMI.getAllUsers();
		}catch (ServiceException e) {
			LOGGER.error("no connection when gett all users", e);
			throw new RequestException(e.getMessage());
		} catch (RemoteException e) {
			LOGGER.error(" connection when gett all users", e);
			throw new RequestException(e.getMessage());
		}
		
	}
	public List<PublicationDTO> getAllPublication() {

		try {
			return ConnectionModel.PUBLICATION_SERVICE_RMI.getAllPublications();
		} catch (ServiceException e) {
			LOGGER.error("Server could not get all publication", e);
			throw new RequestException(e.getMessage());
		} catch (RemoteException e) {
			LOGGER.error("Connection with server failed at get all publication.", e);
			throw new RequestException(e.getMessage());
		}
	}
	
}