package edu.msg.bookland.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import edu.msg.bookland.model.Author;
import edu.msg.bookland.repository.AuthorDAO;
import edu.msg.bookland.repository.RepositoryException;

/**
 * 
 * AuthorDAO implemented with Hibernate and used JPQL language
 */
public class HibernateAuthorDAO implements AuthorDAO {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Override
	public List<Author> getAllAuthors() throws RepositoryException {
		openConnection();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Author> authors = builder.createQuery(Author.class);
						
		
		Root<Author> author = authors.from(Author.class);
		
		authors.select(author);

		
		TypedQuery<Author> authorTypeQuery = entityManager.createQuery(authors);
		List<Author> finalList = authorTypeQuery.getResultList();

		
		closeConnection();
		return finalList;
	}

	@Override
	public void insertAuthor(Author author) throws RepositoryException {
		openConnection();
		entityManager.getTransaction( ).begin();
		entityManager.persist(author);
		entityManager.getTransaction( ).commit();
		closeConnection();
	}

	@Override
	public void updateAuthor(Author author) throws RepositoryException {
		openConnection();
		entityManager.getTransaction( ).begin();
		Author author2 = entityManager.find(Author.class, author.getUUID());
		author2.setName(author.getName());		
		entityManager.getTransaction( ).commit();
		closeConnection();

	}

	@Override
	public void deleteAuthor(Author author) throws RepositoryException {
		openConnection();
		entityManager.getTransaction( ).begin();
		Author author2 = entityManager.find(Author.class, author.getUUID());
		entityManager.remove(author2);	
		entityManager.getTransaction( ).commit();
		closeConnection();

	}

	@Override
	public Author getAuthorByUuid(String uuId) throws RepositoryException {
		openConnection();
		Author author2 = entityManager.find(Author.class, uuId);
		closeConnection();
		return author2;
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
