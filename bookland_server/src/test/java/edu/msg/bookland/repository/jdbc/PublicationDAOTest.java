package edu.msg.bookland.repository.jdbc;

import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.Test;

import edu.msg.bookland.model.Book;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.model.User;
import edu.msg.bookland.repository.PublicationDAO;
import edu.msg.bookland.repository.UserDAO;
import edu.msg.bookland.repository.hibernate.HibernatePublicationDAO;
import edu.msg.bookland.repository.hibernate.HibernateUserDAO;

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
		
		List<User> users = userDAO.getAllUsers();

		System.out.println("users size: " + users.size());
		
		users.forEach(user -> { 
			
		System.out.println(user.getName());
		System.out.println(user.getBorrow().size());
//		user.getBorrows().forEach(borrow -> System.out.println("-----" + borrow.getPublicatoin().getTitle()));
		});
	}
}
