package edu.msg.bookland.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.util.PropertyProvider;


public final class ConnectionManager {
	
	private static final String DBURL = PropertyProvider.INSTANCE.getProperty("mysql.URL");
	private static final String user =PropertyProvider.INSTANCE.getProperty("mysql.user");
	private static final String password = PropertyProvider.INSTANCE.getProperty("mysql.password");
	private static final short size = Short.parseShort(PropertyProvider.INSTANCE.getProperty("connection.size"));

	private static ConnectionManager instance;
	private List<Connection> pool;
	private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);

	private ConnectionManager() throws RepositoryException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			pool = new ArrayList<Connection>();
			for (int i = 0; i < size; ++i) {
				pool.add(DriverManager.getConnection(DBURL, user, password));
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

	public synchronized static ConnectionManager getInstance() throws RepositoryException{
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	public synchronized Connection getConnection() throws RepositoryException{
		Connection con = null;
		if (!pool.isEmpty()) {
			con = pool.get(0);
		} else {
			LOGGER.error("No more connections in the pool");
			throw new RepositoryException("No more connections in the pool");
		}
		return con;
	}

	public synchronized void returnConnection(Connection connection) {
		if (pool.size() < size) {
			pool.add(connection);
		}
	}

}
