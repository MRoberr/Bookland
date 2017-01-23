package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.ServiceException;
import edu.msg.bookland.common.model.UserType;
import edu.msg.bookland.desktop.RequestException;
import edu.msg.bookland.desktop.model.ConnectionModel;
import edu.msg.bookland.desktop.util.textLangProvider;

public class LoginController {
	private static final Logger LOGGER = Logger.getLogger(DataAdministrationController.class);

	public UserType userLogin(String u, String p) {
		try {
			return ConnectionModel.USER_SERVICE_RMI.login(u, p);
		} catch (ServiceException e) {
			LOGGER.error("Server refused login.", e);
			throw new RequestException(textLangProvider.INSTANCE.getProperty("loginInvalidUsernameOrPassword") + "\n" + e.getMessage());
		} catch (RemoteException e) {
			LOGGER.error("Connection with server failed at login.", e);
			throw new RequestException(e.getMessage());
		}
	}

}
