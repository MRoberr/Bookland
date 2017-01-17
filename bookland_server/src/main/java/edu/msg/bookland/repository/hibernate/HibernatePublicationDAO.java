package edu.msg.bookland.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import edu.msg.bookland.model.Book;
import edu.msg.bookland.model.Book_;
import edu.msg.bookland.model.Magazine;
import edu.msg.bookland.model.Newspaper;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.model.Publication_;
import edu.msg.bookland.repository.PublicationDAO;
import edu.msg.bookland.repository.RepositoryException;

public class HibernatePublicationDAO implements PublicationDAO{

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private CriteriaBuilder builder;
	
	
	public HibernatePublicationDAO() {

		entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
		entityManager = entityManagerFactory.createEntityManager();
		builder = entityManager.getCriteriaBuilder();
			
	}
	
	@Override
	public List<Book> getAllBooks() throws RepositoryException {
		
		CriteriaQuery<Book> books = builder.createQuery(Book.class);
		
		Root<Book> book = books.from(Book.class);
		books.select(book);
		
		TypedQuery<Book> bookQuery = entityManager.createQuery(books);
		List<Book> bookList = bookQuery.getResultList();

		return bookList;
	}

	@Override
	public List<Magazine> getAllMagazines() throws RepositoryException {
		
		CriteriaQuery<Magazine> magazines = builder.createQuery(Magazine.class);
		
		Root<Magazine> magazine = magazines.from(Magazine.class);
		magazines.select(magazine);
		
		TypedQuery<Magazine> magazineQuery = entityManager.createQuery(magazines);
		List<Magazine> magazineList = magazineQuery.getResultList();
		
		return magazineList;
	}

	@Override
	public List<Newspaper> getAllNewspapers() throws RepositoryException {

		CriteriaQuery<Newspaper> newspapers = builder.createQuery(Newspaper.class);
		
		Root<Newspaper> newspaper = newspapers.from(Newspaper.class);
		newspapers.select(newspaper);
		
		TypedQuery<Newspaper> newspaperQuery = entityManager.createQuery(newspapers);
		List<Newspaper> newspaperList = newspaperQuery.getResultList();
		
		return newspaperList;
	}

	@Override
	public List<Publication> getAllPublications() throws RepositoryException {

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
	public List<Publication> searchPublication(String title) throws RepositoryException {

		CriteriaQuery<Publication> pubByTitle = builder.createQuery(Publication.class);
		Root<Publication> pub = pubByTitle.from(Publication.class);
		
		pubByTitle.select(pub);
		pubByTitle.where(builder.like(pub.get(Publication_.title), '%' + title + '%'));
		
		TypedQuery<Publication> pubQuery = entityManager.createQuery(pubByTitle);
		List<Publication> pubList = pubQuery.getResultList();
		
		return pubList;
	}

	@Override
	public int getCopiesLeft(String uuid) {

		Publication publication = entityManager.find(Publication.class, uuid);
		
		System.out.println(publication.getCopiesLeft());
		
		return publication.getCopiesLeft();
	}

	@Override
	public void setCopiesLeft(String uuid) {

		try {
			
			Publication pub = entityManager.find(Publication.class, uuid);
			
			entityManager.getTransaction().begin();
			CriteriaUpdate<Book> updateCopiesLeft = builder.createCriteriaUpdate(Book.class);
			
			Root<Book> bookRoot = updateCopiesLeft.from(Book.class);
			
			pub.setCopiesLeft(pub.getCopiesLeft()-1);
			updateCopiesLeft.set(bookRoot.get(Book_.copiesLeft), pub.getCopiesLeft());
			
			updateCopiesLeft.where(builder.equal(bookRoot.get(Book_.uuId), uuid));
			entityManager.createQuery(updateCopiesLeft).executeUpdate();
			entityManager.getTransaction().commit();
			
		} catch(PersistenceException e) {
			//log error
			throw new RepositoryException("",e);
		}
		
	}

	@Override
	public Publication getPublicationByUuid(String uuid) {

		Publication pub = entityManager.find(Publication.class, uuid);
		
		return pub;
	}

}
