package edu.msg.bookland.desktop.controller;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.msg.bookland.desktop.model.ConnectionModel;
import edu.msg.bookland.model.Borrowing;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.model.User;

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
	
	public List<Borrowing> getAllPublications() throws RemoteException {
		return ConnectionModel.borrowingServiceRmi.getAllBorrows();
	}

	public boolean borrow(Borrowing borrow) throws RemoteException {
		return ConnectionModel.borrowingServiceRmi.borrowPublication(borrow);
	}	

	public List<Publication> getBackBorrowingList(User user) {
		List<Publication> listId = new ArrayList<>();
		Publication tmpPub;
		try {
			List<Borrowing> listBorrows = ConnectionModel.borrowingServiceRmi.getAllBorrows();

			for (int i = 0; i < listBorrows.size(); i++) {
				Borrowing borrowtemp = (Borrowing) listBorrows.get(i);
				if (borrowtemp.getUserId().equals(user.getUUID())) {
			//		tmpPub = ConnectionModel.borrowingServiceRmi.getBorrowByUUID(borrowtemp.getPublicationId()).get(0);
					//listId.add(tmpPub);
				}
			}
			return listId;

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return listId;
	}

	public boolean returnBookInLibrary(User user, Publication publication) {
		// List<Borrowing> listId= new ArrayList<>();
		Borrowing borrowingToReturn = new Borrowing();
		try {
			List<Borrowing> listBorrows = ConnectionModel.borrowingServiceRmi.getAllBorrows();
			for (int i = 0; i < listBorrows.size(); i++) {
				Borrowing borrowtemp = (Borrowing) listBorrows.get(i);
				if (borrowtemp.getUserId().equals(user.getUUID())
						&& (borrowtemp.getPublicationId().equals(publication.getUUID()))) {
					borrowtemp.setDeadline(java.sql.Date.valueOf(LocalDate.now()));
					borrowingToReturn = borrowtemp;
				}
			}
			return ConnectionModel.borrowingServiceRmi.returnPublication(borrowingToReturn);

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;

	}

}
