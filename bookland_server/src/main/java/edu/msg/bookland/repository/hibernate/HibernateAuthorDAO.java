package edu.msg.bookland.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import edu.msg.bookland.model.Author;
import edu.msg.bookland.repository.AuthorDAO;
import edu.msg.bookland.repository.RepositoryException;

/**
 * 
 * AuthorDAO implemented with Hibernate and used JPQL language
 */
public class HibernateAuthorDAO implements AuthorDAO {
	private static final Logger LOGGER = Logger.getLogger(HibernateAuthorDAO.class);
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Override
	public List<Author> getAllAuthors() throws RepositoryException {
		List<Author> finalList = null;
		try {
			openConnection();
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Author> authors = builder.createQuery(Author.class);
			Root<Author> author = authors.from(Author.class);
			authors.select(author);
			TypedQuery<Author> authorTypeQuery = entityManager.createQuery(authors);
			finalList = authorTypeQuery.getResultList();
			LOGGER.info("Retrieved all authors");
		} catch (PersistenceException e) {
			LOGGER.error("Could not retrieve all authors", e);
			throw new RepositoryException("Could not retrieve all authors", e);
		} finally {
			closeConnection();
		}
		closeConnection();
		return finalList;
	}

	@Override
	public void insertAuthor(Author author) throws RepositoryException {
		try {
			openConnection();
			entityManager.getTransaction().begin();
			entityManager.persist(author);
			entityManager.getTransaction().commit();
			LOGGER.info("Inserted an author");
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not instert an author", e);
			throw new RepositoryException("Could not instert an author", e);
		} finally {
			closeConnection();
		}

	}

	@Override
	public void updateAuthor(Author author) throws RepositoryException {
		try {
			openConnection();
			entityManager.getTransaction().begin();
			Author authorDB = entityManager.find(Author.class, author.getUUID());
			authorDB.setName(author.getName());
			entityManager.getTransaction().commit();
			LOGGER.info("Updated author");
		} catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not update an author", e);
			throw new RepositoryException("Could not update an author", e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void deleteAuthor(Author author) throws RepositoryException {
		try {
			openConnection();
			entityManager.getTransaction().begin();
			Author author2 = entityManager.find(Author.class, author.getUUID());
			entityManager.remove(author2);
			entityManager.getTransaction().commit();
			closeConnection();
			LOGGER.info("Deleted an author");
		} catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not delete an author", e);
			throw new RepositoryException("Could not delete an author", e);
		} finally {
			closeConnection();
		}
		

	}

	@Override
	public Author getAuthorByUuid(String uuId) throws RepositoryException {
		Author authorDB;
		try {
			openConnection();
			authorDB = entityManager.find(Author.class, uuId);
			LOGGER.info("Retrieved author by id");
		} catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error("Could not retrieve an author by id", e);
			throw new RepositoryException("Could not retrieve an author by id", e);
		} finally {
			closeConnection();
		}
		
		return authorDB;
	}

	public void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void closeConnection() {
		entityManager.close();
		entityManagerFactory.close();

	}

}
