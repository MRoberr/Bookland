package edu.msg.bookland.server.business_logic.basic;

import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.server.business_logic.ArticleBL;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.Article;
import edu.msg.bookland.server.repository.ArticleDAO;
import edu.msg.bookland.server.repository.DAOFactory;
import edu.msg.bookland.server.repository.RepositoryException;

/**
 * Implements methods of {@link ArticleDAO}
 * 
 * @author Sipos Terez
 *
 */
public class BasicArticleBL implements ArticleBL {
	private static final Logger LOGGER = Logger.getLogger(BasicArticleBL.class);
	private ArticleDAO articleDAO = DAOFactory.getDAOFactory().getArticleDAO();

	@Override
	public List<Article> getAllArticles() throws BusinesLogicException {
		try {
			return articleDAO.getAllArticles();
		} catch (RepositoryException e) {
			LOGGER.error("Can't get Articles");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	@Override
	public void insertArticle(Article article) throws BusinesLogicException {
		try {
			articleDAO.insertArticle(article);
		} catch (RepositoryException e) {
			LOGGER.error("Can't insert Articles");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	@Override
	public void updateArticle(Article article) throws BusinesLogicException {
		try {
			articleDAO.updateArticle(article);
		} catch (RepositoryException e) {
			LOGGER.error("Can't update Articles");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteArticle(String id) throws BusinesLogicException {
		try {
			articleDAO.deleteArticle(id);
		} catch (RepositoryException e) {
			LOGGER.error("Can't delete Articles");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

	@Override
	public List<Article> searchArticle(String title) throws BusinesLogicException {
		try {
			return articleDAO.searchArticle(title);
		} catch (RepositoryException e) {
			LOGGER.error("Can't delete Articles");
			throw new BusinesLogicException(e.getMessage(), e);
		}
	}

}
