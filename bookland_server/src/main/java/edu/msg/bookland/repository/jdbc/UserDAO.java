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

public class UserDAO implements edu.msg.bookland.repository.UserDAO{
	private UserType userType;
	

	private ConnectionManager conMan;
	private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

	public UserDAO() {
		conMan = ConnectionManager.getInstance();
	}

	public List<User> getAllUsers() throws RepositoryException {
		List<User> list = new ArrayList();
		Connection con = null;
		try {
			con = conMan.getConnection();
			Statement statemanet = con.createStatement();
			ResultSet users = statemanet.executeQuery("select * from library_users");
			while (users.next()) {
				User u = new User();
				u.setName(users.getString("name"));
				u.setEmail(users.getString("email"));
				u.setLoyaltyIndex(users.getInt("loyality_index"));
				u.setUserType(UserType.valueOf(users.getString("user_type")));
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
	public User insertUser(User user) throws RepositoryException{
		Connection con = null;
		try {
			con = conMan.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("insert into library_users "
					+ "(name, email, user_type, loiality_index, password) " + "values (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getUserType().toString());
			preparedStatement.setInt(4, user.getLoyaltyIndex());	
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			user.setUUID(resultSet.getString(1));
			LOGGER.info("user inserted");
		} catch (SQLException e) {
			LOGGER.error("Could not inserd users. ",e);
			throw new RepositoryException("Could not inser users. ",e);
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
		return user;
	}
	
	public void deleteUser(User user) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	public void updateUser(User user) throws RepositoryException {
		Connection con = null;
		try {
			con = conMan.getConnection();
			PreparedStatement preparedStatement = 
					con.prepareStatement("update library_users set  name=?, email=?, loiality_index=?,  password=? "
					+"where id=?");
			
			
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setInt(3, user.getLoyaltyIndex());
			preparedStatement.setString(4, user.getPassword());
		
			preparedStatement.execute();
			
			LOGGER.info("user info updated");
		} catch (SQLException e) {
			LOGGER.error("Could not update user. ",e);
			throw new RepositoryException("Could not update user. ",e);
		} finally {
			if (con != null) {
				conMan.returnConnection(con);
			}
		}
		
	}

	public void updateUserName(User user) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	

	

}
