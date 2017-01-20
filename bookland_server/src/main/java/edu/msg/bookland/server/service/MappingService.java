package edu.msg.bookland.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.msg.bookland.common.model.AuthorDTO;
import edu.msg.bookland.common.model.BorrowingDTO;
import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.server.model.Author;
import edu.msg.bookland.server.model.Borrowing;
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

	public static Author DTOToAuthor (AuthorDTO authorDTO) {
		Author author = new Author();
		author.setUUID(authorDTO.getUUID());
		author.setName(authorDTO.getName());
		return author;
	}
}
