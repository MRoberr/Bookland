package edu.msg.bookland;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.msg.bookland.rmi.UserServiceRmi;
import edu.msg.bookland.service.UserService;

public class ServerMain {

	public static void main(String[] args) {
		initServer();
	}
	
	public static void initServer() {
		try {
			Registry registry = LocateRegistry.createRegistry(UserServiceRmi.RMI_PORT);
			

			UserService uService = new UserService();
			registry.rebind(UserServiceRmi.RMI_NAME, uService);
			System.out.println("Server online!");
			

			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}


















/*
 * 			
			SessionFactory factory = new AnnotationConfiguration()
					.addAnnotatedClass(Author.class)
					.configure()
					.buildSessionFactory();
			Session session = factory.openSession();

			try {

				Transaction t = session.beginTransaction();
				
				//insert
//				Author a = new Author();
//				a.setName("addsda");
//				a.setUUID("sda23erh");
//				
//				session.save(a);
//				
//				t.commit();
				//insert vege
				
				Criteria criteria = session.createCriteria(Author.class);
				List<Author> authors = criteria.list();
				
				for(Author author:authors) {
					
					System.out.println(author.getName() + author.getUUID());
				}
				
			} catch(HibernateException e) {
				e.printStackTrace();
			}
 */
