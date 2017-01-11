package edu.msg.bookland.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.model.Author;

public interface AuthorServiceRmi extends Remote {

	public static final String RMI_NAME = "Author";

	public static final int RMI_PORT = 1099;

	public List<Author> getAllAuthors() throws RemoteException;

	public boolean insertAuthor(Author author) throws RemoteException;

	public boolean updateAuthor(Author author) throws RemoteException;

	public boolean deleteAuthor(Author author) throws RemoteException;

	public Author getAuthorByUUID(String uuid) throws RemoteException;

}
