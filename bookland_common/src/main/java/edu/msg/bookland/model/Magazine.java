package edu.msg.bookland.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

/**
 * This is the Magazine class as a publication.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 *
 */
@Entity
@DiscriminatorValue("2")
public class Magazine extends Publication {

	@Transient
	private static final long serialVersionUID = -5114016015626666976L;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "publications_authors", joinColumns = @JoinColumn(name = "publications_uuid"), inverseJoinColumns = @JoinColumn(name = "authors_uuid"))
	private List<Author> authors;

	public Magazine() {
		authors = new ArrayList<Author>();
	}

	public Magazine(Magazine magazine) {
		setUUID(magazine.getUUID());
		setCopiesLeft(magazine.getCopiesLeft());
		setNumberOfCopies(magazine.getNumberOfCopies());
		setPublisher(magazine.getPublisher());
		setReleaseDate(magazine.getReleaseDate());
		setTitle(magazine.getTitle());
		authors = new ArrayList<>();
		for (Author a : magazine.getAuthors()) {
			authors.add(new Author(a));
		}
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
