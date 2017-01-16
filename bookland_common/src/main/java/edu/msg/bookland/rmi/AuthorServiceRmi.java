package edu.msg.bookland.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.model.Author;

/**
 * RMI interface for Author CRUD operations.
 * 
 * @author Simo Zoltan
 *
 */
public interface AuthorServiceRmi extends Remote {

	public static final String RMI_NAME = "Author";
	public static final int RMI_PORT = 1099;

	/**
	 * This function gets all the Authors from DB.
	 * 
	 * @return List of Authors
	 * @throws RemoteException
	 */
	public List<Author> getAllAuthors() throws RemoteException;

	/**
	 * This method inserts an Author into DB.
	 * 
	 * @param author
	 * @return true, if insert successful
	 * @throws RemoteException
	 */
	public boolean insertAuthor(Author author) throws RemoteException;

	/**
	 * This method updates the Author by its id.
	 * 
	 * @param author
	 * @return true, if update successful
	 * @throws RemoteException
	 */
	public boolean updateAuthor(Author author) throws RemoteException;

	/**
	 * This method deletes the Author by its id.
	 * 
	 * @param author
	 * @return true, if delete succeeded
	 * @throws RemoteException
	 */
	public boolean deleteAuthor(Author author) throws RemoteException;

	/**
	 * This method searches for an Author with the specified name.
	 * 
	 * @param name
	 * @return List<Author>
	 * @throws RemoteException
	 */
	public List<Author> searchAuthor(String name) throws RemoteException;

}
