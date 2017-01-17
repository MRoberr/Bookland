package edu.msg.bookland.model;

import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Newspaper model class.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 *
 */
@Entity
@DiscriminatorValue("3")
public class Newspaper extends Publication{

	@Transient
	private static final long serialVersionUID = -3891407649176906111L;
	
	public Newspaper() {
		
		borrow = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		String ss=super.toString();
		Calendar date = Calendar.getInstance();
		date.setTime(releaseDate);
		int year = date.get(Calendar.YEAR);  
		int month = date.get(Calendar.MONTH); 
		int day = date.get(Calendar.DAY_OF_MONTH); 
		return "Newspaper: "+ ss+", releaseDate "+year+"-"+month+"-"+day ;
	}

}
