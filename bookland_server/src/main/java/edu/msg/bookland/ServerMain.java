package edu.msg.bookland;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import edu.msg.bookland.model.Author;
import edu.msg.bookland.repository.jdbc.ConnectionManager;
import edu.msg.bookland.rmi.AuthorServiceRmi;
import edu.msg.bookland.rmi.BorrowingServiceRmi;
import edu.msg.bookland.rmi.PublicationServiceRmi;
import edu.msg.bookland.rmi.UserServiceRmi;
import edu.msg.bookland.service.AuthorService;
import edu.msg.bookland.service.BorrowingService;
import edu.msg.bookland.service.PublicationService;
import edu.msg.bookland.service.UserService;

/**
 * Entry point for running Server
 * 
 * @author Jozsef Solomon
 */
public class ServerMain {
	private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);

	public static void main(String[] args) {
		initServer();
	}

	/**
	 * Registry to all RMI methods.
	 */
	public static void initServer() {
		try {
			Registry registry = LocateRegistry.createRegistry(UserServiceRmi.RMI_PORT);
			UserService uService = new UserService();
			registry.rebind(UserServiceRmi.RMI_NAME, uService);
			AuthorService aService = new AuthorService();
			registry.rebind(AuthorServiceRmi.RMI_NAME, aService);
			BorrowingService boService = new BorrowingService();
			registry.rebind(BorrowingServiceRmi.RMI_NAME, boService);
			PublicationService pubService = new PublicationService();
			registry.rebind(PublicationServiceRmi.RMI_NAME, pubService);			
			LOGGER.info("Server online!");

			try {
				
				
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("edu.msg.bookland.jpa");
				EntityManager em = entityManagerFactory.createEntityManager();
				
				CriteriaBuilder builder = em.getCriteriaBuilder();
				
				CriteriaQuery<Author> authors2 = builder.createQuery(Author.class);
				Root<Author> authorRoot = authors2.from(Author.class);
				
				authors2.select(authorRoot);
				TypedQuery<Author> authorTypeQuery = em.createQuery(authors2);
				List<Author> finalList = authorTypeQuery.getResultList();
				System.out.println("ok");
				
				for(Author a:finalList) {
					System.out.println(a.getName());
				}

				
					
			} catch(HibernateException e) {
				e.printStackTrace();
			}
 
			
			
			
			
			
			
			
		} catch (RemoteException e) {
			LOGGER.error("Server not running", e);
		}
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> branch 'develop' of https://github.com/MRoberr/Bookland.git
