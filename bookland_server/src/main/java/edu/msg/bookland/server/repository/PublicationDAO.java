package edu.msg.bookland.server.repository;

import java.util.List;

import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.*;

/**
 * Methods to manage publications in database * 
 * 
 * @author Róbert Levente Májai
 *
 */
public interface PublicationDAO {

	/**
	 * Queries all the books from the publications table.
	 * 
	 * @return A list of {@link Book} from database
	 * @throws RepositoryException if can't execute the query
	 */
	List<Book> getAllBooks() throws RepositoryException;

	/**
	 * Queries all the magazines from the publications table
	 * 
	 * @return A list of {@link Magazine} from the database
	 * @throws RepositoryException if can't execute query
	 */
	List<Magazine> getAllMagazines() throws RepositoryException;

	/**
	 * Queries all the newspapers from the publications table
	 * 
	 * @return A list of {@link Newspaper}
	 * @throws RepositoryException
	 */
	List<Newspaper> getAllNewspapers() throws RepositoryException;
	
	/**
	 * Queries all the publications from the database;
	 * 
	 * @return A list of {@link Publication}. Contains {@link Book}, {@link Magazine}, {@link Newspaper} types
	 * @throws RepositoryException if can't execute the query
	 */
	List<Publication> getAllPublications() throws RepositoryException;

	/**
	 * Inserts a book into the database.
	 * 
	 * @param book The whole {@link Book} object, that will be inserted
	 * @throws RepositoryException if can't insert the book
	 */
	void insertBook(Book book) throws RepositoryException;

	/**
	 * Inserts a magazine into the database
	 * 
	 * @param magazine The whole {@link Magazine} object, that will be inserted
	 * @throws RepositoryException if can't insert the magazine
	 */
	void insertMagazine(Magazine magazine) throws RepositoryException;

	/**
	 * Inserts a newspaper into the database
	 * 
	 * @param newspaper The whole {@link Newspaper} object, that will be inserted
	 * @throws RepositoryException if can't insert the newspaper
	 */
	void insertNewspaper(Newspaper newspaper) throws RepositoryException;

	/**
	 * Updates the given publication. You need to pass a whole object with no missing attributes, or the missing ones will be updated as null, if the database permits it
	 * 
	 * @param publication The whole {@link Publication} object, that will be inserted. This can be a {@link Book}, {@link Magazine} or a {@link Newspaper}
	 * @throws RepositoryException if can't execute the update on the given object
	 */
	void updatePublication(Publication publication) throws RepositoryException;

	/**
	 * Deletes the publication from the database with the given id
	 * @param uuid The id of the publication that will be deleted
	 * @throws RepositoryException if can't execute the delete operation
	 */
	void deletePublication(String uuid) throws RepositoryException;

	// request methods
	/**
	 * Returns a whole publication with the given id. 
	 * @param uuid The id of the publication that will be returned
	 * @return If it's a book, then a {@link Book} object will be returned, * If it's a magazine, then a {@link Magazine} object will be returned and if it's a newspaper, then a {@link Newspaper} object will be returend.
	 * @throws RepositoryException if can't execute the query
	 */
	Publication getPublicationByUuid(String uuid) throws RepositoryException;

	/**
	 * Searches the database for a book.
	 * 
	 * @param title 
	 * @return empty list if no books were found or a list of {@link Book}
	 * @throws RepositoryException
	 */
	List<Book> searchBook(String title) throws RepositoryException;

	List<Magazine> searchMagazine(String title) throws BusinesLogicException;

	List<Newspaper> searchNewspaper(String title) throws BusinesLogicException;

	List<Publication> searchPublication(String title) throws BusinesLogicException;

	void increaseCopiesLeft(String uuid) throws RepositoryException; // ++1

	void decreaseCopiesLeft(String uuid) throws RepositoryException;// --1
	
}
