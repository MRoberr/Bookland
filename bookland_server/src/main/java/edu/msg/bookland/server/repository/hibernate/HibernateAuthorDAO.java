package edu.msg.bookland.server.repository.hibernate;

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

import edu.msg.bookland.common.model.AuthorDTO;
import edu.msg.bookland.server.repository.AuthorDAO;
import edu.msg.bookland.server.repository.RepositoryException;

/**
 * 
 * AuthorDAO implemented with Hibernate and used JPQL language
 */
public class HibernateAuthorDAO implements AuthorDAO {
	private static final Logger LOGGER = Logger.getLogger(HibernateAuthorDAO.class);
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Override
	public List<AuthorDTO> getAllAuthors() throws RepositoryException {
		List<AuthorDTO> finalList = null;
		try {
			openConnection();
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<AuthorDTO> authors = builder.createQuery(AuthorDTO.class);
			Root<AuthorDTO> author = authors.from(AuthorDTO.class);
			authors.select(author);
			TypedQuery<AuthorDTO> authorTypeQuery = entityManager.createQuery(authors);
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
	public void insertAuthor(AuthorDTO author) throws RepositoryException {
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
	public void updateAuthor(AuthorDTO author) throws RepositoryException {
		try {
			openConnection();
			entityManager.getTransaction().begin();
			AuthorDTO authorDB = entityManager.find(AuthorDTO.class, author.getUUID());
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
	public void deleteAuthor(AuthorDTO author) throws RepositoryException {
		try {
			openConnection();
			entityManager.getTransaction().begin();
			AuthorDTO author2 = entityManager.find(AuthorDTO.class, author.getUUID());
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
	public AuthorDTO getAuthorByUuid(String uuId) throws RepositoryException {
		AuthorDTO authorDB;
		try {
			openConnection();
			authorDB = entityManager.find(AuthorDTO.class, uuId);
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
