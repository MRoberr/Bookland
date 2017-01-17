package edu.msg.bookland.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.model.Book;
import edu.msg.bookland.model.Magazine;
import edu.msg.bookland.model.Newspaper;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.repository.DAOFactory;
import edu.msg.bookland.repository.PublicationDAO;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.repository.jdbc.JDBCUserDAO;
import edu.msg.bookland.rmi.PublicationServiceRmi;

public class PublicationService extends UnicastRemoteObject implements PublicationServiceRmi {

	private static final long serialVersionUID = 3284877157625860710L;

	private static final Logger LOGGER = Logger.getLogger(JDBCUserDAO.class);
	private PublicationDAO pubDAO;

	public PublicationService() throws RemoteException {
		pubDAO = DAOFactory.getDAOFactory().getPublicationDAO();
	}

	@Override
	public List<Book> getAllBooks() throws RemoteException {
		return pubDAO.getAllBooks();
	}

	@Override
	public List<Magazine> getAllMagazines() throws RemoteException {
		return pubDAO.getAllMagazines();
	}

	@Override
	public List<Newspaper> getAllNewspapers() throws RemoteException {
		return pubDAO.getAllNewspapers();
	}

	@Override
	public List<Publication> getAllPublications() throws RemoteException {
		return pubDAO.getAllPublications();
	}

	@Override
	public boolean insertBook(Book book) throws RemoteException {
		try{
			pubDAO.insertBook(book);
			return true;
		}catch (RepositoryException e) {
			LOGGER.error("Can't insert Book");
			return false;
		}
	}

	@Override
	public boolean insertMagazine(Magazine magazine) throws RemoteException {
		try{
			pubDAO.insertMagazine(magazine);
			return true;
		}catch (RepositoryException e) {
			LOGGER.error("Can't insert Magazine");
			return false;
		}
	}

	@Override
	public boolean insertNewspaper(Newspaper newspaper) throws RemoteException {
		try{
			pubDAO.insertNewspaper(newspaper);
			return true;
		}catch (RepositoryException e) {
			LOGGER.error("Can't insert Newspaper");
			return false;
		}
	}

	@Override
	public boolean updateBook(Book book) throws RemoteException {
		try{
			pubDAO.updateBook(book);
			return true;
		}catch (RepositoryException e) {
			LOGGER.error("Can't update Book");
			return false;
		}
	}

	@Override
	public boolean updateMagazine(Magazine magazine) throws RemoteException {
		try{
			pubDAO.updateMagazine(magazine);
			return true;
		}catch (RepositoryException e) {
			LOGGER.error("Can't update Magazine");
			return false;
		}
	}

	@Override
	public boolean updateNewspaper(Newspaper newspaper) throws RemoteException {
		try{
			pubDAO.updateNewspaper(newspaper);
			return true;
		}catch (RepositoryException e) {
			LOGGER.error("Can't update Newspaper");
			return false;
		}
	}

	@Override
	public boolean deleteBook(Book book) throws RemoteException {
		try{
			pubDAO.deleteBook(book);
			return true;
		}catch (RepositoryException e) {
			LOGGER.error("Can't delete Book");
			return false;
		}
	}

	@Override
	public boolean deleteMagazine(Magazine magazine) throws RemoteException {
		try{
			pubDAO.deleteMagazine(magazine);
			return true;
		}catch (RepositoryException e) {
			LOGGER.error("Can't delete Magazine");
			return false;
		}
	}

	@Override
	public boolean deleteNewspaper(Newspaper newspaper) throws RemoteException {
		try{
			pubDAO.deleteNewspaper(newspaper);
			return true;
		}catch (RepositoryException e) {
			LOGGER.error("Can't delete Newspaper");
			return false;
		}
	}

	@Override
	public List<Publication> searchPublicationByRegexp(String regex) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPublicationCopiesLeft(String uuid) {
		return 0;
	}

	public boolean setPublicationCopiesLeft(String uuid) {
		return false;
	}

	public Publication getPublicationByUuid(String uuid) {
		return null;
	}

	@Override
	public Book searchBook(String title) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Magazine searchMagazin(String title) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Newspaper searchNewspaper(String title) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
