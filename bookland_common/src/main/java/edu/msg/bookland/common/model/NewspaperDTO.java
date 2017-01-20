package edu.msg.bookland.common.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Newspaper model class.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */
public class NewspaperDTO extends PublicationDTO {

	private static final long serialVersionUID = -3891407649176906111L;
	
	//list articlea

	public NewspaperDTO() {
		borrow = new ArrayList<>();
	}

	@Override
	public String toString() {
		String ss = super.toString();
		Calendar date = Calendar.getInstance();
		date.setTime(releaseDate);
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		int day = date.get(Calendar.DAY_OF_MONTH);
		return "Newspaper: " + ss + ", releaseDate " + year + "-" + month + "-" + day;
	}

}
