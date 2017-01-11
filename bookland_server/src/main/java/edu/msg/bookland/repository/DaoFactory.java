package edu.msg.bookland.repository;


public abstract class DaoFactory {
	public static DaoFactory getDaoFactory(){
		return null;
		//return new HibernateDAOFactory();
	}
	//public abstract UserDAO getUserDao();
}
