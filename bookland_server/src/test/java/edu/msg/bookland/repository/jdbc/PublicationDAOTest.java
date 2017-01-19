package edu.msg.bookland.repository.jdbc;

import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.Test;

import edu.msg.bookland.common.model.Book;
import edu.msg.bookland.common.model.Publication;
import edu.msg.bookland.common.model.UserDTO;
import edu.msg.bookland.server.repository.PublicationDAO;
import edu.msg.bookland.server.repository.UserDAO;
import edu.msg.bookland.server.repository.hibernate.HibernatePublicationDAO;
import edu.msg.bookland.server.repository.hibernate.HibernateUserDAO;

public class PublicationDAOTest {
	
	private PublicationDAO publicationDAO;
	private UserDAO userDAO;
	
	{
		try {

			publicationDAO = new HibernatePublicationDAO();
			userDAO = new HibernateUserDAO();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// @Test
	public void testGetAllPublications() {

		List<Publication> pubs = publicationDAO.getAllPublications();
		pubs.forEach(pub -> System.out.println(pub.getTitle()));
		

	}

//	@Test
	public void testGet() {

		try {
			publicationDAO.getAllBooks();
			publicationDAO.getAllMagazines();
			publicationDAO.getAllNewspapers();
			publicationDAO.getAllPublications();
			
			Book book = new Book();
			book.setUUID("1klj345n1");
			book.setTitle("minusz beszuras");
			book.setCopiesLeft(11);
			book.setCopiesLeft(1);
			
			
//			publicationDAO.insertBook(book);
			
			book.setTitle("haha uj");
//			publicationDAO.updateBook(book);
			publicationDAO.searchPublication("ember");
			
			publicationDAO.getCopiesLeft("1234");
			publicationDAO.setCopiesLeft("1234");
			
		} catch(PersistenceException e) {
			e.printStackTrace();
		}

		
	}
	
	@Test
	public void userBorrowRelationHibernate() {
		
		List<UserDTO> users = userDAO.getAllUsers();

		System.out.println("users size: " + users.size());
		
		users.forEach(user -> { 
			
		System.out.println(user.getName());
		System.out.println(user.getBorrow().size());
//		user.getBorrows().forEach(borrow -> System.out.println("-----" + borrow.getPublicatoin().getTitle()));
		});
	}
}
