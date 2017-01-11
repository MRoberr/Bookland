package edu.msg.bookland.model;

public class Newspaper extends Publication{

	private static final long serialVersionUID = -3891407649176906111L;
	
	public Newspaper() {
		
		
	}
	
	@Override
	public String toString() {
		String ss=super.toString();
		return "Newspaper: "+ ss ;
	}

}
