package edu.msg.bookland.model;

/**
 * Tuple of a borrowing and a Publication object
 * 
 * @author Terez Sipos
 *
 */
public class Tuple {
	Borrowing borrow;
	Publication publication;

	public Tuple(Borrowing borrow, Publication publication) {
		this.borrow = borrow;
		this.publication = publication;
	}
}
