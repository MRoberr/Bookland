package edu.msg.bookland.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * This is the ArticleDTO class corresponding to Article in ServerModel
 * 
 * @author Sipos Terez
 *
 */

@Entity
@Table(name = "articles")
public class Article extends BaseEntity {
	@Transient
	private static final long serialVersionUID = -5384319391418339346L;

	@Column(name = "title", nullable = false, unique = true)
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
