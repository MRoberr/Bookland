package edu.msg.bookland.server.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
			entityManager.clear();
			CriteriaQuery<Author> authors = builder.createQuery(Author.class);
			Root<Author> Author = authors.from(Author.class);
			authors.select(Author);
			TypedQuery<Author> authorQuery = entityManager.createQuery(authors);
			List<Author> authorList = authorQuery.getResultList();
			if (authorList.isEmpty()) {
				LOGGER.error("Can't find any Author.");
				throw new RepositoryException("Can't find any Author.");
			}
			LOGGER.info("All authors sellected!");
			return authorList;
		} catch (PersistenceException e) {
			LOGGER.error("Could not get all authors", e);
			throw new RepositoryException("Could not get all authors", e);
		}
	}

	/*
	 * @see edu.msg.bookland.server.repository.AuthorDAO#insertAuthor(edu.msg.
	 * bookland.server.model.Author)
	 */
	@Override
	public void insertAuthor(Author author) throws RepositoryException {
		try {
			entityManager.clear();
			entityManager.getTransaction().begin();
			author.getUUID();
			entityManager.persist(author);
			entityManager.getTransaction().commit();
			LOGGER.info("Author inserted");
		} catch (RollbackException e) {
			Throwable t = e.getCause();
			while ((t != null) && !(t instanceof ConstraintViolationException)) {
				t = t.getCause();
			}
			if (t instanceof ConstraintViolationException) {
				LOGGER.error("This author allready exist", e);
				throw new RepositoryException(t.getCause().getMessage(), e);
			} else {
				LOGGER.error("Could not instert this author", e.getCause());
				throw new RepositoryException("Could not instert this author", e);
			}
		} catch (PersistenceException e) {
			LOGGER.error("Could not instert an author", e.getCause());
			throw new RepositoryException("Could not instert an author", e);
		}
	}

	/*
	 * @see edu.msg.bookland.server.repository.AuthorDAO#updateAuthor(edu.msg.
	 * bookland.server.model.Author)
	 */
	@Override
	public void updateAuthor(Author author) throws RepositoryException {
		try {
			entityManager.clear();
			entityManager.getTransaction().begin();
			CriteriaUpdate<Author> update = builder.createCriteriaUpdate(Author.class);

			Root<Author> authorRoot = update.from(Author.class);
			update.set(authorRoot.get(Author_.name), author.getName());
			update.where(builder.equal(authorRoot.get(Author_.uuId), author.getUUID()));
			entityManager.createQuery(update).executeUpdate();
			entityManager.getTransaction().commit();
			LOGGER.info("Updated author");
		} catch (RollbackException e) {
			Throwable t = e.getCause();
			while ((t != null) && !(t instanceof ConstraintViolationException)) {
				t = t.getCause();
			}
			if (t instanceof ConstraintViolationException) {
				LOGGER.error("This name allready exist", e);
				throw new RepositoryException(t.getCause().getMessage(), e);
			} else {
				LOGGER.error("Could not update this author", e.getCause());
				throw new RepositoryException("Could not update this author", e);
			}
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not update an author", e);
			throw new RepositoryException("Could not update an author", e);
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.server.repository.AuthorDAO#getAuthorByUuid(java.lang.
	 * String)
	 */
	@Override
	public Author getAuthorByUuid(String uuId) throws RepositoryException {
		try {
			entityManager.clear();
			Author a = entityManager.find(Author.class, uuId);
			LOGGER.info("Retrieved author by id");
			if (a == null) {
				LOGGER.error("Can't find author with specifield Id <" + uuId + ">");
				throw new RepositoryException("Can't find author with specifield Id.");
			}
			LOGGER.info("Author by Id retrived.");
			return a;
		} catch (NoResultException e) {
			LOGGER.error("Can't find author with specifield Id <" + uuId + ">");
			throw new RepositoryException("Can't find author with specifield Id.", e);
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not retrieve an author by id", e);
			throw new RepositoryException("Could not retrieve an author by id", e);
		}
	}

	/*
	 * @see edu.msg.bookland.server.repository.AuthorDAO#deleteAuthor(java.lang.
	 * String)
	 */
	@Override
	public void deleteAuthor(String id) throws RepositoryException {
		try {
			entityManager.clear();
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
	 * @see edu.msg.bookland.server.repository.AuthorDAO#searchAuthor(java.lang.
	 * String)
	 */
	@Override
	public List<Author> searchAuthor(String name) throws RepositoryException {
		try {
			entityManager.clear();
			CriteriaQuery<Author> authorByName = builder.createQuery(Author.class);
			Root<Author> autor = authorByName.from(Author.class);
			authorByName.select(autor);
			authorByName.where(builder.like(autor.get(Author_.name), '%' + name + '%'));
			TypedQuery<Author> authorQuery = entityManager.createQuery(authorByName);
			List<Author> authorList = authorQuery.getResultList();
			if(authorList.isEmpty()){
				LOGGER.error("Can't find author by name <" + name + ">.");
				throw new RepositoryException("Can't find author by name <" + name + ">.");
			}
			LOGGER.info("Search Author done");
			return authorList;
		} catch (PersistenceException e) {
			LOGGER.error("Could not find author by name <" + name + ">", e);
			throw new RepositoryException("Could not find author by name <" + name + ">", e);
		}
	}

	@Override
	public int getPublicationNumberOfAuthor(String authorId) throws RepositoryException {

		entityManager.clear();
		
		
		return 0;
	}

}
