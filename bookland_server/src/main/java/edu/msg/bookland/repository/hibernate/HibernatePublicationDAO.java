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

import edu.msg.bookland.common.model.BookDTO;
import edu.msg.bookland.common.model.Book_;
import edu.msg.bookland.common.model.MagazineDTO;
import edu.msg.bookland.common.model.NewspaperDTO;
import edu.msg.bookland.common.model.PublicationDTO;
import edu.msg.bookland.common.model.Publication_;
import edu.msg.bookland.repository.PublicationDAO;
import edu.msg.bookland.repository.RepositoryException;

public class HibernatePublicationDAO implements PublicationDAO {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private CriteriaBuilder builder;

	public HibernatePublicationDAO() {

		entityManagerFactory = Persistence.createEntityManagerFactory("bookland_jpa");
		entityManager = entityManagerFactory.createEntityManager();
		builder = entityManager.getCriteriaBuilder();

	}

	@Override
	public List<BookDTO> getAllBooks() throws RepositoryException {

		CriteriaQuery<BookDTO> books = builder.createQuery(BookDTO.class);

		Root<BookDTO> book = books.from(BookDTO.class);
		books.select(book);

		TypedQuery<BookDTO> bookQuery = entityManager.createQuery(books);
		List<BookDTO> bookList = bookQuery.getResultList();

		return bookList;
	}

	@Override
	public List<MagazineDTO> getAllMagazines() throws RepositoryException {

		CriteriaQuery<MagazineDTO> magazines = builder.createQuery(MagazineDTO.class);

		Root<MagazineDTO> magazine = magazines.from(MagazineDTO.class);
		magazines.select(magazine);

		TypedQuery<MagazineDTO> magazineQuery = entityManager.createQuery(magazines);
		List<MagazineDTO> magazineList = magazineQuery.getResultList();

		return magazineList;
	}

	@Override
	public List<NewspaperDTO> getAllNewspapers() throws RepositoryException {

		CriteriaQuery<NewspaperDTO> newspapers = builder.createQuery(NewspaperDTO.class);

		Root<NewspaperDTO> newspaper = newspapers.from(NewspaperDTO.class);
		newspapers.select(newspaper);

		TypedQuery<NewspaperDTO> newspaperQuery = entityManager.createQuery(newspapers);
		List<NewspaperDTO> newspaperList = newspaperQuery.getResultList();

		return newspaperList;
	}

	@Override
	public List<PublicationDTO> getAllPublications() throws RepositoryException {

		CriteriaQuery<PublicationDTO> pubs = builder.createQuery(PublicationDTO.class);

		Root<PublicationDTO> pub = pubs.from(PublicationDTO.class);
		pubs.select(pub);

		TypedQuery<PublicationDTO> pubQuery = entityManager.createQuery(pubs);
		List<PublicationDTO> pubList = pubQuery.getResultList();

		return pubList;
	}

	@Override
	public void insertBook(BookDTO book) throws RepositoryException {

		entityManager.getTransaction().begin();
		entityManager.persist(book);
		entityManager.getTransaction().commit();
	}

	@Override
	public void insertMagazine(MagazineDTO magazine) throws RepositoryException {

		entityManager.getTransaction().begin();
		entityManager.persist(magazine);
		entityManager.getTransaction().commit();
	}

	@Override
	public void insertNewspaper(NewspaperDTO newspaper) throws RepositoryException {

		entityManager.getTransaction().begin();
		entityManager.persist(newspaper);
		entityManager.getTransaction().commit();
	}

	@Override
	public void updateBook(BookDTO book) throws RepositoryException {
		// entityManager.getTransaction().begin();
		// Book book2 = entityManager.find(Book.class, book.getUUID());
		// book2.setTitle("asddsad");
		// entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		CriteriaUpdate<BookDTO> update = builder.createCriteriaUpdate(BookDTO.class);

		Root<BookDTO> bookRoot = update.from(BookDTO.class);

		update.set(bookRoot.get(Book_.copiesLeft), book.getCopiesLeft());
		update.set(bookRoot.get(Book_.title), book.getTitle());
		update.set(bookRoot.get(Book_.releaseDate), book.getReleaseDate());
		update.set(bookRoot.get(Book_.publisher), book.getPublisher());

		update.where(builder.equal(bookRoot.get(Book_.uuId), book.getUUID()));
		entityManager.createQuery(update).executeUpdate();
		entityManager.getTransaction().commit();

	}

	@Override
	public void updateMagazine(MagazineDTO magazine) throws RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNewspaper(NewspaperDTO newspaper) throws RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBook(BookDTO book) throws RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMagazine(MagazineDTO magazine) throws RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteNewspaper(NewspaperDTO newspaper) throws RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PublicationDTO> searchPublication(String title) throws RepositoryException {

		CriteriaQuery<PublicationDTO> pubByTitle = builder.createQuery(PublicationDTO.class);
		Root<PublicationDTO> pub = pubByTitle.from(PublicationDTO.class);
		
		pubByTitle.select(pub);
		pubByTitle.where(builder.like(pub.get(Publication_.title), '%' + title + '%'));
		
		TypedQuery<PublicationDTO> pubQuery = entityManager.createQuery(pubByTitle);
		List<PublicationDTO> pubList = pubQuery.getResultList();
		
		if(pubList.isEmpty()) {
		
			//log
			throw new RepositoryException("Empty find result.");
		} else {
			
			return pubList;
		}
	}

	@Override
	public int getCopiesLeft(String uuid) {

		PublicationDTO publication = entityManager.find(PublicationDTO.class, uuid);

		System.out.println(publication.getCopiesLeft());

		return publication.getCopiesLeft();
	}

	@Override
	public void setCopiesLeft(String uuid) {

		try {

			PublicationDTO pub = entityManager.find(PublicationDTO.class, uuid);

			entityManager.getTransaction().begin();
			CriteriaUpdate<BookDTO> updateCopiesLeft = builder.createCriteriaUpdate(BookDTO.class);

			Root<BookDTO> bookRoot = updateCopiesLeft.from(BookDTO.class);

			pub.setCopiesLeft(pub.getCopiesLeft() - 1);
			updateCopiesLeft.set(bookRoot.get(Book_.copiesLeft), pub.getCopiesLeft());

			updateCopiesLeft.where(builder.equal(bookRoot.get(Book_.uuId), uuid));
			entityManager.createQuery(updateCopiesLeft).executeUpdate();
			entityManager.getTransaction().commit();

		} catch (PersistenceException e) {
			// log error
			throw new RepositoryException("", e);
		}

	}

	@Override
	public PublicationDTO getPublicationByUuid(String uuid) {

		PublicationDTO pub = entityManager.find(PublicationDTO.class, uuid);

		return pub;
	}

}
