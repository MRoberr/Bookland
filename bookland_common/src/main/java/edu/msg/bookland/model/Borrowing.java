package edu.msg.bookland.model;

import java.io.Serializable;
import java.sql.Date;

public class Borrowing implements Serializable {

	private static final long serialVersionUID = 482888478185204088L;
	
	private String userId;
	private String publicationId;
	private Date borrowingDate;
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
