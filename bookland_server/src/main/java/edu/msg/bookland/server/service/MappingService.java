package edu.msg.bookland.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.msg.bookland.common.model.ArticleDTO;
import edu.msg.bookland.common.model.AuthorDTO;
import edu.msg.bookland.common.model.BookDTO;
import edu.msg.bookland.common.model.BorrowingDTO;
import edu.msg.bookland.common.model.MagazineDTO;
import edu.msg.bookland.common.model.NewspaperDTO;
import edu.msg.bookland.common.model.PublicationDTO;
import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.server.model.Article;
import edu.msg.bookland.server.model.Author;
import edu.msg.bookland.server.model.Book;
import edu.msg.bookland.server.model.Borrowing;
import edu.msg.bookland.server.model.Magazine;
import edu.msg.bookland.server.model.Newspaper;
import edu.msg.bookland.server.model.Publication;
import edu.msg.bookland.server.model.User;

/**
 * 
 * @author Sipos Terez
 * @author Solomon Jozsef
 */
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
					borrowingDTO.setUserId(u.getUUID());
					borrowingDTO.setPublication(publicationToDTOGen(b.getPublication()));
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

	public static AuthorDTO autorToDTO(Author author) {
		AuthorDTO authorDTO = new AuthorDTO();
		authorDTO.setName(author.getName());
		authorDTO.setUUID(author.getUUID());
		return authorDTO;
	}

	public static List<AuthorDTO> authorsToDTO(List<Author> authors) {
		List<AuthorDTO> authorsDTO = new ArrayList<>();
		if (authors != null && authors.size() > 0) {
			for (Author a : authors) {
				authorsDTO.add(autorToDTO(a));
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
				authors.add(DTOToAuthor(a));
			}
			return authors;
		} else {
			return Collections.emptyList();
		}

	}

	public static <D extends PublicationDTO, O extends Publication> List<D> publicationsToDTOGen(List<O> o) {

		List<D> publicationListDTO = new ArrayList<>();
		if (o != null && o.size() > 0) {
			for (O p : o) {
				publicationListDTO.add(publicationToDTOGen(p));
			}
			return publicationListDTO;
		} else {
			return Collections.emptyList();
		}
	}

	@SuppressWarnings("unchecked")
	public static <D extends PublicationDTO, O extends Publication> D publicationToDTOGen(O p) {
		D publicationDTO;
		if (p instanceof Book) {
			publicationDTO = (D) new BookDTO();
			((BookDTO) publicationDTO).setAuthors(authorsToDTO(((Book) p).getAuthors()));
		} else if (p instanceof Magazine) {
			publicationDTO = (D) new MagazineDTO();
			((MagazineDTO) publicationDTO).setAuthors(authorsToDTO(((Magazine) p).getAuthors()));
		} else {
			publicationDTO = (D) new NewspaperDTO();
			((NewspaperDTO) publicationDTO).setArticles(articleLisToDTO(((Newspaper) p).getArticles()));
		}
		publicationDTO.setUUID(p.getUUID());
		publicationDTO.setTitle(p.getTitle());
		publicationDTO.setReleaseDate(p.getReleaseDate());
		publicationDTO.setPublisher(p.getPublisher());
		publicationDTO.setNumberOfCopies(p.getNumberOfCopies());
		publicationDTO.setCopiesLeft(p.getCopiesLeft());

		return publicationDTO;

	}

	
	@SuppressWarnings("unchecked")
	public static <O extends Publication, D extends PublicationDTO> O DTOToPublication(D d) {
		O pub;
		if (d instanceof BookDTO) {
			pub = (O) new Book();
			((Book) pub).setAuthors(DTOToAuthorList(((BookDTO) d).getAuthors()));
		} else if (d instanceof MagazineDTO) {
			pub = (O) new Magazine();
			((Magazine) pub).setAuthors(DTOToAuthorList(((MagazineDTO) d).getAuthors()));
		} else {
			pub = (O) new Newspaper();
			((Newspaper) pub).setArticles(DTOLisToArticle(((NewspaperDTO) d).getArticles()));
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
		borrowing.setPublication(DTOToPublication(borrowingDTO.getPublication()));
		borrowing.setPublicationId(borrowing.getPublicationId());
		borrowing.setUserId(borrowingDTO.getUserId());
		borrowing.setUserPublicationId(borrowing.getUserId(), borrowing.getPublicationId());
		borrowing.setDeadline(borrowingDTO.getDeadline());
		borrowing.setBorrowingDate(borrowingDTO.getBorrowingDate());
		return borrowing;

	}

	public static ArticleDTO articleToDTO(Article article) {
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setUUID(article.getUUID());
		articleDTO.setTitle(article.getTitle());
		return articleDTO;
	}

	public static List<ArticleDTO> articleLisToDTO(List<Article> articles) {
		List<ArticleDTO> articlesDTO = new ArrayList<>();
		if (articles != null && articles.size() > 0) {
			for (Article a : articles) {
				articlesDTO.add(articleToDTO(a));
			}
		}
		return articlesDTO;
	}

	public static List<Article> DTOLisToArticle(List<ArticleDTO> articlesDTO) {
		List<Article> articles = new ArrayList<>();
		if (articlesDTO != null && articlesDTO.size() > 0) {
			for (ArticleDTO a : articlesDTO) {
				articles.add(DTOToArticle(a));
			}
		}
		return articles;
	}

	public static Article DTOToArticle(ArticleDTO articleDTO) {
		Article article = new Article();
		article.setUUID(articleDTO.getUUID());
		article.setTitle(articleDTO.getTitle());
		return article;
	}
}