package edu.msg.bookland.server.business_logic.basic;

import java.util.List;

import edu.msg.bookland.server.model.Article;
import edu.msg.bookland.server.repository.ArticleDAO;
import edu.msg.bookland.server.repository.RepositoryException;
/**
 * Implements methods of {@link ArticleDAO}
 * @author Sipos Terez
 *
 */
public class BasicArticleBL implements ArticleBL {

	@Override
	public List<Article> getAllArticles() throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertArticle(Article Article) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateArticle(Article Article) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArticle(String id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Article getArticleByUuid(String uuId) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> searchArticle(String name) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
