package edu.msg.bookland.repository.jdbc;

import java.util.List;

import org.junit.Test;

import edu.msg.bookland.model.Book;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.repository.PublicationDAO;
import edu.msg.bookland.repository.hibernate.HibernatePublicationDAO;

public class PublicationDAOTest {
	
	private PublicationDAO publicationDAO;
	
	{
		try {

			publicationDAO = new HibernatePublicationDAO();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// @Test
	public void testGetAllPublications() {

		List<Publication> pubs = publicationDAO.getAllPublications();
		pubs.forEach(pub -> System.out.println(pub.getTitle()));
		

	}

	@Test
	public void testGet() {

		try {
			publicationDAO.getAllBooks();
			publicationDAO.getAllMagazines();
			publicationDAO.getAllNewspapers();
			publicationDAO.getAllPublications();
			
			Book book = new Book();
			book.setUUID("asdq14312");
			book.setTitle("title hahaha xD");
			
//			publicationDAO.insertBook(book);
			
			book.setTitle("haha uj");
			publicationDAO.updateBook(book);
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
