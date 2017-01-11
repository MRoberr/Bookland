package edu.msg.bookland.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public final class ConnectionManager {
	/*
	private static final String DBURL = PropertyProvider.INSTANCE.getProperty("mysql.URL");
	private static final String user =PropertyProvider.INSTANCE.getProperty("mysql.user");
	private static final String password = PropertyProvider.INSTANCE.getProperty("mysql.password");
	private static final short size = Short.parseShort(PropertyProvider.INSTANCE.getProperty("connection.size"));
	*/
	private static ConnectionManager instance;
	private List<Connection> pool;
	private int size=10;
	//private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

	private ConnectionManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			pool = new ArrayList<Connection>();
			// con=DriverManager.getConnection(DBURL,user,password);
			for (int i = 0; i < size; ++i) {
			//	pool.add(DriverManager.getConnection(DBURL, user, password));
			}
		//	LOGGER.info("Connection pool initialized");

		} catch (ClassNotFoundException e) {
//			LOGGER.error("Could not find class", e);
//			throw new RepositoryException("Could not Find Class", e);
//		} catch (SQLException e) {
//			LOGGER.error("Could not create connection", e);
//			throw new RepositoryException("Could not Create Connection", e);
		}
	}

	public synchronized static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	public synchronized Connection getConnection() {
		Connection con = null;
		if (!pool.isEmpty()) {
			con = pool.get(0);
		} else {
			//throw new RepositoryException("No more connections in the pool");
		}
		return con;
	}

	public synchronized void returnConnection(Connection connection) {
		if (pool.size() < size) {
			pool.add(connection);
		}
	}

}
