package edu.msg.bookland.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.model.Book;
import edu.msg.bookland.model.Magazine;
import edu.msg.bookland.model.Newspaper;
import edu.msg.bookland.model.Publication;

/**
 * RMI interface for Publication CRUD operations.
 * 
 * @author Simo Zoltan
 *
 */
public interface PublicationServiceRmi extends Remote {

	public static final String RMI_NAME = "Book";
	public static final int RMI_PORT = 10099;
	
	/**
	 * This function gets all the Books from DB.
	 * 
	 * @return Book List
	 * @throws RemoteException
	 */
	public List<Book> getAllBooks() throws RemoteException; 
	
	/**
	 * This function gets all the Magazines from DB.
	 * 
	 * @return Magazine List
	 * @throws RemoteException
	 */
	public List<Magazine> getAllMagazines() throws RemoteException;
	
	/**
	 * This function gets all the Newspapers from DB.
	 * 
	 * @return Newspaper List
	 * @throws RemoteException
	 */
	public List<Newspaper> getAllNewspapers() throws RemoteException;
	
	/**
	 * This function gets all the Publications from DB.
	 * 
	 * @return Publication List
	 * @throws RemoteException
	 */
	public List<Publication> getAllPublications() throws RemoteException;
	
	/**
	 * This method inserts a Book into DB.
	 * 	
	 * @param book
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean insertBook(Book book) throws RemoteException;
	
	/**
	 * This method inserts a Magazine into DB.
	 * 
	 * @param magazine
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean insertMagazine(Magazine magazine) throws RemoteException;
	
	/**
	 * This method inserts a Newspaper into DB.
	 * 
	 * @param newspaper
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean insertNewspaper(Newspaper newspaper) throws RemoteException;
	
	/**
	 * This method updates the Book by its id.
	 * 
	 * @param book
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean updateBook(Book book) throws RemoteException;
	
	/**
	 * This method updates the Magazine by its id.
	 * 
	 * @param magazine
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean updateMagazine(Magazine magazine) throws RemoteException;
	
	/**
	 * This method updates the Newspaper by its id.
	 * 
	 * @param newspaper
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean updateNewspaper(Newspaper newspaper) throws RemoteException;
	
	/**
	 * This method deletes the Book by its id.
	 * 
	 * @param book
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean deleteBook(Book book) throws RemoteException;
	
	/**
	 * This method deletes the Magazine by its id.
	 * 
	 * @param magazine
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean deleteMagazine(Magazine magazine) throws RemoteException;
	
	/**
	 * This method deletes the Newspaper by its id.
	 * 
	 * @param newspaper
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean deleteNewspaper(Newspaper newspaper) throws RemoteException;
	
	/**
	 * This method searches for a Book with the specified uuid.
	 * 
	 * @param uuid
	 * @return Book
	 * @throws RemoteException
	 */
	public Book searchBook(String title) throws RemoteException; 
	
	/**
	 * This method searches for a Magazine with the specified uuid.
	 * 
	 * @param uuid
	 * @return Magazine
	 * @throws RemoteException
	 */
	public Magazine searchMagazin(String title) throws RemoteException; 
	
	/**
	 * This method searches for a Newspaper with the specified uuid.
	 * 
	 * @param uuid
	 * @return Newspaper
	 * @throws RemoteException
	 */
	public Newspaper searchNewspaper(String title) throws RemoteException; 
	
	/**
	 * This method searches for all Publications with the specified title using regex.
	 * 
	 * @param regex
	 * @return Publication List
	 * @throws RemoteException
	 */
	public List<Publication> searchPublicationByRegexp(String regex) throws RemoteException;

	
}