package edu.msg.bookland.server.model;

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
 * Book model class.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 *
 */

@Entity
@DiscriminatorValue("1")
public class Book extends Publication {

	@Transient
	private static final long serialVersionUID = -5590379529535305833L;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "publications_authors", joinColumns=@JoinColumn(name = "publications_uuid"), inverseJoinColumns = @JoinColumn(name = "authors_uuid"))
	private List<Author> authors;

	public Book() {
		
		authors = new ArrayList<Author>();
		borrow = new ArrayList<>();
	}
	
	public Book(Book book){
		setUUID(book.getUUID());
		setCopiesLeft(book.getCopiesLeft());
		setNumberOfCopies(book.getNumberOfCopies());
		setPublisher(book.getPublisher());
		setReleaseDate(book.getReleaseDate());
		setTitle(book.getTitle());
		authors=new ArrayList<>();
		for(Author a:book.getAuthors()){
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
		return "Book: " + ss + ", releaseDate " + year + ", " + authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

}
