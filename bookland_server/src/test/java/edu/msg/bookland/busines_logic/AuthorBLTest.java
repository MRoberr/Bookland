package edu.msg.bookland.busines_logic;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.bookland.server.business_logic.AuthorBL;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.Author;

public class AuthorBLTest {

	@Test
	public void testGetAllAuthor() {
		try {
			AuthorBL.getInstance().getAllAuthors().forEach(a -> System.out.println(a));
			assertTrue(true);
		} catch (BusinesLogicException e) {
			Assert.fail("Could no insert user.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateAuthor() {
		Author a = AuthorBL.getInstance().getAllAuthors().get(0);
		a.setName("Janos Arany");
		AuthorBL.getInstance().updateAuthor(a);
	}

	@Test
	public void insertAuthor() {
		try {
			Author a = new Author();
			a.setName("Janos");
			AuthorBL.getInstance().insertAuthor(a);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void deleteAuthor() {
		try {
			AuthorBL.getInstance().deleteAuthor("5");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
