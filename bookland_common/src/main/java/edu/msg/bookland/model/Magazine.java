package edu.msg.bookland.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This is the Magazine class as a publication.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 *
 */

public class Magazine extends Publication {

	private static final long serialVersionUID = -5114016015626666976L;

	private List<Author> authors;

	public Magazine() {
		authors = new ArrayList<Author>();
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
		int month = date.get(Calendar.MONTH);
		return "Magazine: " + ss + ", releaseDate " + year + "-" + month + ", " + authors;
	}

}
