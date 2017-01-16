package edu.msg.bookland.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.bookland.model.Book;
import edu.msg.bookland.model.Magazine;
import edu.msg.bookland.model.Newspaper;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.rmi.PublicationServiceRmi;

public class PublicationService extends UnicastRemoteObject implements PublicationServiceRmi {

	private static final long serialVersionUID = 3284877157625860710L;

	public PublicationService() throws RemoteException {
		super();

	}

	@Override
	public List<Book> getAllBooks() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Magazine> getAllMagazines() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Newspaper> getAllNewspapers() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publication> getAllPublications() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertBook(Book book) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertMagazine(Magazine magazine) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertNewspaper(Newspaper newspaper) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBook(Book book) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMagazine(Magazine magazine) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateNewspaper(Newspaper newspaper) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBook(Book book) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMagazine(Magazine magazine) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNewspaper(Newspaper newspaper) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Publication> searchPublicationByRegexp(String regex) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPublicationCopiesLeft(String uuid) {
		return 0;
	}

	public boolean setPublicationCopiesLeft(String uuid) {
		return false;
	}

	public Publication getPublicationByUuid(String uuid){
		return null;
	}

	@Override
	public Book searchBook(String title) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Magazine searchMagazin(String title) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Newspaper searchNewspaper(String title) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
