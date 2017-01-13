package edu.msg.bookland.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.model.User;
import edu.msg.bookland.model.UserType;
import edu.msg.bookland.repository.RepositoryException;
import edu.msg.bookland.repository.UserDAO;

/**
 * Implements CRUD for USER Model
 * 
 * @author Csilla Szocs
 * @author Terez Sipos
 * @author Simo Zoltan
 * 
 */
public class JDBCUserDAO implements UserDAO { 
	private ConnectionManager conMan;
	private static final Logger LOGGER = Logger.getLogger(JDBCUserDAO.class);

	/**
	 * Initialize ConnectionManager
	 * 
	 * @throws RepositoryException
	 */
	public JDBCUserDAO() {
		conMan = ConnectionManager.getInstance();
	}

	/*
	 * @see edu.msg.bookland.repository.UserDAO#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() throws RepositoryException {
		List<User> list = new ArrayList<User>();
		Connection con = null;
		try {
			con = conMan.getConnection();
			Statement statemanet = con.createStatement();
			ResultSet users = statemanet.executeQuery("select * from library_users");
			while (users.next()) {
				User u = new User("");
				u.setName(users.getString("name"));
				u.setEmail(users.getString("email"));
				u.setUserType(UserType.valueOf(users.getString("user_type")));
				u.setLoyaltyIndex(users.getInt("loyalty_index"));
				u.setUUID(users.getString("uuid"));
				list.add(u);
			}
			LOGGER.info("All user selected");
		} catch (SQLException e) {
			LOGGER.error("Could not query Users", e);
			throw new RepositoryException("Could not query Users", e);
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
		return list;
	}

	/*
	 * @see
	 * edu.msg.bookland.repository.UserDAO#insertUser(edu.msg.bookland.model.
	 * User)
	 */
	@Override
	public void insertUser(User user) throws RepositoryException {
		Connection con = null;
		try {
			con = conMan.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("insert into library_users "
					+ "(uuid, name, email, user_type, loyalty_index, password) " + "values ( ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, user.getUUID());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getUserType().toString());
			preparedStatement.setInt(5, user.getLoyaltyIndex());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.execute();

			LOGGER.info("user inserted");
		} catch (SQLException e) {
			LOGGER.error("Could not inserd users. ", e);
			throw new RepositoryException("Could not inser users. ", e);
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.repository.UserDAO#deleteUser(edu.msg.bookland.model.
	 * User)
	 */
	@Override
	public void deleteUser(User user) throws RepositoryException {
		Connection con = null;
		try {
			con = conMan.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM library_users WHERE uuid = ?");
			preparedStatement.setString(1, user.getUUID());
			preparedStatement.execute();

			LOGGER.info("user deleted");
		} catch (SQLException e) {
			LOGGER.error("Could not delete user. ", e);
			throw new RepositoryException("Could not delete user. ", e);
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}

	}

	/*
	 * @see
	 * edu.msg.bookland.repository.UserDAO#updateUser(edu.msg.bookland.model.
	 * User)
	 */
	@Override
	public void updateUser(User user) throws RepositoryException {
		Connection con = null;
		try {
			con = conMan.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(
					"update library_users set  name=?, email=?, loyalty_index=?,  password=? " + "where uuid=?");

			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setInt(3, user.getLoyaltyIndex());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getUUID());
			preparedStatement.execute();
			LOGGER.info("user info updated");

		} catch (SQLException e) {
			LOGGER.error("Could not update user. ", e);
			throw new RepositoryException("Could not update user. ", e);
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}

	}

	/*
	 * @see
	 * edu.msg.bookland.repository.UserDAO#updateUserWithoutPassword(edu.msg.
	 * bookland.model.User)
	 */
	@Override
	public void updateUserWithoutPassword(User user) throws RepositoryException {
		Connection con = null;
		try {
			con = conMan.getConnection();
			PreparedStatement preparedStatement = con
					.prepareStatement("update library_users set  name=?, email=?, loyalty_index=?" + "where uuid=?");

			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setInt(3, user.getLoyaltyIndex());
			preparedStatement.setString(5, user.getUUID());
			preparedStatement.execute();
			LOGGER.info("user info updated");

		} catch (SQLException e) {
			LOGGER.error("Could not update user. ", e);
			throw new RepositoryException("Could not update user. ", e);
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
	}

	/*
	 * @see edu.msg.bookland.repository.UserDAO#login(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public UserType login(String userName, String password) throws RepositoryException {
		Connection con = null;
		try {
			con = conMan.getConnection();
			PreparedStatement preparedStatement = con
					.prepareStatement("select user_type from library.library_users where name = ? and password = ?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			UserType ut = UserType.valueOf(resultSet.getString(1).toUpperCase());
			preparedStatement.close();
			resultSet.close();
			return ut;

		} catch (SQLException e) {
			LOGGER.error("Login failed! ", e);
			throw new RepositoryException("Login failed!", e);
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
	}

	/*
	 * @see edu.msg.bookland.repository.UserDAO#getUserByName(java.lang.String)
	 */
	public User getUserByName(String name) throws RepositoryException {
		Connection con = null;
		String query = "select uuid, name, email, user_type, loyalty_index from library_users where name = ?";
		User user = new User("");
		try {
			con = conMan.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, name);
			ResultSet users = statement.executeQuery();
			if (users.next()) {
				user = new User(users.getString("name"), users.getString("email"),
						UserType.valueOf(users.getString("user_type")), users.getInt("loyalty_index"));
				user.setUUID(users.getString("uuid"));
			}
			statement.close();
			users.close();
		} catch (SQLException e) {
			LOGGER.error("Could not retrieve User! ", e);
			throw new RepositoryException("Could not retrive User!", e);
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
		return user;
	}

	/*
	 * @see edu.msg.bookland.repository.UserDAO#getUserById(java.lang.String)
	 */
	public User getUserById(String id) throws RepositoryException {
		Connection con = null;
		String query = "select name, email, user_type, loyalty_index from library_users where uuid = ?";
		User user = new User("");
		try {
			con = conMan.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, id);
			ResultSet users = statement.executeQuery();
			if (users.next()) {
				user = new User(users.getString("name"), users.getString("email"),
						UserType.valueOf(users.getString("user_type")), users.getInt("loyalty_index"));
				user.setUUID(id);
			}
			statement.close();
			users.close();
		} catch (SQLException e) {
			LOGGER.error("Could not retrieve User! ", e);
			throw new RepositoryException("Could not retrive User!", e);
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
		return user;
	}

	/*
	 * @see
	 * edu.msg.bookland.repository.UserDAO#searchUserByName(java.lang.String)
	 */
	public List<User> searchUserByName(String name) throws RepositoryException {
		Connection con = null;
		String query = "select uuid, name, email, user_type, loyalty_index from library_users where name like ?";
		User user = new User("");
		List<User> userList = new ArrayList<User>();
		try {
			con = conMan.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, "%" + name + "%");
			ResultSet users = statement.executeQuery();
			while (users.next()) {
				user = new User(users.getString("name"), users.getString("email"),
						UserType.valueOf(users.getString("user_type")), users.getInt("loyalty_index"));
				user.setUUID(users.getString("uuid"));
				userList.add(user);
			}
			statement.close();
			users.close();
		} catch (SQLException e) {
			LOGGER.error("Could not retrieve User list! ", e);
			throw new RepositoryException("Could not retrive User list!", e);
		} finally {			
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
		return userList;
	}

}
