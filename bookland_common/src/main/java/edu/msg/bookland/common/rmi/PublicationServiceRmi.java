package edu.msg.bookland.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.common.model.BookDTO;
import edu.msg.bookland.common.model.MagazineDTO;
import edu.msg.bookland.common.model.NewspaperDTO;
import edu.msg.bookland.common.model.PublicationDTO;
import edu.msg.bookland.common.model.ServiceException;

/**
 * RMI interface for Publication CRUD operations.
 * 
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */
public interface PublicationServiceRmi extends Remote {

	public static final String RMI_NAME = "Publication";

	/**
	 * This function gets all Books from DB.
	 * 
	 * @return Book List
	 * @throws RemoteException
	 */
	public List<BookDTO> getAllBooks() throws RemoteException, ServiceException;

	/**
	 * This function gets all Magazines from DB.
	 * 
	 * @return Magazine List
	 * @throws RemoteException
	 */
	public List<MagazineDTO> getAllMagazines() throws RemoteException, ServiceException;

	/**
	 * This function gets all Newspapers from DB.
	 * 
	 * @return Newspaper List
	 * @throws RemoteException
	 */
	public List<NewspaperDTO> getAllNewspapers() throws RemoteException, ServiceException;

	/**
	 * This function gets all Publications from DB.
	 * 
	 * @return Publication List
	 * @throws RemoteException
	 */
	public List<PublicationDTO> getAllPublications() throws RemoteException, ServiceException;

	/**
	 * This method inserts a Book into DB.
	 * 
	 * @param book
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void insertBook(BookDTO bookDTO) throws RemoteException, ServiceException;

	/**
	 * This method inserts a Magazine into DB.
	 * 
	 * @param magazineDTO
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void insertMagazine(MagazineDTO magazineDTO) throws RemoteException, ServiceException;

	/**
	 * This method inserts a Newspaper into DB.
	 * 
	 * @param newspaperDTO
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void insertNewspaper(NewspaperDTO newspaperDTO) throws RemoteException, ServiceException;

	/**
	 * This method updates the Book by its id.
	 * 
	 * @param book
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void updateBook(BookDTO bookDTO) throws RemoteException, ServiceException;

	/**
	 * This method updates the Magazine by its id.
	 * 
	 * @param magazineDTO
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void updateMagazine(MagazineDTO magazineDTO) throws RemoteException, ServiceException;

	/**
	 * This method updates the Newspaper by its id.
	 * 
	 * @param newspaperDTO
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void updateNewspaper(NewspaperDTO newspaperDTO) throws RemoteException, ServiceException;

	/**
	 * This method deletes the Publciation by its id.
	 * 
	 * @param book
	 * @return true, if succeeded
	 * @throws RemoteException
	 */
	public void deletePublication(String publicationId) throws RemoteException, ServiceException;

	/**
	 * This method searches for Books with the specified title.
	 * 
	 * @param uuid
	 * @return Book
	 * @throws RemoteException
	 */
	public List<BookDTO> searchBooks(String title) throws RemoteException, ServiceException;

	/**
	 * This method searches for Magazines with the specified title.
	 * 
	 * @param uuid
	 * @return Magazine
	 * @throws RemoteException
	 */
	public List<MagazineDTO> searchMagazins(String title) throws RemoteException, ServiceException;

	/**
	 * This method searches for Newspapers with the specified uuid.
	 * 
	 * @param uuid
	 * @return Newspaper
	 * @throws RemoteException
	 */
	public List<NewspaperDTO> searchNewspapers(String title) throws RemoteException, ServiceException;

	/**
	 * This method searches for all Publications with the specified title using
	 * regex.
	 * 
	 * @param regex
	 * @return Publication List
	 * @throws RemoteException
	 */
	public List<PublicationDTO> searchPublicationsByRegexp(String regex) throws RemoteException, ServiceException;

}