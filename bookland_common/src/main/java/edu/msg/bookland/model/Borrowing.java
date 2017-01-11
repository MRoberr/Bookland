package edu.msg.bookland.model;

import java.sql.Date;

public class Borrowing extends BaseEntity {

	private static final long serialVersionUID = 482888478185204088L;
	
	private User user;
	private Publication publication;
	private Date borrowingDate;
	private Date deadline;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Publication getPublication() {
		return publication;
	}
	public void setPublication(Publication publication) {
		this.publication = publication;
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
		return "Borrowing [user=" + user + ", publication=" + publication + ", borrowingDate=" + borrowingDate
				+ ", deadline=" + deadline + "]";
	}

}
