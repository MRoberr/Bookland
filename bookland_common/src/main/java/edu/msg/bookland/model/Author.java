package edu.msg.bookland.model;

/**
 * This is the Author class corresponding to Authors table in the DB.
 * 
 * @author Majai Robert
 *
 */

public class Author extends BaseEntity {

	private static final long serialVersionUID = 3106992709617480273L;
	private String name;	
	
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

}
