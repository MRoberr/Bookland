package edu.msg.bookland.server.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * This is the class that represents the publication borrowing.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 *
 */

@Entity
@Table(name = "publication_borrowings")

public class Borrowing implements Serializable {

	@Transient
	private static final long serialVersionUID = 482888478185204088L;

	@EmbeddedId
	private UserPublicationId userPublicationId = new UserPublicationId();

	@Column(name = "borrowing_date")
	private Date borrowingDate;

	@Column(name = "deadline")
	private Date deadline;

	@ManyToOne(cascade = {CascadeType.DETACH})
	@MapsId("publicationId")
	@JoinColumn(name = "publications_uuid")
	private Publication publication;

	public Borrowing() {
		userPublicationId=new UserPublicationId();
	}

	public Borrowing(Borrowing b) {
		userPublicationId.SetPublicationId(b.getPublicationId());
		userPublicationId.setUserId(b.getUserId());
		borrowingDate = b.getBorrowingDate();
		deadline = b.getDeadline();
	}

	public String getUserId() {
		return userPublicationId.getUserId();
	}

	public void setUserId(String userId) {

		userPublicationId.setUserId(userId);
	}

	public String getPublicationId() {

		return userPublicationId.getPublicationId();
	}

	public void setPublicationId(String publicationId) {

		userPublicationId.SetPublicationId(publicationId);
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

	public Publication getPublication() {

		return publication;
	}

	@Override
	public String toString() {
		return "Borrowing [userId=" + userPublicationId.getUserId() + ", publicationId="
				+ userPublicationId.getPublicationId() + ", borrowingDate=" + borrowingDate + ", deadline=" + deadline
				+ "]";
	}

	public UserPublicationId getUserPublicationId() {
		return userPublicationId;
	}

	public void setUserPublicationId(String userId,String publicationId) {
		this.userPublicationId =new UserPublicationId(userId, publicationId);
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
		this.setPublicationId(publication.getUUID());
	}

}
