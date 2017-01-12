package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.desktop.model.ConnectionModel;
import edu.msg.bookland.model.Publication;

public class DataAdministrationController {
	
	public List<Publication> getPublications(String title) {
		try {
			return ConnectionModel.publicationServiceRmi.searchPublicationByTitles(title);
		} catch (RemoteException e) {
			System.out.println("hiba");
		}
		return null;
	}
	
	public List<Publication> getPublications() {
		try {
			return ConnectionModel.publicationServiceRmi.getAllPublications();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

}
