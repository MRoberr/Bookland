package edu.msg.bookland.repository.jdbc;

import java.util.List;

import org.junit.Test;

import edu.msg.bookland.model.Publication;
import edu.msg.bookland.repository.PublicationDAO;

public class PublicationDAOTest {

	PublicationDAO publicationDAO = new JdbcPublicationDAO();
	
	@Test
	public void testGetAllPublications() {	
		List<Publication> pubs = publicationDAO.getAllPublications();
		pubs.forEach(pub -> System.out.println(pub.getTitle()));
	}
}
