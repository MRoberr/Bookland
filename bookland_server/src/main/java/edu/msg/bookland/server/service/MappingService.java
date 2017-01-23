package edu.msg.bookland.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.msg.bookland.common.model.AuthorDTO;
import edu.msg.bookland.common.model.BookDTO;
import edu.msg.bookland.common.model.BorrowingDTO;
import edu.msg.bookland.common.model.MagazineDTO;
import edu.msg.bookland.common.model.NewspaperDTO;
import edu.msg.bookland.common.model.PublicationDTO;
import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.server.model.Author;
import edu.msg.bookland.server.model.Book;
import edu.msg.bookland.server.model.Borrowing;
import edu.msg.bookland.server.model.Magazine;
import edu.msg.bookland.server.model.Newspaper;
import edu.msg.bookland.server.model.Publication;
import edu.msg.bookland.server.model.User;

public class MappingService {

	public static List<UserDTO> usersToDTO(List<User> users) {
		List<UserDTO> usersDTO = new ArrayList<>();

		if (users != null && users.size() > 0) {
			for (User u : users) {
				UserDTO userDTO = new UserDTO();
				userDTO.setName(u.getName());
				userDTO.setEmail(u.getEmail());
				userDTO.setLoyaltyIndex(u.getLoyaltyIndex());
				userDTO.setUUID(u.getUUID());
				userDTO.setUserType(u.getUserType());
				List<Borrowing> borrowings = u.getBorrow();
				List<BorrowingDTO> borrowingsDTO = new ArrayList<>();

				for (Borrowing b : borrowings) {
					BorrowingDTO borrowingDTO = new BorrowingDTO();
					borrowingDTO.setUserId(b.getUserId());
					borrowingDTO.setPublicationId(b.getPublicationId());
					borrowingDTO.setBorrowingDate(b.getBorrowingDate());
					borrowingDTO.setDeadline(b.getDeadline());
					borrowingsDTO.add(borrowingDTO);
				}
				userDTO.setBorrow(borrowingsDTO);
				usersDTO.add(userDTO);
			}
			return usersDTO;
		} else {
			return Collections.emptyList();
		}

	}

	public static User DTOToUser(UserDTO userDTO) {
		User user = new User();
		user.setUUID(userDTO.getUUID());
		user.setName(userDTO.getName());
		if (userDTO.getPassword() != null) {
			user.setPassword(userDTO.getPassword());
		}
		user.setLoyaltyIndex(userDTO.getLoyaltyIndex());
		user.setEmail(userDTO.getEmail());
		user.setUserType(userDTO.getUserType());
		return user;
	}

	public static List<AuthorDTO> authorsToDTO(List<Author> authors) {
		List<AuthorDTO> authorsDTO = new ArrayList<>();
		if (authors != null && authors.size() > 0) {
			for (Author a : authors) {
				AuthorDTO authorDTO = new AuthorDTO();
				authorDTO.setName(a.getName());
				authorDTO.setUUID(a.getUUID());
				authorsDTO.add(authorDTO);
			}
			return authorsDTO;
		} else {
			return Collections.emptyList();
		}
	}

	public static Author DTOToAuthor(AuthorDTO authorDTO) {
		Author author = new Author();
		author.setUUID(authorDTO.getUUID());
		author.setName(authorDTO.getName());
		return author;
	}

	public static List<Author> DTOToAuthorList(List<AuthorDTO> authorsDTO) {

		if (authorsDTO != null && authorsDTO.size() > 0) {
			List<Author> authors = new ArrayList<>();
			for (AuthorDTO a : authorsDTO) {
				Author author = new Author();
				author.setUUID(a.getUUID());
				author.setName(a.getName());
				authors.add(author);
			}
			return authors;
		} else {
			return Collections.emptyList();
		}

	}

	@SuppressWarnings("unchecked")
	public static <D extends PublicationDTO, O extends Publication> List<D> publicationToDTOGen(List<O> o) {

		List<D> publicationListDTO = new ArrayList<>();

		if (o != null && o.size() > 0) {
			for (O p : o) {
				D publicationDTO;
				if (p instanceof Book) {
					publicationDTO = (D) new BookDTO();
					((BookDTO) publicationDTO).setAuthors(authorsToDTO(((Book) p).getAuthors()));
				} else if (p instanceof Magazine) {
					publicationDTO = (D) new MagazineDTO();
					((MagazineDTO) publicationDTO).setAuthors(authorsToDTO(((Magazine) p).getAuthors()));
				} else {
					publicationDTO = (D) new NewspaperDTO();
				}
				publicationDTO.setUUID(p.getUUID());
				publicationDTO.setTitle(p.getTitle());
				publicationDTO.setReleaseDate(p.getReleaseDate());
				publicationDTO.setPublisher(p.getPublisher());
				publicationDTO.setNumberOfCopies(p.getNumberOfCopies());
				publicationDTO.setCopiesLeft(p.getCopiesLeft());
				publicationListDTO.add(publicationDTO);
			}
			return publicationListDTO;

		} else {
			return Collections.emptyList();
		}
	}

	public static List<PublicationDTO> publicationToDTO(List<Publication> publications) {

		List<PublicationDTO> publicationListDTO = new ArrayList<>();

		if (publications != null && publications.size() > 0) {
			for (Publication p : publications) {
				PublicationDTO pubDTO;
				if (p instanceof Book) {
					pubDTO = new BookDTO();
					((BookDTO) pubDTO).setAuthors(authorsToDTO(((Book) p).getAuthors()));
				} else if (p instanceof Magazine) {
					pubDTO = new MagazineDTO();
					((MagazineDTO) pubDTO).setAuthors(authorsToDTO(((Magazine) p).getAuthors()));
				} else {
					pubDTO = new NewspaperDTO();
				}
				pubDTO.setUUID(p.getUUID());
				pubDTO.setTitle(p.getTitle());
				pubDTO.setReleaseDate(p.getReleaseDate());
				pubDTO.setPublisher(p.getPublisher());
				pubDTO.setNumberOfCopies(p.getNumberOfCopies());
				pubDTO.setCopiesLeft(p.getCopiesLeft());
				publicationListDTO.add(pubDTO);
			}

			return publicationListDTO;
		} else {
			return Collections.emptyList();
		}

	}

	@SuppressWarnings("unchecked")
	public static <O extends Publication, D extends PublicationDTO> O DTOToPublication(D d) {
		O pub;
		if(d instanceof BookDTO) {
		 pub = (O) new Book();
		 ((Book) pub).setAuthors(DTOToAuthorList(((BookDTO) d).getAuthors()));
		} else if(d instanceof MagazineDTO) {
			pub = (O) new Magazine();
			((Magazine) pub).setAuthors(DTOToAuthorList(((BookDTO) d).getAuthors()));
		} else {
			pub = (O) new Newspaper();
		}
		pub.setUUID(d.getUUID());
		pub.setTitle(d.getTitle());
		pub.setReleaseDate(d.getReleaseDate());
		pub.setPublisher(d.getPublisher());
		pub.setNumberOfCopies(d.getNumberOfCopies());
		pub.setCopiesLeft(d.getCopiesLeft());
		return pub;
	}

	public static Borrowing DTOToBorrow(BorrowingDTO borrowingDTO) {
		Borrowing borrowing = new Borrowing();
		borrowing.setUserId(borrowing.getUserId());
		borrowing.setPublicationId(borrowingDTO.getPublicationId());
		borrowing.setDeadline(borrowing.getDeadline());
		borrowing.setBorrowingDate(borrowingDTO.getBorrowingDate());
		return borrowing;
		
	}
}
