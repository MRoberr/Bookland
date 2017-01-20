package edu.msg.bookland.busines_logic;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.bookland.server.business_logic.AuthorBL;
import edu.msg.bookland.server.business_logic.BusinesLogicException;


public class AuthorBLTest {

	@Test
	public void testGetAllAuthor() {
		try {		
			AuthorBL.getInstance().getAllAuthors().forEach(a->System.out.println(a));
			assertTrue(true);
		} catch (BusinesLogicException e) {
			Assert.fail("Could no insert user.");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
