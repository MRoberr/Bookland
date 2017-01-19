package edu.msg.bookland.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.BookDTO;
import edu.msg.bookland.common.model.MagazineDTO;
import edu.msg.bookland.common.model.NewspaperDTO;
import edu.msg.bookland.common.model.PublicationDTO;
import edu.msg.bookland.common.rmi.PublicationServiceRmi;
import edu.msg.bookland.repository.DAOFactory;
import edu.msg.bookland.repository.PublicationDAO;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.repository.jdbc.JDBCUserDAO;

public class PublicationService extends UnicastRemoteObject implements PublicationServiceRmi {

	private static final long serialVersionUID = 3284877157625860710L;

	private static final Logger LOGGER = Logger.getLogger(JDBCUserDAO.class);
	private PublicationDAO pubDAO;

	public PublicationService() throws RemoteException {
		pubDAO = DAOFactory.getDAOFactory().getPublicationDAO();
	}

	@Override
	public List<BookDTO> getAllBooks() throws RemoteException {
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
	public List<PublicationDTO> getAllPublications() throws RemoteException {
		try {
			return pubDAO.getAllPublications();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get all Publications");
			return null;
		}
	}

	@Override
	public boolean insertBook(BookDTO book) throws RemoteException {
		try {
			pubDAO.insertBook(book);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert Book");
			return false;
		}
	}

	@Override
	public boolean insertMagazine(MagazineDTO magazine) throws RemoteException {
		try {
			pubDAO.insertMagazine(magazine);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert Magazine");
			return false;
		}
	}

	@Override
	public boolean insertNewspaper(NewspaperDTO newspaper) throws RemoteException {
		try {
			pubDAO.insertNewspaper(newspaper);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert Newspaper");
			return false;
		}
	}

	@Override
	public boolean updateBook(BookDTO book) throws RemoteException {
		try {
			pubDAO.updateBook(book);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Book");
			return false;
		}
	}

	@Override
	public boolean updateMagazine(MagazineDTO magazine) throws RemoteException {
		try {
			pubDAO.updateMagazine(magazine);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Magazine");
			return false;
		}
	}

	@Override
	public boolean updateNewspaper(NewspaperDTO newspaper) throws RemoteException {
		try {
			pubDAO.updateNewspaper(newspaper);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Newspaper");
			return false;
		}
	}

	@Override
	public boolean deleteBook(BookDTO book) throws RemoteException {
		try {
			pubDAO.deleteBook(book);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't delete Book");
			return false;
		}
	}

	@Override
	public boolean deleteMagazine(MagazineDTO magazine) throws RemoteException {
		try {
			pubDAO.deleteMagazine(magazine);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't delete Magazine");
			return false;
		}
	}

	@Override
	public boolean deleteNewspaper(NewspaperDTO newspaper) throws RemoteException {
		try {
			pubDAO.deleteNewspaper(newspaper);
			return true;
		} catch (RepositoryException e) {
			LOGGER.error("Can't delete Newspaper");
			return false;
		}
	}

	@Override
	public List<PublicationDTO> searchPublicationByRegexp(String regex) throws RemoteException {
		List<PublicationDTO> pubsList=new ArrayList<>();
		List<PublicationDTO> list=new ArrayList<>();
		try {
			pubsList= pubDAO.searchPublication(regex);
			for(PublicationDTO p:pubsList){
				switch (p.getClass().getSimpleName()) {
				case "Book":
					BookDTO b=new BookDTO((BookDTO)p);
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

	public PublicationDTO getPublicationByUuid(String uuid) {
		return null;
	}

	@Override
	public BookDTO searchBooks(String title) throws RemoteException {
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
