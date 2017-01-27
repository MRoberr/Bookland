package edu.msg.bookland.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.BookDTO;
import edu.msg.bookland.common.model.MagazineDTO;
import edu.msg.bookland.common.model.NewspaperDTO;

import edu.msg.bookland.common.model.PublicationDTO;
import edu.msg.bookland.common.model.ServiceException;
import edu.msg.bookland.common.rmi.PublicationServiceRmi;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.business_logic.PublicationBL;
import edu.msg.bookland.server.model.Book;
import edu.msg.bookland.server.model.Magazine;
import edu.msg.bookland.server.model.Newspaper;
import edu.msg.bookland.server.model.Publication;

public class PublicationService extends UnicastRemoteObject implements PublicationServiceRmi {

	private static final long serialVersionUID = 3284877157625860710L;

	private static final Logger LOGGER = Logger.getLogger(PublicationService.class);
	private PublicationBL publicationBL;

	public PublicationService() throws RemoteException {
		publicationBL = PublicationBL.getInstance();
	}

	@Override
	public List<BookDTO> getAllBooks() throws RemoteException, ServiceException {
		List<Book> books;
		try {
			books = publicationBL.getAllBooks();
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return MappingService.<BookDTO, Book>publicationsToDTOGen(books);

	}

	@Override
	public List<MagazineDTO> getAllMagazines() throws RemoteException, ServiceException {
		List<Magazine> magazines;
		try {
			magazines = publicationBL.getAllMagazines();
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return MappingService.<MagazineDTO, Magazine>publicationsToDTOGen(magazines);
	}

	@Override
	public List<NewspaperDTO> getAllNewspapers() throws RemoteException, ServiceException {
		List<Newspaper> newspapers;
		try {
			newspapers = publicationBL.getAllNewspapers();
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return MappingService.<NewspaperDTO, Newspaper>publicationsToDTOGen(newspapers);
	}

	@Override
	public List<PublicationDTO> getAllPublications() throws RemoteException, ServiceException {
		List<Publication> publications;
		try {
			publications = publicationBL.getAllPublications();
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return MappingService.publicationsToDTOGen(publications);
	}

	public List<PublicationDTO> getAllPiblicationPagination(int pageIndex, int noOfRecords)
			throws RemoteException, ServiceException {
		List<Publication> publications;
		try {
			publications = publicationBL.getAllPiblicationPagination(pageIndex, noOfRecords);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return MappingService.publicationsToDTOGen(publications);
	}

	@Override
	public void insertBook(BookDTO bookDTO) throws RemoteException, ServiceException {

		try {
			publicationBL.insertBook(MappingService.<Book, BookDTO>DTOToPublication(bookDTO));
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void insertMagazine(MagazineDTO magazineDTO) throws RemoteException, ServiceException {
		try {
			publicationBL.insertMagazine(MappingService.<Magazine, MagazineDTO>DTOToPublication(magazineDTO));
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void insertNewspaper(NewspaperDTO newspaperDTO) throws RemoteException, ServiceException {
		try {
			publicationBL.insertNewspaper(MappingService.<Newspaper, NewspaperDTO>DTOToPublication(newspaperDTO));
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateBook(BookDTO bookDTO) throws RemoteException, ServiceException {
		try {
			publicationBL.updateBook(MappingService.<Book, BookDTO>DTOToPublication(bookDTO));
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateMagazine(MagazineDTO magazineDTO) throws RemoteException, ServiceException {
		try {
			publicationBL.updateMagazine(MappingService.<Magazine, MagazineDTO>DTOToPublication(magazineDTO));
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateNewspaper(NewspaperDTO newspaperDTO) throws RemoteException, ServiceException {
		try {
			publicationBL.updateNewspaper(MappingService.<Newspaper, NewspaperDTO>DTOToPublication(newspaperDTO));
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void deletePublication(String publicationId) throws RemoteException, ServiceException {
		try {
			publicationBL.deletePublication(publicationId);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<BookDTO> searchBooks(String title) throws RemoteException, ServiceException {
		List<Book> books;
		try {
			books = publicationBL.searchBook(title);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return MappingService.<BookDTO, Book>publicationsToDTOGen(books);
	}

	@Override
	public List<MagazineDTO> searchMagazins(String title) throws RemoteException, ServiceException {
		List<Magazine> magazines;
		try {
			magazines = publicationBL.searchMagazine(title);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return MappingService.<MagazineDTO, Magazine>publicationsToDTOGen(magazines);
	}

	@Override
	public List<NewspaperDTO> searchNewspapers(String title) throws RemoteException, ServiceException {
		List<Newspaper> newspapers;
		try {
			newspapers = publicationBL.searchNewspaper(title);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return MappingService.<NewspaperDTO, Newspaper>publicationsToDTOGen(newspapers);
	}

	@Override
	public List<PublicationDTO> searchPublicationsByRegexp(String regex) throws RemoteException, ServiceException {
		List<Publication> publications;
		try {
			publications = publicationBL.searchPublication(regex);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return MappingService.publicationsToDTOGen(publications);
	}

}
