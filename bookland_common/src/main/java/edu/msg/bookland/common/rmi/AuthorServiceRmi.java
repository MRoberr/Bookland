package edu.msg.bookland.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.common.model.AuthorDTO;

/**
 * RMI interface for Author CRUD operations.
 * 
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */
public interface AuthorServiceRmi extends Remote {

	public static final String RMI_NAME = "Author";
	public static final int RMI_PORT = 10099;

	/**
	 * This function gets all the Authors from DB.
	 * 
	 * @return List of Authors
	 * @throws RemoteException
	 */
	public List<AuthorDTO> getAllAuthors() throws RemoteException;

	/**
	 * This method inserts an Author into DB.
	 * 
	 * @param authorDTO
	 * @return true, if insert successful
	 * @throws RemoteException
	 */
	public void insertAuthor(AuthorDTO authorDTO) throws RemoteException;

	/**
	 * This method updates the Author by its id.
	 * 
	 * @param authorDTO
	 * @return true, if update successful
	 * @throws RemoteException
	 */
	public void updateAuthor(AuthorDTO authorDTO) throws RemoteException;

	/**
	 * This method deletes the Author by its id.
	 * 
	 * @param authorDTO
	 * @return true, if delete succeeded
	 * @throws RemoteException
	 */
	public void deleteAuthor(AuthorDTO authorDTO) throws RemoteException;

	/**
	 * This method searches for an Author with the specified name.
	 * 
	 * @param name
	 * @return List<Author>
	 * @throws RemoteException
	 */
	public List<AuthorDTO> searchAuthor(String name) throws RemoteException;

}
