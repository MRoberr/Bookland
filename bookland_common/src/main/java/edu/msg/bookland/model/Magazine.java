package edu.msg.bookland.model;

import java.util.ArrayList;
import java.util.List;

public class Magazine extends Publication {

	private static final long serialVersionUID = -5114016015626666976L;

	private List<Author> authors;

	public Magazine() {		
		authors = new ArrayList<Author>();
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void addAuthor(Author author) {
		authors.add(author);
	}
	
	@Override
	public String toString() {
		String ss=super.toString();
		return "Magazine: "+ ss + authors ;
	}

}
