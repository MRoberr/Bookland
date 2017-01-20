package edu.msg.bookland.server.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Publication model class.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 *
 */
@Entity
@Table(name = "publications")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Publication extends BaseEntity {

	@Transient
	private static final long serialVersionUID = 2723956432127785557L;
	
	@Column(name = "title", nullable = false, unique = true)
	private String title;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "nr_of_copies")
	private int numberOfCopies;
	
	@Column(name = "copies_left")
	private int copiesLeft;
	
	@Column(name = "release_date")
	protected Date releaseDate;
	
	@OneToMany(mappedBy = "publication")
	protected List<Borrowing> borrow;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public int getCopiesLeft() {
		return copiesLeft;
	}

	public void setCopiesLeft(int copiesLeft) {
		this.copiesLeft = copiesLeft;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "title=" + title + ", publisher=" + publisher + ", numberOfCopies=" + numberOfCopies + ", copiesLeft="
				+ copiesLeft;
	}

}
