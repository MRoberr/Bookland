package edu.msg.bookland.server.model;

/**
 * This is the ArticleDTO class corresponding to Article in ServerModel
 * 
 * @author Sipos Terez
 *
 */
public class Article extends BaseEntity {

	private static final long serialVersionUID = -5384319391418339346L;

	private String title;

	public Article() {

	}

	@Override
	public String toString() {
		return "Article title=" + title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
