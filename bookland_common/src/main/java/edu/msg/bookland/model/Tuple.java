package edu.msg.bookland.model;

import java.io.Serializable;

public class Tuple implements Serializable{	
	private static final long serialVersionUID = -8678286611552033616L;
	private Borrowing borrow;
	private Publication publication;

	public Tuple(Borrowing borrow, Publication publication) {
		this.borrow = borrow;
		this.publication = publication;
	}

	public Borrowing getBorrow() {
		return borrow;
	}

	public Publication getPublication() {
		return publication;
	}

}
