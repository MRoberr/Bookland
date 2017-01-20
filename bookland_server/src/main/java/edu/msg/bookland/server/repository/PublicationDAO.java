package edu.msg.bookland.server.repository;

import java.util.List;

import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.*;

public interface PublicationDAO {

	List<Book> getAllBooks() throws RepositoryException;

	List<Magazine> getAllMagazines() throws RepositoryException;

	List<Newspaper> getAllNewspapers() throws RepositoryException;

	List<Publication> getAllPublications() throws RepositoryException;

	void insertBook(Book book) throws RepositoryException;

	void insertMagazine(Magazine magazine) throws RepositoryException;

	void insertNewspaper(Newspaper newspaper) throws RepositoryException;

	void updateBook(Book book) throws RepositoryException;

	void updateMagazine(Magazine magazine) throws RepositoryException;

	void updateNewspaper(Newspaper newspaper) throws RepositoryException;

	void deletePublication(String uuid) throws RepositoryException;

	// request methods
	Publication getPublicationByUuid(String uuid) throws RepositoryException;

	List<Book> searchBook(String title) throws BusinesLogicException;

	List<Magazine> searchMagazine(String title) throws BusinesLogicException;

	List<Newspaper> searchNewspaper(String title) throws BusinesLogicException;

	List<Publication> searchPublication(String title) throws BusinesLogicException;

	int getCopiesLeft(String uuid) throws RepositoryException;

	void setCopiesLeft(String uuid) throws RepositoryException; // csookent 1el
																// + throw
}
