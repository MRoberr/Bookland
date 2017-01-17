package edu.msg.bookland.model;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	private UserPublicationId userPublicationId;
	
	
	@Column(name = "borrowing_date")
	private Date borrowingDate;
	
	@Column(name = "deadline")
	private Date deadline;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_uuid")
	private User user;
	
	@ManyToOne
	@MapsId("publicationId")
	@JoinColumn(name = "publications_uuid")
	private Publication publication;
	
	public Borrowing(){
		
	}
	
	public Borrowing(Borrowing b){
		borrowingDate=b.getBorrowingDate();
		deadline=b.getDeadline();
		
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
	
	public Publication getPublicatoin() {
		
		return publication;
	}

	@Override
	public String toString() {
		return "Borrowing [userId=" + userPublicationId.getUserId() + ", publicationId=" + userPublicationId.getPublicationId() + ", borrowingDate=" + borrowingDate
				+ ", deadline=" + deadline + "]";
	}

}
