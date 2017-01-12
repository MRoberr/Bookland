package edu.msg.bookland;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.msg.bookland.rmi.UserServiceRmi;
import edu.msg.bookland.service.UserService;

public class ServerMain {

	public static void main(String[] args) {
		initServer();
	}
	
	public static void initServer() {
		try {
			Registry registry = LocateRegistry.createRegistry(UserServiceRmi.RMI_PORT);
			

			UserService uService = new UserService();
			registry.rebind(UserServiceRmi.RMI_NAME, uService);
			System.out.println("Server online!");
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
