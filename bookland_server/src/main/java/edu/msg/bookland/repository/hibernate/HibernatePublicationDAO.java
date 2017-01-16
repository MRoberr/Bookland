package edu.msg.bookland.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import edu.msg.bookland.model.Book;
import edu.msg.bookland.model.Book_;
import edu.msg.bookland.model.Magazine;
import edu.msg.bookland.model.Newspaper;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.repository.PublicationDAO;
import edu.msg.bookland.repository.RepositoryException;

public class HibernatePublicationDAO implements PublicationDAO{

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public HibernatePublicationDAO() {

		entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
		entityManager = entityManagerFactory.createEntityManager();
			
	}
	
	@Override
	public List<Book> getAllBooks() throws RepositoryException {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> books = builder.createQuery(Book.class);
		
		Root<Book> book = books.from(Book.class);
		books.select(book);
		
		TypedQuery<Book> bookQuery = entityManager.createQuery(books);
		List<Book> bookList = bookQuery.getResultList();

		return bookList;
	}

	@Override
	public List<Magazine> getAllMagazines() throws RepositoryException {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Magazine> magazines = builder.createQuery(Magazine.class);
		
		Root<Magazine> magazine = magazines.from(Magazine.class);
		magazines.select(magazine);
		
		TypedQuery<Magazine> magazineQuery = entityManager.createQuery(magazines);
		List<Magazine> magazineList = magazineQuery.getResultList();
		
		return magazineList;
	}

	@Override
	public List<Newspaper> getAllNewspapers() throws RepositoryException {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Newspaper> newspapers = builder.createQuery(Newspaper.class);
		
		Root<Newspaper> newspaper = newspapers.from(Newspaper.class);
		newspapers.select(newspaper);
		
		TypedQuery<Newspaper> newspaperQuery = entityManager.createQuery(newspapers);
		List<Newspaper> newspaperList = newspaperQuery.getResultList();
		
		return newspaperList;
	}

	@Override
	public List<Publication> getAllPublications() throws RepositoryException {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Publication> pubs = builder.createQuery(Publication.class);
		
		Root<Publication> pub = pubs.from(Publication.class);
		pubs.select(pub);
		
		TypedQuery<Publication> pubQuery = entityManager.createQuery(pubs);
		List<Publication> pubList = pubQuery.getResultList();
		
		return pubList;
	}

	@Override
	public void insertBook(Book book) throws RepositoryException {

		entityManager.getTransaction( ).begin();
		entityManager.persist(book);
		entityManager.getTransaction( ).commit();
	}

	@Override
	public void insertMagazine(Magazine magazine) throws RepositoryException {

		entityManager.getTransaction( ).begin();
		entityManager.persist(magazine);
		entityManager.getTransaction( ).commit();
	}

	@Override
	public void insertNewspaper(Newspaper newspaper) throws RepositoryException {

		entityManager.getTransaction( ).begin();
		entityManager.persist(newspaper);
		entityManager.getTransaction( ).commit();
	}

	@Override
	public void updateBook(Book book) throws RepositoryException {
//		entityManager.getTransaction().begin();
//		Book book2 = entityManager.find(Book.class, book.getUUID());
//		book2.setTitle("asddsad");
//		entityManager.getTransaction().commit();
		
		entityManager.getTransaction().begin();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Book> update = builder.createCriteriaUpdate(Book.class);
		
		Root<Book> bookRoot = update.from(Book.class);
		
		update.set(bookRoot.get(Book_.copiesLeft), book.getCopiesLeft());
		update.set(bookRoot.get(Book_.title), book.getTitle());
		update.set(bookRoot.get(Book_.releaseDate), book.getReleaseDate());
		update.set(bookRoot.get(Book_.publisher), book.getPublisher());
		
		update.where(builder.equal(bookRoot.get(Book_.uuId), book.getUUID()));
		entityManager.createQuery(update).executeUpdate();
		entityManager.getTransaction().commit();
		
	}

	@Override
	public void updateMagazine(Magazine magazine) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNewspaper(Newspaper newspaper) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(Book book) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMagazine(Magazine magazine) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNewspaper(Newspaper newspaper) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Publication> searchBublication(String name) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
