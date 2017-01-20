package edu.msg.bookland.server.business_logic.basic;

import java.util.List;

import edu.msg.bookland.server.business_logic.ArticleBL;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.Article;
import edu.msg.bookland.server.repository.ArticleDAO;

/**
 * Implements methods of {@link ArticleDAO}
 * 
 * @author Sipos Terez
 *
 */
public class BasicArticleBL implements ArticleBL {

	@Override
	public List<Article> getAllArticles() throws BusinesLogicException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertArticle(Article article) throws BusinesLogicException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateArticle(Article article) throws BusinesLogicException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteArticle(String id) throws BusinesLogicException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Article> searchArticle(String title) throws BusinesLogicException {
		// TODO Auto-generated method stub
		return null;
	}

}
