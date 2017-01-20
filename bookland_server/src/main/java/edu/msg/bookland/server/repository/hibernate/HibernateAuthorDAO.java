package edu.msg.bookland.server.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import edu.msg.bookland.server.model.Author;
import edu.msg.bookland.server.model.Author_;
import edu.msg.bookland.server.repository.AuthorDAO;
import edu.msg.bookland.server.repository.RepositoryException;

/**
 * 
 * AuthorDAO implemented with Hibernate and used JPA and Criteria Api
 * 
 * @author Sipos Terez
 * @author Solomon Jozsef
 */
public class HibernateAuthorDAO implements AuthorDAO {
	private static final Logger LOGGER = Logger.getLogger(HibernateAuthorDAO.class);

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private CriteriaBuilder builder;

	public HibernateAuthorDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
		entityManager = entityManagerFactory.createEntityManager();
		builder = entityManager.getCriteriaBuilder();
	}
/*
 * @see edu.msg.bookland.server.repository.AuthorDAO#getAllAuthors()
 */
	@Override
	public List<Author> getAllAuthors() throws RepositoryException {
		try {
			CriteriaQuery<Author> authors = builder.createQuery(Author.class);
			Root<Author> Author = authors.from(Author.class);
			authors.select(Author);
			TypedQuery<Author> authorQuery = entityManager.createQuery(authors);
			List<Author> authorList = authorQuery.getResultList();
			LOGGER.info("All authors sellected!");
			return authorList;
		} catch (PersistenceException e) {
			LOGGER.error("Could not get all authors", e);
			throw new RepositoryException("Could not get all authors", e);
		}
	}
/*
 * @see edu.msg.bookland.server.repository.AuthorDAO#insertAuthor(edu.msg.bookland.server.model.Author)
 */
	@Override
	public void insertAuthor(Author author) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(author);
			entityManager.getTransaction().commit();
			LOGGER.info("Author inserted");
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not instert an author", e);
			throw new RepositoryException("Could not instert an author", e);
		}
	}
/*
 * @see edu.msg.bookland.server.repository.AuthorDAO#updateAuthor(edu.msg.bookland.server.model.Author)
 */
	@Override
	public void updateAuthor(Author author) throws RepositoryException {
		try {
			entityManager.getTransaction().begin();
			CriteriaUpdate<Author> update = builder.createCriteriaUpdate(Author.class);

			Root<Author> authorRoot = update.from(Author.class);
			update.set(authorRoot.get(Author_.name), author.getName());
			update.where(builder.equal(authorRoot.get(Author_.uuId), author.getUUID()));
			entityManager.createQuery(update).executeUpdate();
			entityManager.getTransaction().commit();
			LOGGER.info("Updated author");
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not update an author", e);
			throw new RepositoryException("Could not update an author", e);
		}
	}
/*
 * @see edu.msg.bookland.server.repository.AuthorDAO#getAuthorByUuid(java.lang.String)
 */
	@Override
	public Author getAuthorByUuid(String uuId) throws RepositoryException {
		try {
			Author a = entityManager.find(Author.class, uuId);
			LOGGER.info("Retrieved author by id");
			return a;
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not retrieve an author by id", e);
			throw new RepositoryException("Could not retrieve an author by id", e);
		}
	}
/*
 * @see edu.msg.bookland.server.repository.AuthorDAO#deleteAuthor(java.lang.String)
 */
	@Override
	public void deleteAuthor(String id) throws RepositoryException {
		try {
			CriteriaDelete<Author> delete = builder.createCriteriaDelete(Author.class);
			Root<Author> authorRoot = delete.from(Author.class);
			delete.where(builder.equal(authorRoot.get(Author_.uuId), id));
			entityManager.createQuery(delete).executeUpdate();
			LOGGER.info("Delete author done");
		} catch (PersistenceException e) {
			LOGGER.error("Could not delete author by id", e);
			throw new RepositoryException("Could not delete author by id", e);
		}
	}
/*
 * @see edu.msg.bookland.server.repository.AuthorDAO#searchAuthor(java.lang.String)
 */
	@Override
	public List<Author> searchAuthor(String name) throws RepositoryException {
		try {
			CriteriaQuery<Author> authorByName = builder.createQuery(Author.class);
			Root<Author> autor = authorByName.from(Author.class);
			authorByName.select(autor);
			authorByName.where(builder.like(autor.get(Author_.name), '%' + name + '%'));
			TypedQuery<Author> authorQuery = entityManager.createQuery(authorByName);
			List<Author> authorList = authorQuery.getResultList();
			LOGGER.info("Search Author done");
			return authorList;
		} catch (PersistenceException e) {
			LOGGER.error("Could not find author by name <" + name + ">", e);
			throw new RepositoryException("Could not find author by name <" + name + ">", e);
		}
	}

}
