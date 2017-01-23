package edu.msg.bookland.busines_logic;

import org.junit.Test;

import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.PublicationDAO;

public class RandomTests {
	{
		
			
			
	}
	@Test
	public void test() {
		
		
		try {
			PublicationDAO dao = DAOFactory.getDAOFactory().getPublicationDAO();
		
//			dao.getAllPublications().forEach(p -> System.out.println(p.getTitle()));
//			dao.searchPublication("em").forEach(e -> System.out.println(e.getClass().getSimpleName()));
//			dao.deletePublication("1234");
//			dao.searchBook("a");
			System.out.print("asdasd1");
			System.out.println("\b ");
		} catch (Exception e) {

			e.printStackTrace();
			
		}
	}
}
