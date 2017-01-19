package edu.msg.bookland.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.common.model.Book;
import edu.msg.bookland.common.model.Magazine;
import edu.msg.bookland.common.model.Newspaper;
import edu.msg.bookland.common.model.Publication;

/**
 * RMI interface for Publication CRUD operations.
 * 
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */
public interface PublicationServiceRmi extends Remote {

	public static final String RMI_NAME = "Publication";
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
	public void insertBook(Book book) throws RemoteException;

	/**
	 * This method inserts a Magazine into DB.
	 * 
	 * @param magazine
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void insertMagazine(Magazine magazine) throws RemoteException;

	/**
	 * This method inserts a Newspaper into DB.
	 * 
	 * @param newspaper
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void insertNewspaper(Newspaper newspaper) throws RemoteException;

	/**
	 * This method updates the Book by its id.
	 * 
	 * @param book
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void updateBook(Book book) throws RemoteException;

	/**
	 * This method updates the Magazine by its id.
	 * 
	 * @param magazine
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void updateMagazine(Magazine magazine) throws RemoteException;

	/**
	 * This method updates the Newspaper by its id.
	 * 
	 * @param newspaper
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void updateNewspaper(Newspaper newspaper) throws RemoteException;

	/**
	 * This method deletes the Book by its id.
	 * 
	 * @param book
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void deletePublication(String publicationId) throws RemoteException;

	
	
	/**
	 * This method searches for Books with the specified title.
	 * 
	 * @param uuid
	 * @return Book
	 * @throws RemoteException
	 */
	public List<Book> searchBooks(String title) throws RemoteException;

	/**
	 * This method searches for Magazines with the specified title.
	 * 
	 * @param uuid
	 * @return Magazine
	 * @throws RemoteException
	 */
	public List<Magazine> searchMagazins(String title) throws RemoteException;

	/**
	 * This method searches for Newspapers with the specified uuid.
	 * 
	 * @param uuid
	 * @return Newspaper
	 * @throws RemoteException
	 */
	public List<Newspaper> searchNewspapers(String title) throws RemoteException;

	/**
	 * This method searches for all Publications with the specified title using
	 * regex.
	 * 
	 * @param regex
	 * @return Publication List
	 * @throws RemoteException
	 */
	public List<Publication> searchPublicationsByRegexp(String regex) throws RemoteException;

}