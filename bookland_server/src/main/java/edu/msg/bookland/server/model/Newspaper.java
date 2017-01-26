package edu.msg.bookland.server.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "publication_uuid")
	List<Article> articles;
	
	public Newspaper() {		
	}
	
	public Newspaper(Newspaper newspaper) {
		setUUID(newspaper.getUUID());
		setCopiesLeft(newspaper.getCopiesLeft());
		setNumberOfCopies(newspaper.getNumberOfCopies());
		setPublisher(newspaper.getPublisher());
		setReleaseDate(newspaper.getReleaseDate());
		setTitle(newspaper.getTitle());
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

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
