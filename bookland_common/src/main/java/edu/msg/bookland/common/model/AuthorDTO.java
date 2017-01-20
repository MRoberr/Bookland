package edu.msg.bookland.common.model;

/**
 * This is the AuthorDTO class corresponding to Authors in ServerModel
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */
public class AuthorDTO extends BaseEntityDTO {

	private static final long serialVersionUID = 3106992709617480273L;

	private String name;

	public AuthorDTO() {

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

}
