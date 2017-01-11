package edu.msg.bookland.model;

public class Newspaper extends Publication{

	private static final long serialVersionUID = -3891407649176906111L;
	
	public Newspaper() {
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		String ss=super.toString();
		return "Newspaper: "+ ss+", releaseDate "+releaseDate.getYear()+"-"+releaseDate.getMonth()+"-"+releaseDate.getDay() ;
	}

}
