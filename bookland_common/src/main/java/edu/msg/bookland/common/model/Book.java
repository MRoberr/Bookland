package edu.msg.bookland.common.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * BookDTO model class coresponding to Book model in server.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */

public class Book extends Publication {

	private static final long serialVersionUID = -5590379529535305833L;

	private List<Author> authors;

	public Book() {

		authors = new ArrayList<Author>();
		borrow = new ArrayList<>();
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void addAuthor(Author author) {
		authors.add(author);
	}

	@Override
	public String toString() {
		String ss = super.toString();
		Calendar date = Calendar.getInstance();
		date.setTime(releaseDate);
		int year = date.get(Calendar.YEAR);
		return "Book: " + ss + ", releaseDate " + year + ", " + authors;
	}

}
