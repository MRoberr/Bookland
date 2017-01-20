package edu.msg.bookland.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.Book;
import edu.msg.bookland.common.model.MagazineDTO;
import edu.msg.bookland.common.model.NewspaperDTO;
import edu.msg.bookland.common.model.Publication;
import edu.msg.bookland.common.rmi.PublicationServiceRmi;
import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.PublicationDAO;
import edu.msg.bookland.server.repository.RepositoryException;
import edu.msg.bookland.server.repository.jdbc.JDBCUserDAO;

public class PublicationService extends UnicastRemoteObject implements PublicationServiceRmi {

	private static final long serialVersionUID = 3284877157625860710L;

	private static final Logger LOGGER = Logger.getLogger(JDBCUserDAO.class);
	private PublicationDAO pubDAO;

	public PublicationService() throws RemoteException {
		pubDAO = DAOFactory.getDAOFactory().getPublicationDAO();
	}

	@Override
	public List<Book> getAllBooks() throws RemoteException {
		try {
			return pubDAO.getAllBooks();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all books");
			return null;
		}
	}

	@Override
	public List<MagazineDTO> getAllMagazines() throws RemoteException {
		try {
			return pubDAO.getAllMagazines();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all Magazines");
			return null;
		}
	}

	@Override
	public List<NewspaperDTO> getAllNewspapers() throws RemoteException {
		try {
			return pubDAO.getAllNewspapers();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all Newspapers");
			return null;
		}
	}

	@Override
	public List<Publication> getAllPublications() throws RemoteException {
		try {
			return pubDAO.getAllPublications();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all Publications");
			return null;
		}
	}

	@Override
	public boolean insertBook(Book bookDTO) throws RemoteException {
		try {
			pubDAO.insertBook(book);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert Book");
			return false;
		}
	}

	@Override
	public boolean insertMagazine(MagazineDTO magazineDTO) throws RemoteException {
		try {
			pubDAO.insertMagazine(magazineDTO);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert Magazine");
			return false;
		}
	}

	@Override
	public boolean insertNewspaper(NewspaperDTO newspaperDTO) throws RemoteException {
		try {
			pubDAO.insertNewspaper(newspaperDTO);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert Newspaper");
			return false;
		}
	}

	@Override
	public boolean updateBook(Book bookDTO) throws RemoteException {
		try {
			pubDAO.updateBook(book);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Book");
			return false;
		}
	}

	@Override
	public boolean updateMagazine(MagazineDTO magazineDTO) throws RemoteException {
		try {
			pubDAO.updateMagazine(magazineDTO);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Magazine");
			return false;
		}
	}

	@Override
	public boolean updateNewspaper(NewspaperDTO newspaperDTO) throws RemoteException {
		try {
			pubDAO.updateNewspaper(newspaperDTO);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Newspaper");
			return false;
		}
	}

	@Override
	public boolean deleteBook(Book bookDTO) throws RemoteException {
		try {
			pubDAO.deleteBook(book);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't delete Book");
			return false;
		}
	}

	@Override
	public boolean deleteMagazine(MagazineDTO magazineDTO) throws RemoteException {
		try {
			pubDAO.deleteMagazine(magazineDTO);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't delete Magazine");
			return false;
		}
	}

	@Override
	public boolean deleteNewspaper(NewspaperDTO newspaperDTO) throws RemoteException {
		try {
			pubDAO.deleteNewspaper(newspaperDTO);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't delete Newspaper");
			return false;
		}
	}

	@Override
	public List<Publication> searchPublicationByRegexp(String regex) throws RemoteException {
		List<Publication> pubsList=new ArrayList<>();
		List<Publication> list=new ArrayList<>();
		try {
			pubsList= pubDAO.searchPublication(regex);
			for(Publication p:pubsList){
				switch (p.getClass().getSimpleName()) {
				case "Book":
					Book b=new Book((Book)p);
					list.add(b);
					break;
				case "Magazine":
					MagazineDTO m=new MagazineDTO((MagazineDTO)p);
					list.add(m);
					break;
				case "Newspaper":
					NewspaperDTO n=new NewspaperDTO((NewspaperDTO)p);
					list.add(n);
					break;
				default:
					break;
				}
			}
			return list;
		} catch (RepositoryException e) {
			return null;
		}
	}

	public int getPublicationCopiesLeft(String uuid) {
		try{
			return pubDAO.getCopiesLeft(uuid);
		} catch (RepositoryException e) {
			return -1;
		}
	}

	public boolean setPublicationCopiesLeft(String uuid) {
		return false;
	}

	public Publication getPublicationByUuid(String uuid) {
		return null;
	}

	@Override
	public Book searchBooks(String title) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MagazineDTO searchMagazin(String title) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewspaperDTO searchNewspapers(String title) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
