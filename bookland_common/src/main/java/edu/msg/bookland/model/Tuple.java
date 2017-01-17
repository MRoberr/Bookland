package edu.msg.bookland.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tuple of a borrowing and a Publication object
 * @author Terez Sipos
 *
 */
public class Tuple {
	Borrowing borrow;
	Publication publication;
	
	public Tuple(Borrowing borrow,Publication publication){
		this.borrow=borrow;
		this.publication=publication;
	}
}
