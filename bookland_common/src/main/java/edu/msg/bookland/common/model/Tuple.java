package edu.msg.bookland.common.model;

import java.io.Serializable;
/**
 * Tuple of a borrowing and a Publication object
 * 
 * @author Terez Sipos
 * @author Simo Zoltan
 *
 */
public class Tuple implements Serializable{	
	private static final long serialVersionUID = -8678286611552033616L;
	private BorrowingDTO borrow;
	private PublicationDTO publication;

	public Tuple(BorrowingDTO borrow, PublicationDTO publication) {
		this.borrow = borrow;
		this.publication = publication;
	}

	public BorrowingDTO getBorrow() {
		return borrow;
	}

	public PublicationDTO getPublication() {
		return publication;
	}

}
