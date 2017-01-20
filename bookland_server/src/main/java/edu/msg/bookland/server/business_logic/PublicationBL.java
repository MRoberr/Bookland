package edu.msg.bookland.server.business_logic;

import java.util.List;

import edu.msg.bookland.server.business_logic.basic.BasicPublicationBL;
import edu.msg.bookland.server.model.Book;
import edu.msg.bookland.server.model.Magazine;
import edu.msg.bookland.server.model.Newspaper;
import edu.msg.bookland.server.model.Publication;

/**
 * Business Logic for PublicationService
 * 
 * @author sipost
 *
 */
public interface PublicationBL {
	/**
	 * static method to get an instance
	 * 
	 * @return PublicationBL instance
	 */
	public static PublicationBL getInstance() {
		return new BasicPublicationBL();
	}

	/**
	 * This method define select all for {@link Book}
	 * 
	 * @return list of all Books
	 * @throws BusinesLogicException
	 */
	List<Book> getAllBooks() throws BusinesLogicException;

	/**
	 * This method define select all for {@link Magazine}
	 * 
	 * @return list of all Magazine
	 * @throws BusinesLogicException
	 */
	List<Magazine> getAllMagazines() throws BusinesLogicException;

	/**
	 * This method define select all for {@link Newspaper}
	 * 
	 * @return list of all Newspaper
	 * @throws BusinesLogicException
	 */
	List<Newspaper> getAllNewspapers() throws BusinesLogicException;

	/**
	 * This method define select all for {@link Publication}
	 * 
	 * @return list of all Publication
	 * @throws BusinesLogicException
	 */
	List<Publication> getAllPublications() throws BusinesLogicException;

	/**
	 * This method define insert for {@link Book}
	 * 
	 * @param book
	 * @throws BusinesLogicException
	 */
	void insertBook(Book book) throws BusinesLogicException;

	/**
	 * This method define insert for {@link Magazine}
	 * 
	 * @param magazine
	 * @throws BusinesLogicException
	 */
	void insertMagazine(Magazine magazine) throws BusinesLogicException;

	/**
	 * This method define insert for {@link Newspaper}
	 * 
	 * @param newspaper
	 * @throws BusinesLogicException
	 */
	void insertNewspaper(Newspaper newspaper) throws BusinesLogicException;

	/**
	 * This method define update for {@link Book}
	 * 
	 * @param book
	 * @throws BusinesLogicException
	 */
	void updateBook(Book book) throws BusinesLogicException;

	/**
	 * This method define update for {@link Magazine}
	 * 
	 * @param magazine
	 * @throws BusinesLogicException
	 */
	void updateMagazine(Magazine magazine) throws BusinesLogicException;

	/**
	 * This method define update for {@link Newspaper}
	 * 
	 * @param newspaper
	 * @throws BusinesLogicException
	 */
	void updateNewspaper(Newspaper newspaper) throws BusinesLogicException;

	/**
	 * This method define delete for {@link Publication}
	 * 
	 * @param id
	 *            of Publication
	 * @throws BusinesLogicException
	 */
	void deletePublication(String id) throws BusinesLogicException;

	/**
	 * This method define select with condition of title for {@link Book}
	 * 
	 * @param title
	 * @return list of Books
	 * @throws BusinesLogicException
	 */
	List<Book> searchBook(String title) throws BusinesLogicException;

	/**
	 * This method define select with condition of title for {@link Magazine}
	 * 
	 * @param title
	 * @return list of Magazines
	 * @throws BusinesLogicException
	 */
	List<Magazine> searchMagazine(String title) throws BusinesLogicException;

	/**
	 * This method define select with condition of contains username for
	 * {@link Newspaper}
	 * 
	 * @param title
	 * @return list of Newspapers
	 * @throws BusinesLogicException
	 */
	List<Newspaper> searchNewspaper(String title) throws BusinesLogicException;

	/**
	 * This method define select with condition of title for {@link Publication}
	 * 
	 * @param title
	 * @return list of Publications
	 * @throws BusinesLogicException
	 */
	List<Publication> searchPublication(String title) throws BusinesLogicException;

}
