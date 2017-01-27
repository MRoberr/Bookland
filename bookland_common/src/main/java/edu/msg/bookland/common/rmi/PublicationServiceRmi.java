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
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public List<BookDTO> getAllBooks() throws RemoteException, ServiceException;

	/**
	 * This function gets all Magazines from DB.
	 * 
	 * @return Magazine List
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public List<MagazineDTO> getAllMagazines() throws RemoteException, ServiceException;

	/**
	 * This function gets all Newspapers from DB.
	 * 
	 * @return Newspaper List
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public List<NewspaperDTO> getAllNewspapers() throws RemoteException, ServiceException;

	/**
	 * This function gets all Publications from DB.
	 * 
	 * @return Publication List
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public List<PublicationDTO> getAllPublications() throws RemoteException, ServiceException;

	public List<PublicationDTO> getAllPiblicationPagination(int pageIndex, int noOfRecords)
			throws RemoteException, ServiceException;

	/**
	 * This method inserts a Book into DB.
	 * 
	 * @param book
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public void insertBook(BookDTO bookDTO) throws RemoteException, ServiceException;

	/**
	 * This method inserts a Magazine into DB.
	 * 
	 * @param magazineDTO
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public void insertMagazine(MagazineDTO magazineDTO) throws RemoteException, ServiceException;

	/**
	 * This method inserts a Newspaper into DB.
	 * 
	 * @param newspaperDTO
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public void insertNewspaper(NewspaperDTO newspaperDTO) throws RemoteException, ServiceException;

	/**
	 * This method updates the Book by its id.
	 * 
	 * @param book
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public void updateBook(BookDTO bookDTO) throws RemoteException, ServiceException;

	/**
	 * This method updates the Magazine by its id.
	 * 
	 * @param magazineDTO
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public void updateMagazine(MagazineDTO magazineDTO) throws RemoteException, ServiceException;

	/**
	 * This method updates the Newspaper by its id.
	 * 
	 * @param newspaperDTO
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public void updateNewspaper(NewspaperDTO newspaperDTO) throws RemoteException, ServiceException;

	/**
	 * This method deletes the Publciation by its id.
	 * 
	 * @param book
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public void deletePublication(String publicationId) throws RemoteException, ServiceException;

	/**
	 * This method searches for Books with the specified title.
	 * 
	 * @param uuid
	 * @return Book List
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public List<BookDTO> searchBooks(String title) throws RemoteException, ServiceException;

	/**
	 * This method searches for Magazines with the specified title.
	 * 
	 * @param uuid
	 * @return Magazine List
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public List<MagazineDTO> searchMagazins(String title) throws RemoteException, ServiceException;

	/**
	 * This method searches for Newspapers with the specified uuid.
	 * 
	 * @param uuid
	 * @return Newspaper List
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public List<NewspaperDTO> searchNewspapers(String title) throws RemoteException, ServiceException;

	/**
	 * This method searches for all Publications with the specified title using
	 * regex.
	 * 
	 * @param regex
	 * @return Publication List
	 * @throws RemoteException,
	 *             when connection through RMI failed
	 * @throws ServiceException,
	 *             when Server could not finish request
	 */
	public List<PublicationDTO> searchPublicationsByRegexp(String regex) throws RemoteException, ServiceException;

}