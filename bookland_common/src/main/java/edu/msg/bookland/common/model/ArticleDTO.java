package edu.msg.bookland.common.model;

/**
 * This is the ArticleDTO class corresponding to Article in ServerModel
 * 
 * @author Sipos Terez
 *
 */
public class ArticleDTO extends BaseEntityDTO {

	private static final long serialVersionUID = -5384319391418339346L;

	private String title;

	public ArticleDTO() {

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
