package edu.msg.bookland.busines_logic;

import org.junit.Test;

import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.PublicationDAO;

public class RandomTests {
	
	@Test
	public void test() {
		
		
		try {

			System.out.println("number");
			PublicationDAO dao = DAOFactory.getDAOFactory().getPublicationDAO();
			System.out.println(dao.getPublicationByUuid("123456"));
			//UserDAO udao = DAOFactory.getDAOFactory().getUserDAO();
			
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
	}
}
