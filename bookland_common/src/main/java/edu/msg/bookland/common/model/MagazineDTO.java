package edu.msg.bookland.common.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This is the Magazine class as a publication.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */
public class MagazineDTO extends PublicationDTO {

	private static final long serialVersionUID = -5114016015626666976L;

	private List<AuthorDTO> authors;

	public MagazineDTO() {
		authors = new ArrayList<AuthorDTO>();
		borrow = new ArrayList<>();
	}

	public List<AuthorDTO> getAuthors() {
		return authors;
	}

	public void addAuthor(AuthorDTO author) {
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
