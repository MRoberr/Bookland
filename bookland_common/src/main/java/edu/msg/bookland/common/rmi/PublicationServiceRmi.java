package edu.msg.bookland.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.common.model.BookDTO;
import edu.msg.bookland.common.model.MagazineDTO;
import edu.msg.bookland.common.model.NewspaperDTO;
import edu.msg.bookland.common.model.PublicationDTO;

/**
 * RMI interface for Publication CRUD operations.
 * 
 * @author Simo Zoltan
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
	public List<BookDTO> getAllBooks() throws RemoteException;

	/**
	 * This function gets all the Magazines from DB.
	 * 
	 * @return Magazine List
	 * @throws RemoteException
	 */
	public List<MagazineDTO> getAllMagazines() throws RemoteException;

	/**
	 * This function gets all the Newspapers from DB.
	 * 
	 * @return Newspaper List
	 * @throws RemoteException
	 */
	public List<NewspaperDTO> getAllNewspapers() throws RemoteException;

	/**
	 * This function gets all the Publications from DB.
	 * 
	 * @return Publication List
	 * @throws RemoteException
	 */
	public List<PublicationDTO> getAllPublications() throws RemoteException;

	/**
	 * This method inserts a Book into DB.
	 * 
	 * @param book
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean insertBook(BookDTO book) throws RemoteException;

	/**
	 * This method inserts a Magazine into DB.
	 * 
	 * @param magazine
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean insertMagazine(MagazineDTO magazine) throws RemoteException;

	/**
	 * This method inserts a Newspaper into DB.
	 * 
	 * @param newspaper
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean insertNewspaper(NewspaperDTO newspaper) throws RemoteException;

	/**
	 * This method updates the Book by its id.
	 * 
	 * @param book
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean updateBook(BookDTO book) throws RemoteException;

	/**
	 * This method updates the Magazine by its id.
	 * 
	 * @param magazine
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean updateMagazine(MagazineDTO magazine) throws RemoteException;

	/**
	 * This method updates the Newspaper by its id.
	 * 
	 * @param newspaper
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean updateNewspaper(NewspaperDTO newspaper) throws RemoteException;

	/**
	 * This method deletes the Book by its id.
	 * 
	 * @param book
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean deleteBook(BookDTO book) throws RemoteException;

	/**
	 * This method deletes the Magazine by its id.
	 * 
	 * @param magazine
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean deleteMagazine(MagazineDTO magazine) throws RemoteException;

	/**
	 * This method deletes the Newspaper by its id.
	 * 
	 * @param newspaper
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public boolean deleteNewspaper(NewspaperDTO newspaper) throws RemoteException;

	/**
	 * This method searches for a Book with the specified uuid.
	 * 
	 * @param uuid
	 * @return Book
	 * @throws RemoteException
	 */
	public BookDTO searchBook(String title) throws RemoteException;

	/**
	 * This method searches for a Magazine with the specified uuid.
	 * 
	 * @param uuid
	 * @return Magazine
	 * @throws RemoteException
	 */
	public MagazineDTO searchMagazin(String title) throws RemoteException;

	/**
	 * This method searches for a Newspaper with the specified uuid.
	 * 
	 * @param uuid
	 * @return Newspaper
	 * @throws RemoteException
	 */
	public NewspaperDTO searchNewspaper(String title) throws RemoteException;

	/**
	 * This method searches for all Publications with the specified title using
	 * regex.
	 * 
	 * @param regex
	 * @return Publication List
	 * @throws RemoteException
	 */
	public List<PublicationDTO> searchPublicationByRegexp(String regex) throws RemoteException;

}