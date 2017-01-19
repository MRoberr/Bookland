package edu.msg.bookland.server.repository;

import java.util.List;

import edu.msg.bookland.common.model.BookDTO;
import edu.msg.bookland.common.model.MagazineDTO;
import edu.msg.bookland.common.model.NewspaperDTO;
import edu.msg.bookland.common.model.PublicationDTO;

public interface PublicationDAO {

	
	List<BookDTO> getAllBooks() throws RepositoryException;
	List<MagazineDTO> getAllMagazines() throws RepositoryException;
	List<NewspaperDTO> getAllNewspapers() throws RepositoryException;	
	List<PublicationDTO> getAllPublications() throws RepositoryException;
	
	void insertBook(BookDTO book) throws RepositoryException;
	void insertMagazine(MagazineDTO magazine) throws RepositoryException;
	void insertNewspaper(NewspaperDTO newspaper) throws RepositoryException;
	
	void updateBook(BookDTO book) throws RepositoryException;
	void updateMagazine(MagazineDTO magazine) throws RepositoryException;
	void updateNewspaper(NewspaperDTO newspaper) throws RepositoryException;
	
	void deleteBook(BookDTO book) throws RepositoryException;
	void deleteMagazine(MagazineDTO magazine) throws RepositoryException;
	void deleteNewspaper(NewspaperDTO newspaper) throws RepositoryException;
	
	//request methods
	List<PublicationDTO> searchPublication(String title) throws RepositoryException;
	int getCopiesLeft(String uuid) throws RepositoryException;
	void setCopiesLeft(String uuid) throws RepositoryException; //csookent 1el + throw
	PublicationDTO getPublicationByUuid(String uuid) throws RepositoryException;
	
	
}
