package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.PublicationDTO;
import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.desktop.model.ConnectionModel;

/**
 * This is the controller class for different data administration requests.
 * 
 * @author Simo Zoltan
 *
 */
public class DataAdministrationController {
	private static final Logger LOGGER = Logger.getLogger(DataAdministrationController.class);

	/**
	 * This method returns the list of publications with specified title.
	 * 
	 * @param title
	 * @return Publication list
	 */
	public List<PublicationDTO> getPublications(String title) {
		try {
			return ConnectionModel.PUBLICATION_SERVICE_RMI.searchPublicationByRegexp(title);
		} catch (RemoteException e) {
			LOGGER.error("No connection when searching publications.", e);
			return null;
		}
	}

	/**
	 * This method returns list of users found with the given name.
	 * 
	 * @param title
	 * @return User list
	 */
	public List<UserDTO> getUsers(String name) {
		try {
			List<UserDTO> list= ConnectionModel.USER_SERVICE_RMI.searchUser(name);
			return list;
		} catch (RemoteException e) {
			LOGGER.error("No connection when searching publications.", e);
			return null;
		}
	}

}