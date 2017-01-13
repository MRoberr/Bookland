package edu.msg.bookland.model;

import java.sql.Date;

/**
 * Publication model class.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 *
 */

public abstract class Publication extends BaseEntity {

	private static final long serialVersionUID = 2723956432127785557L;
	private String title;
	private String publisher;
	private int numberOfCopies;
	private int copiesLeft;
	protected Date releaseDate;

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
