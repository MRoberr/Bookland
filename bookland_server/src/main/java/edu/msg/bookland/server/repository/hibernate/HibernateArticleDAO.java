package edu.msg.bookland.server.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import edu.msg.bookland.server.model.Article;
import edu.msg.bookland.server.model.Article_;
import edu.msg.bookland.server.repository.ArticleDAO;
import edu.msg.bookland.server.repository.RepositoryException;

/**
 * ArticleDAO implemented with Hibernate and used JPA and Criteria Api
 * 
 * @author Sipos Terez
 */
public class HibernateArticleDAO implements ArticleDAO {

	private static final Logger LOGGER = Logger.getLogger(HibernateArticleDAO.class);

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private CriteriaBuilder builder;

	public HibernateArticleDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
		entityManager = entityManagerFactory.createEntityManager();
		builder = entityManager.getCriteriaBuilder();
	}

	/*
	 * @see edu.msg.bookland.server.repository.ArticleDAO#getAllArticles()
	 */
	@Override
	public List<Article> getAllArticles() throws RepositoryException {
		try {
			entityManager.clear();
			CriteriaQuery<Article> articles = builder.createQuery(Article.class);
			Root<Article> article = articles.from(Article.class);
			articles.select(article);
			TypedQuery<Article> articleQuery = entityManager.createQuery(articles);
			List<Article> articleList = articleQuery.getResultList();
			if (articleList.isEmpty()) {
				LOGGER.error("Can't find any Article.");
				throw new RepositoryException("Can't find any Article.");
			}
			LOGGER.info("All articles sellected!");
			return articleList;
		} catch (PersistenceException e) {
			LOGGER.error("Could not get all articles", e);
			throw new RepositoryException("Could not get all articles", e);
		}
	}

	/*
	 * @see edu.msg.bookland.server.repository.ArticleDAO#insertArticle(edu.msg.
	 * bookland.server.model.Article)
	 */
	@Override
	public void insertArticle(Article article) throws RepositoryException {
		try {
			entityManager.clear();
			entityManager.getTransaction().begin();
			entityManager.persist(article);
			entityManager.getTransaction().commit();
			LOGGER.info("Article inserted");
		} catch (RollbackException e) {
			Throwable t = e.getCause();
			while ((t != null) && !(t instanceof ConstraintViolationException)) {
				t = t.getCause();
			}
			if (t instanceof ConstraintViolationException) {
				LOGGER.error("This article allready exist", e);
				throw new RepositoryException("Can't find author with specifield Id.", e);
			} else {
				LOGGER.error("Could not instert this article", e.getCause());
				throw new RepositoryException("Could not instert this article", e);
			}
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not instert an article", e);
			throw new RepositoryException("Could not instert an article", e);
		}

	}

	/*
	 * @see edu.msg.bookland.server.repository.ArticleDAO#updateArticle(edu.msg.
	 * bookland.server.model.Article)
	 */
	@Override
	public void updateArticle(Article article) throws RepositoryException {
		try {
			entityManager.clear();
			entityManager.getTransaction().begin();
			CriteriaUpdate<Article> update = builder.createCriteriaUpdate(Article.class);

			Root<Article> articleRoot = update.from(Article.class);
			update.set(articleRoot.get(Article_.title), article.getTitle());
			update.where(builder.equal(articleRoot.get(Article_.uuId), article.getUUID()));
			entityManager.createQuery(update).executeUpdate();
			entityManager.getTransaction().commit();
			LOGGER.info("Updated article");
		} catch (RollbackException e) {
			Throwable t = e.getCause();
			while ((t != null) && !(t instanceof ConstraintViolationException)) {
				t = t.getCause();
			}
			if (t instanceof ConstraintViolationException) {
				LOGGER.error("This title allready exist", e);
				throw new RepositoryException("Can't find author with specifield Id.", e);
			} else {
				LOGGER.error("Could not update this article", e.getCause());
				throw new RepositoryException("Could not update this article", e);
			}
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not update an article", e);
			throw new RepositoryException("Could not update an article", e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.repository.ArticleDAO#deleteArticle(java.lang.
	 * String)
	 */
	@Override
	public void deleteArticle(String id) throws RepositoryException {
		try {
			entityManager.clear();
			CriteriaDelete<Article> delete = builder.createCriteriaDelete(Article.class);
			Root<Article> authorRoot = delete.from(Article.class);
			delete.where(builder.equal(authorRoot.get(Article_.uuId), id));
			entityManager.createQuery(delete).executeUpdate();
			LOGGER.info("Delete author done");
		} catch (PersistenceException e) {
			LOGGER.error("Could not delete author by id", e);
			throw new RepositoryException("Could not delete author by id", e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.repository.ArticleDAO#getArticleByUuid(java.lang.
	 * String)
	 */
	@Override
	public Article getArticleByUuid(String uuId) throws RepositoryException {
		try {
			entityManager.clear();
			Article a = entityManager.find(Article.class, uuId);
			if (a == null) {
				LOGGER.error("Could not found an article by id.");
				throw new RepositoryException("Could not found an article by id.");
			}
			LOGGER.info("Retrieved article by id");
			return a;
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not retrieve an article by id", e);
			throw new RepositoryException("Could not retrieve an article by id", e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.repository.ArticleDAO#searchArticle(java.lang.
	 * String)
	 */
	@Override
	public List<Article> searchArticle(String title) throws RepositoryException {
		try {
			entityManager.clear();
			CriteriaQuery<Article> articleByName = builder.createQuery(Article.class);
			Root<Article> article = articleByName.from(Article.class);
			articleByName.select(article);
			articleByName.where(builder.like(article.get(Article_.title), '%' + title + '%'));
			TypedQuery<Article> articleQuery = entityManager.createQuery(articleByName);
			List<Article> articleList = articleQuery.getResultList();
			if (articleList.isEmpty()) {
				LOGGER.error("Could not find article by name <" + title + ">.");
				throw new RepositoryException("Could not find article by name <" + title + ">.");
			}
			LOGGER.info("Search Article done");
			return articleList;
		} catch (PersistenceException e) {
			LOGGER.error("Could not find article.", e);
			throw new RepositoryException("Could not find article.", e);
		}
	}

}
