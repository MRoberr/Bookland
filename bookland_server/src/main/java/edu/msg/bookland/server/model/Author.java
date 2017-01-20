package edu.msg.bookland.server.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * This is the Author class corresponding to Authors table in the DB.
 * 
 * @author Majai Robert
 *
 */

@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

	@Transient
	private static final long serialVersionUID = 3106992709617480273L;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "publications_authors", joinColumns = @JoinColumn(name = "authors_uuid"), inverseJoinColumns = @JoinColumn(name = "publications_uuid"))
	private List<Publication> publicationAuthors;
	

	
	public Author() {
	}

	public Author(Author a) {
		setUUID(a.getUUID());
		name = a.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Author name=" + name;
	}

	public List<Publication> getPublicationAuthors() {
		return publicationAuthors;
	}

	public void setPublicationAuthors(List<Publication> publicationAuthors) {
		this.publicationAuthors = publicationAuthors;
	}

}
