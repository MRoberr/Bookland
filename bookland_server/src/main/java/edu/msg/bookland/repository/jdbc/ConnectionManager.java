package edu.msg.bookland.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.util.PropertyProvider;

/**
 * Singleton class to initialize a connection pool and manage connections
 * 
 * @author Terez Sipos
 *
 */
public final class ConnectionManager {

	private static final String DBURL = PropertyProvider.getProperty("mysql.URL");
	private static final String USER = PropertyProvider.getProperty("mysql.user");
	private static final String PASSWORD = PropertyProvider.getProperty("mysql.password");
	private static final short SIZE = Short.parseShort(PropertyProvider.getProperty("connection.size"));

	private static ConnectionManager instance;
	private List<Connection> pool;
	private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);

	/**
	 * Initialize connection pool
	 * 
	 * @throws RepositoryException
	 */
	private ConnectionManager() throws RepositoryException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			pool = new ArrayList<>();
			for (int i = 0; i < SIZE; ++i) {
				pool.add(DriverManager.getConnection(DBURL, USER, PASSWORD));
			}
			LOGGER.info("Connection pool initialized");
		} catch (ClassNotFoundException e) {
			LOGGER.error("Could not find class", e);
			throw new RepositoryException("Could not Find Class", e);
		} catch (SQLException e) {
			LOGGER.error("Could not create connection", e);
			throw new RepositoryException("Could not Create Connection", e);
		}
	}

	/**
	 * This method call the private constructor of the class and return one
	 * single instance
	 * 
	 * @return the only instance of connection Manager
	 * @throws RepositoryException
	 */
	public static synchronized ConnectionManager getInstance() throws RepositoryException {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	/**
	 * This method remove one connection from pool and return it
	 * 
	 * @return Connection
	 * @throws RepositoryException
	 */
	public synchronized Connection getConnection() throws RepositoryException {
		Connection con = null;
		if (!pool.isEmpty()) {
			con = pool.remove(0);
		} else {
			LOGGER.error("No more connections in the pool");
			throw new RepositoryException("No more connections in the pool");
		}
		return con;
	}

	/**
	 * This method retrieve one connection to pool
	 * 
	 * @param connection
	 */
	public synchronized void returnConnection(Connection connection) {
		if (pool.size() < SIZE) {
			pool.add(connection);
		}
	}

}
