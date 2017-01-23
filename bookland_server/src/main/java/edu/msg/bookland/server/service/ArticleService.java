package edu.msg.bookland.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.ArticleDTO;
import edu.msg.bookland.common.model.ServiceException;
import edu.msg.bookland.common.rmi.ArticleServiceRmi;
import edu.msg.bookland.server.business_logic.ArticleBL;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.Article;

public class ArticleService extends UnicastRemoteObject implements ArticleServiceRmi {

	private static final long serialVersionUID = -6452060159188405953L;
	private static final Logger LOGGER = Logger.getLogger(ArticleService.class);
	private ArticleBL articleBL;

	protected ArticleService() throws RemoteException {
		super();
		articleBL = ArticleBL.getInstance();
	}

	@Override
	public List<ArticleDTO> getAllArticles() throws RemoteException, ServiceException {
		List<Article> articles;
		try {
			articles = articleBL.getAllArticles();
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return MappingService.articleToDTO(articles);
	}

	@Override
	public void insertArticle(ArticleDTO articleDTO) throws RemoteException, ServiceException {
		try {
			articleBL.insertArticle(MappingService.DTOToArticle(articleDTO));
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateArticle(ArticleDTO articleDTO) throws RemoteException, ServiceException {
		try {
			articleBL.updateArticle(MappingService.DTOToArticle(articleDTO));
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void deleteArticle(String articleId) throws RemoteException, ServiceException {
		try {
			articleBL.deleteArticle(articleId);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<ArticleDTO> searchArticle(String name) throws RemoteException, ServiceException {
		List<Article> articles;
		try {
			articles = articleBL.searchArticle(name);
		} catch (BusinesLogicException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return MappingService.articleToDTO(articles);
	}

}
