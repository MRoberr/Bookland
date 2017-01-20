package edu.msg.bookland.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.common.model.AuthorDTO;
import edu.msg.bookland.common.model.ServiceException;

/**
 * RMI interface for Author CRUD operations.
 * 
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */
public interface AuthorServiceRmi extends Remote {

	public static final String RMI_NAME = "Author";

	/**
	 * This function gets all the Authors from DB.
	 * 
	 * @return List of Authors
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when Server could not finish request
	 */
	public List<AuthorDTO> getAllAuthors() throws RemoteException, ServiceException;

	/**
	 * This method inserts an Author into DB.
	 * 
	 * @param authorDTO
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when Server could not finish request
	 */
	public void insertAuthor(AuthorDTO authorDTO) throws RemoteException, ServiceException;

	/**
	 * This method updates the Author by its id.
	 * 
	 * @param authorDTO
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when Server could not finish request
	 */
	public void updateAuthor(AuthorDTO authorDTO) throws RemoteException, ServiceException;

	/**
	 * This method deletes the Author by its id.
	 * 
	 * @param authorDTO
	 * @throws RemoteException, when connection through RMI failed
	 * @throws ServiceException, when Server could not finish request
	 */
	public void deleteAuthor(AuthorDTO authorDTO) throws RemoteException, ServiceException;

	/**
	 * This method searches for an Author with the specified name.
	 * 
	 * @param name
	 * @return List<Author>
	 * @throws RemoteException, when connection through RMI failed 
	 * @throws ServiceException, when Server could not finish request
	 */
	public List<AuthorDTO> searchAuthor(String name) throws RemoteException, ServiceException;

}
