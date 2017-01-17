package edu.msg.bookland.model;

import java.util.ArrayList;
import java.util.List;

public class Tuple {
	Borrowing borrow;
	Publication publication;
	
	public Tuple(Borrowing borrow,Publication publication){
		this.borrow=borrow;
		this.publication=publication;
	}
	
	public static void main(String...args){
		Tuple t=new Tuple(new Borrowing(),new Book());
		List<Tuple> l=new ArrayList<>();
		l.add(t);
		for(Tuple tl:l){
			System.out.println(tl.borrow.getPublicationId());
		}
	}
}
