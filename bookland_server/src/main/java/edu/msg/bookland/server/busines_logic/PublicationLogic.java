package edu.msg.bookland.server.busines_logic;

import java.util.List;

import edu.msg.bookland.server.model.Book;
import edu.msg.bookland.server.model.Magazine;
import edu.msg.bookland.server.model.Newspaper;
import edu.msg.bookland.server.model.Publication;


public interface PublicationLogic {

	
	List<Book> getAllBooks() throws BusinesLogicException;
	List<Magazine> getAllMagazines() throws BusinesLogicException;
	List<Newspaper> getAllNewspapers() throws BusinesLogicException;	
	List<Publication> getAllPublications() throws BusinesLogicException;
	
	void insertBook(Book book) throws BusinesLogicException;
	void insertMagazine(Magazine magazine) throws BusinesLogicException;
	void insertNewspaper(Newspaper newspaper) throws BusinesLogicException;
	
	void updateBook(Book book) throws BusinesLogicException;
	void updateMagazine(Magazine magazine) throws BusinesLogicException;
	void updateNewspaper(Newspaper newspaper) throws BusinesLogicException;
	
	void deletePublication(Publication publication) throws BusinesLogicException;
	
	//request methods
	List<Publication> searchPublication(String title) throws BusinesLogicException;
	int getCopiesLeft(String uuid) throws BusinesLogicException;
	void setCopiesLeft(String uuid) throws BusinesLogicException; //csookent 1el + throw
	
	Publication getPublicationByUuid(String uuid) throws BusinesLogicException;
	
	
}
