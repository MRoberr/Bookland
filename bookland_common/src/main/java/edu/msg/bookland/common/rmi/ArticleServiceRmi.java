package edu.msg.bookland.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.bookland.common.model.ArticleDTO;
import edu.msg.bookland.common.model.AuthorDTO;
import edu.msg.bookland.common.model.ServiceException;

public interface ArticleServiceRmi extends Remote {

	public static final String RMI_NAME = "Article";
	
	public List<ArticleDTO> getAllArticles() throws RemoteException, ServiceException;
	
	public void insertArticle(ArticleDTO articleDTO) throws RemoteException, ServiceException;
	
	public void updateArticle(ArticleDTO articleDTO) throws RemoteException, ServiceException;
	
	public void deleteArticle(String articleId) throws RemoteException, ServiceException;
	
	public List<ArticleDTO> searchArticle(String name) throws RemoteException, ServiceException;
}
