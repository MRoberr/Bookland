package edu.msg.bookland.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.msg.bookland.model.Author;
import edu.msg.bookland.repository.AuthorDAO;
import edu.msg.bookland.repository.RepositoryException;

/**
 * 
 * AuthorDAO implemented with Hibernate and used JPQL language
 */
public class HibernateAuthorDAO implements AuthorDAO {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public List<Author> getAllAuthors() throws RepositoryException {

		TypedQuery<Author> query = entityManager.createQuery("Select a from Author a", Author.class);
		List<Author> authors = query.getResultList();
		return authors;
	}

	@Override
	public void insertAuthor(Author author) throws RepositoryException {
		entityManager.getTransaction().begin();
		entityManager.persist(author);
		entityManager.getTransaction().commit();
	}

	@Override
	public void updateAuthor(Author author) throws RepositoryException {
		entityManager.getTransaction().begin();
		Author author2 = entityManager.find(Author.class, author.getUUID());
		author2.setName(author.getName());
		entityManager.getTransaction().commit();

	}

	@Override
	public void deleteAuthor(Author author) throws RepositoryException {
		entityManager.getTransaction().begin();
		Author author2 = entityManager.find(Author.class, author.getUUID());
		entityManager.remove(author2);
		entityManager.getTransaction().commit();

	}

	@Override
	public Author getAuthorByUuid(String uuId) throws RepositoryException {
		Author author2 = entityManager.find(Author.class, uuId);
		return author2;
	}

}
