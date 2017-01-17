package edu.msg.bookland.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	@Id
	@Column(name = "user_uuid", nullable = false)
	private String userId;
	
	@Id
	@Column(name = "publications_uuid", nullable = false)
	private String publicationId;
	
	@Column(name = "borrowing_date")
	private Date borrowingDate;
	
	@Column(name = "deadline")
	private Date deadline;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(String publicationId) {
		this.publicationId = publicationId;
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
		return "Borrowing [userId=" + userId + ", publicationId=" + publicationId + ", borrowingDate=" + borrowingDate
				+ ", deadline=" + deadline + "]";
	}

}
