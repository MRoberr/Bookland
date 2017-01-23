package edu.msg.bookland.server.business_logic;

import java.util.List;

import edu.msg.bookland.server.business_logic.basic.BasicArticleBL;
import edu.msg.bookland.server.model.Article;

/**
 * Business Logic for ArticleService
 * 
 * @article Sipos Terez
 */
public interface ArticleBL {

	/**
	 * static method to get an instance
	 * 
	 * @return BasicArticleBL instance
	 */
	public static ArticleBL getInstance() {
		return new BasicArticleBL();
	}

	/**
	 * This method define select all for {@link Article}
	 * 
	 * @return List of all Articles from DB
	 * @throws BusinesLogicException
	 */
	List<Article> getAllArticles() throws BusinesLogicException;

	/**
	 * This method define insert for {@link Article}
	 * 
	 * @param article
	 * @throws BusinesLogicException
	 */
	void insertArticle(Article article) throws BusinesLogicException;

	/**
	 * This method define update for {@link Article}
	 * 
	 * @param article
	 * @throws BusinesLogicException
	 */
	void updateArticle(Article article) throws BusinesLogicException;

	/**
	 * This method define delete for {@link Article}
	 * 
	 * @param article
	 *            id
	 * @throws BusinesLogicException
	 */
	void deleteArticle(String id) throws BusinesLogicException;

	/**
	 * This method define select with condition for {@link Article}
	 * 
	 * @param Article
	 *            title
	 * @return Article List if exist
	 * @throws BusinesLogicException
	 */
	List<Article> searchArticle(String title) throws BusinesLogicException;
}
