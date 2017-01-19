package edu.msg.bookland.common.model;

import java.sql.Date;
import java.util.List;

/**
 * Publication model class.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */
public abstract class Publication extends BaseEntityDTO {

	private static final long serialVersionUID = 2723956432127785557L;

	private String title;
	private String publisher;
	private int numberOfCopies;
	private int copiesLeft;
	protected Date releaseDate;
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
