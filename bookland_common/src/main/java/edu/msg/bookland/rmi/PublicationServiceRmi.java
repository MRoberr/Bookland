package edu.msg.bookland.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.model.Book;
import edu.msg.bookland.model.Magazine;
import edu.msg.bookland.model.Newspaper;
import edu.msg.bookland.model.Publication;

public interface PublicationServiceRmi extends Remote {

	public static final String RMI_NAME = "Book";

	public static final int RMI_PORT = 1099;
	
	public List<Book> getAllBooks() throws RemoteException; 
	public List<Magazine> getAllMagazines() throws RemoteException;
	public List<Newspaper> getAllNewspapers() throws RemoteException;
	public List<Publication> getAllPublications() throws RemoteException;
	
	public boolean insertBook(Book book) throws RemoteException;
	public boolean insertMagazine(Magazine magazine) throws RemoteException;
	public boolean insertNewspaper(Newspaper newspaper) throws RemoteException;
	
	public boolean updateBook(Book book) throws RemoteException;
	public boolean updateMagazine(Magazine magazine) throws RemoteException;
	public boolean updateNewspaper(Newspaper newspaper) throws RemoteException;
	
	public boolean deleteBook(Book book) throws RemoteException;
	public boolean deleteMagazine(Magazine magazine) throws RemoteException;
	public boolean deleteNewspaper(Newspaper newspaper) throws RemoteException;
	
	public Book getBookByUUID(String uuid) throws RemoteException; 
	public Magazine getMagazineByUUID(String uuid) throws RemoteException; 
	public Newspaper getNewspaperByUUID(String uuid) throws RemoteException; 
	
	public List<Publication> searchPublicationByTitles(String title) throws RemoteException;	
	public List<Publication> searchPublicationByRegexp(String regex) throws RemoteException;

}