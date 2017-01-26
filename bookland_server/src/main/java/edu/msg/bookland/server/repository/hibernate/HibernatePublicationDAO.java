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

import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.Book;
import edu.msg.bookland.server.model.Book_;
import edu.msg.bookland.server.model.Magazine;
import edu.msg.bookland.server.model.Magazine_;
import edu.msg.bookland.server.model.Newspaper;
import edu.msg.bookland.server.model.Newspaper_;
import edu.msg.bookland.server.model.Publication;
import edu.msg.bookland.server.model.Publication_;
import edu.msg.bookland.server.repository.PublicationDAO;
import edu.msg.bookland.server.repository.RepositoryException;

/**
 * Managing the publication database with Hibernate - criteria api
 * 
 * @author Róbert Levente Májai
 *
 */
public class HibernatePublicationDAO implements PublicationDAO {

	private static final Logger LOGGER = Logger.getLogger(HibernatePublicationDAO.class);

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

		try {
			CriteriaQuery<Book> books = builder.createQuery(Book.class);

			Root<Book> book = books.from(Book.class);
			books.select(book);

			TypedQuery<Book> bookQuery = entityManager.createQuery(books);
			List<Book> bookList = bookQuery.getResultList();

			LOGGER.info("Book query was successful");

			return bookList;
		} catch (PersistenceException e) {

			LOGGER.error("Couldn't execute query on books", e);
			throw new RepositoryException("Couldn't execute query on books", e);

		}
	}

	@Override
	public List<Magazine> getAllMagazines() throws RepositoryException {

		try {
			CriteriaQuery<Magazine> magazines = builder.createQuery(Magazine.class);

			Root<Magazine> magazine = magazines.from(Magazine.class);
			magazines.select(magazine);

			TypedQuery<Magazine> magazineQuery = entityManager.createQuery(magazines);
			List<Magazine> magazineList = magazineQuery.getResultList();

			LOGGER.info("Magazine query was successful");

			return magazineList;
		} catch (PersistenceException e) {

			LOGGER.error("Couldn't execute query on magazines", e);
			throw new RepositoryException("Couldn't execute query on magazines", e);
		}
	}

	@Override
	public List<Newspaper> getAllNewspapers() throws RepositoryException {

		try {
			CriteriaQuery<Newspaper> newspapers = builder.createQuery(Newspaper.class);

			Root<Newspaper> newspaper = newspapers.from(Newspaper.class);
			newspapers.select(newspaper);

			TypedQuery<Newspaper> newspaperQuery = entityManager.createQuery(newspapers);
			List<Newspaper> newspaperList = newspaperQuery.getResultList();

			LOGGER.info("Newspaper query was successful");

			return newspaperList;
		} catch (PersistenceException e) {

			LOGGER.error("Couldn't execute query on newspapers", e);
			throw new RepositoryException("Couldn't execute query on newspapers", e);
		}
	}

	@Override
	public List<Publication> getAllPublications() throws RepositoryException {

		try {
			CriteriaQuery<Publication> pubs = builder.createQuery(Publication.class);

			Root<Publication> pub = pubs.from(Publication.class);
			pubs.select(pub);

			TypedQuery<Publication> pubQuery = entityManager.createQuery(pubs);
			List<Publication> pubList = pubQuery.getResultList();

			LOGGER.info("Publications query was successful");

			return pubList;
		} catch (PersistenceException e) {

			LOGGER.error("Couldn't execute query on publications", e);
			throw new RepositoryException("Couldn't execute query on publications", e);
		}
	}

	@Override
	public void insertBook(Book book) throws RepositoryException {

		try {
			entityManager.getTransaction().begin();
			book.getUUID();
			entityManager.merge(book);
			entityManager.getTransaction().commit();

			LOGGER.info("Book insert completed successfully");

		} catch (PersistenceException e) {

			LOGGER.error("Error while inserting book", e);
			throw new RepositoryException("Error while inserting book", e);
		}
	}

	@Override
	public void insertMagazine(Magazine magazine) throws RepositoryException {

		try {
			entityManager.getTransaction().begin();
			magazine.getUUID();
			entityManager.persist(magazine);
			entityManager.getTransaction().commit();

			LOGGER.info("Magazine insert completed successgully");

		} catch (PersistenceException e) {

			LOGGER.error("Error while inserting magazine", e);
			throw new RepositoryException("Error while inserting magazine", e);
		}
	}

	@Override
	public void insertNewspaper(Newspaper newspaper) throws RepositoryException {

		try {
			entityManager.getTransaction().begin();
			newspaper.getUUID();
			entityManager.persist(newspaper);
			entityManager.getTransaction().commit();

			LOGGER.info("Newspaper insert complered successfully");

		} catch (PersistenceException e) {

			LOGGER.error("Error while inserting  newspaper", e);
			throw new RepositoryException("Error while inserting newspaper", e);
		}
	}

	@Override
	public void updatePublication(Publication publication) throws RepositoryException {

		try {
			entityManager.getTransaction().begin();
			CriteriaUpdate<Publication> update = builder.createCriteriaUpdate(Publication.class);

			Root<Publication> pubRoot = update.from(Publication.class);

			update.set(pubRoot.get(Book_.title), publication.getTitle());
			update.set(pubRoot.get(Book_.publisher), publication.getPublisher());
			update.set(pubRoot.get(Book_.releaseDate), publication.getReleaseDate());
			update.set(pubRoot.get(Book_.copiesLeft), publication.getCopiesLeft());
			update.set(pubRoot.get(Book_.releaseDate), publication.getReleaseDate());

			update.where(builder.equal(pubRoot.get(Book_.uuId), publication.getUUID()));
			entityManager.createQuery(update).executeUpdate();
			entityManager.getTransaction().commit();

			LOGGER.info("Publication update completed");

		} catch (PersistenceException e) {

			LOGGER.error("Error while updating publication", e);
			throw new RepositoryException("Error while updating publication", e);

		}
	}

	@Override
	public List<Publication> searchPublication(String title) throws RepositoryException {

		try {
			CriteriaQuery<Publication> pubByTitle = builder.createQuery(Publication.class);
			Root<Publication> pub = pubByTitle.from(Publication.class);

			pubByTitle.select(pub);
			pubByTitle.where(builder.like(pub.get(Publication_.title), '%' + title + '%'));

			TypedQuery<Publication> pubQuery = entityManager.createQuery(pubByTitle);
			List<Publication> pubList = pubQuery.getResultList();
			
			if (pubList.isEmpty()) {

				LOGGER.error("Searching for publication by its title resulted an empty list");
				throw new RepositoryException("Searching for publication by its title resulted an empty list");
			} else {

				LOGGER.info("Search for publication by title was executed with success");
				return pubList;
			}
		} catch (PersistenceException e) {

			LOGGER.error("Searching for publication by its title failed", e);
			throw new RepositoryException("Searching for publication by its tutle failed", e);
		}
	}

	@Override
	public void increaseCopiesLeft(String uuid) throws RepositoryException {

		try {

			modifyCopiesLeft(uuid, 1);
			LOGGER.info("Number of copies left increased");
		} catch (PersistenceException e) {

			LOGGER.error("Failed to increased number of copies left", e);
			throw new RepositoryException("Failed to increased number of copies left", e);
		}
	}

	@Override
	public void decreaseCopiesLeft(String uuid) {

		try {

			modifyCopiesLeft(uuid, -1);
			LOGGER.info("Number of copies left decreased");
		} catch (PersistenceException e) {

			LOGGER.error("Failed to decrease number of copies left", e);
			throw new RepositoryException("Failed to decrease number of copies left", e);
		}

	}

	@Override
	public Publication getPublicationByUuid(String uuid) {

		try {
			
			Publication pub = entityManager.find(Publication.class, uuid);
			
			LOGGER.info("Got publication by id");
			return pub;
			
		} catch(PersistenceException e) {
			
			LOGGER.error("Failed to get publication by id", e);
			throw new RepositoryException("Failed to get publication by id", e);
		}
	}

	@Override
	public void deletePublication(String uuid) throws RepositoryException {

		try {
			entityManager.getTransaction().begin();
			CriteriaDelete<Publication> delete = builder.createCriteriaDelete(Publication.class);
			
			Root<Publication> pubRoot = delete.from(Publication.class);
			
			delete.where(builder.equal(pubRoot.get(Publication_.uuId), uuid));
			entityManager.createQuery(delete).executeUpdate();
			entityManager.getTransaction().commit();
			LOGGER.info("Publication deleted successgully");
			
		} catch (PersistenceException e) {
			
			LOGGER.error("Failed to delete publication", e);
			throw new RepositoryException("Failed to delete publication", e);
		}
		
	}

	@Override
	public List<Book> searchBook(String title) throws BusinesLogicException {

		CriteriaQuery<Book> bookByTitle = builder.createQuery(Book.class);
		Root<Book> pub = bookByTitle.from(Book.class);

		bookByTitle.select(pub);
		bookByTitle.where(builder.like(pub.get(Book_.title), '%' + title + '%'));

		TypedQuery<Book> bookQuery = entityManager.createQuery(bookByTitle);
		List<Book> bookList = bookQuery.getResultList();
		
		return bookList;
	}

	@Override
	public List<Magazine> searchMagazine(String title) throws BusinesLogicException {

		CriteriaQuery<Magazine> magazineByTitle = builder.createQuery(Magazine.class);
		Root<Magazine> pub = magazineByTitle.from(Magazine.class);

		magazineByTitle.select(pub);
		magazineByTitle.where(builder.like(pub.get(Magazine_.title), '%' + title + '%'));

		TypedQuery<Magazine> magazineQuery = entityManager.createQuery(magazineByTitle);
		List<Magazine> magazineList = magazineQuery.getResultList();
		
		return magazineList;
	}

	@Override
	public List<Newspaper> searchNewspaper(String title) throws RepositoryException {

		CriteriaQuery<Newspaper> newspaperByTitle = builder.createQuery(Newspaper.class);
		Root<Newspaper> pub = newspaperByTitle.from(Newspaper.class);

		newspaperByTitle.select(pub);
		newspaperByTitle.where(builder.like(pub.get(Newspaper_.title), '%' + title + '%'));

		TypedQuery<Newspaper> newspaperQuery = entityManager.createQuery(newspaperByTitle);
		List<Newspaper> newspaperList = newspaperQuery.getResultList();
		
		return newspaperList;
	}

	public void modifyCopiesLeft(String id, int value) {

		Publication pub = entityManager.find(Publication.class, id);

		entityManager.getTransaction().begin();
		CriteriaUpdate<Book> updateCopiesLeft = builder.createCriteriaUpdate(Book.class);
 
		Root<Book> bookRoot = updateCopiesLeft.from(Book.class);

		pub.setCopiesLeft(pub.getCopiesLeft() + value);
		updateCopiesLeft.set(bookRoot.get(Book_.copiesLeft), pub.getCopiesLeft());

		updateCopiesLeft.where(builder.equal(bookRoot.get(Book_.uuId), id));
		entityManager.createQuery(updateCopiesLeft).executeUpdate();
		entityManager.getTransaction().commit();
	}
	

}






















