package edu.msg.bookland.repository;

import java.util.List;

import edu.msg.bookland.model.Book;
import edu.msg.bookland.model.Magazine;
import edu.msg.bookland.model.Newspaper;
import edu.msg.bookland.model.Publication;

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
	
	void deleteBook(Book book) throws RepositoryException;
	void deleteMagazine(Magazine magazine) throws RepositoryException;
	void deleteNewspaper(Newspaper newspaper) throws RepositoryException;
	
}
