package edu.msg.bookland.busines_logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.msg.bookland.server.model.Author;
import edu.msg.bookland.server.model.Book;
import edu.msg.bookland.server.model.Borrowing;
import edu.msg.bookland.server.model.User;
import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.PublicationDAO;
import edu.msg.bookland.server.repository.UserDAO;

public class RandomTests {
	{
		
			
			
	}
	@Test
	public void test() {
		
		
		try {
			PublicationDAO dao = DAOFactory.getDAOFactory().getPublicationDAO();
			UserDAO udao = DAOFactory.getDAOFactory().getUserDAO();
			
//			dao.getAllPublications().forEach(p -> System.out.println(p.getTitle()));
//			dao.searchPublication("em").forEach(e -> System.out.println(e.getClass().getSimpleName()));
//			dao.deletePublication("1234");
//			dao.searchBook("a");
			
			Book b1 = new Book();
			b1.getUUID();
			b1.setTitle("ujbook");
			
			List<Author> b1aut = new ArrayList<>();
			Author a1 = new Author();
			a1.getUUID();
			a1.setName("ujautor1");
			Author a2 = new Author();
			a2.setUUID("c31b2b09-c902-447e-8079-c8b3125da9bd");
			a2.setName("a4");
			
			b1aut.add(a1);
			b1aut.add(a2);
			
			b1.setAuthors(b1aut);
			
//			dao.insertBook(b1);
			
			User u = udao.getUserById("123");

//			System.out.println(u.getBorrow().size());
			
			Borrowing bo = new Borrowing();
			bo.setPublication(b1);
			bo.setUserId(u.getUUID());
			
			
			u.getBorrow().remove(bo);
			
			udao.insertUser(u);
			
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
	}
}
