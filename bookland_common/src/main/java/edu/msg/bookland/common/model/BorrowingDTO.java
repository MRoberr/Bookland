package edu.msg.bookland.common.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * This is the class that represents the publication borrowing.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */
public class BorrowingDTO implements Serializable {

	private static final long serialVersionUID = 482888478185204088L;

	private Date borrowingDate;
	private Date deadline;
	private String userId;
	private PublicationDTO publication;

	public BorrowingDTO() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId=userId;
	}

	public String getPublicationId() {
		return publication.getUUID();
	}

	public void setPublicationId(String publicationId) {
		publication.setUUID(publicationId);
	}

	public Date getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(Date borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		String out="Borrowing [";
		if(publication!=null){
			out+=publication;
		}
		return out+"borrowingDate="
				+ borrowingDate + ", deadline=" + deadline + "]";
	}

	public PublicationDTO getPublication() {
		return publication;
	}

	public void setPublication(PublicationDTO publication) {
		this.publication = publication;
	}

}
