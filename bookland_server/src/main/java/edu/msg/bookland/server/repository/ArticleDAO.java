package edu.msg.bookland.server.repository;

import java.util.List;

import edu.msg.bookland.server.model.Article;

/**
 * Defines database methods for Article Model
 * 
 * @Article Sipos Terez
 */
public interface ArticleDAO {
	/**
	 * This method define select all for {@link Article}
	 * 
	 * @return List of all Articles from DB
	 * @throws RepositoryException
	 */
	List<Article> getAllArticles() throws RepositoryException;

	/**
	 * This method define insert for {@link Article}
	 * 
	 * @param Article
	 * @throws RepositoryException
	 */
	void insertArticle(Article Article) throws RepositoryException;

	/**
	 * This method define update for {@link Article}
	 * 
	 * @param Article
	 * @throws RepositoryException
	 */
	void updateArticle(Article Article) throws RepositoryException;

	/**
	 * This method define delete for {@link Article}
	 * 
	 * @param Article
	 * @throws RepositoryException
	 */
	void deleteArticle(String id) throws RepositoryException;

	/**
	 * This method define select with condition for {@link Article}
	 * 
	 * @param uuId-Article uuid
	 * @return Article if exist
	 * @throws RepositoryException
	 */
	Article getArticleByUuid(String uuId) throws RepositoryException;
	
	/**
	 * This method define select with condition for {@link Article}
	 * 
	 * @param title-Article title
	 * @return List Article if exist
	 * @throws RepositoryException
	 */
	List<Article> searchArticle(String title) throws RepositoryException;
}
