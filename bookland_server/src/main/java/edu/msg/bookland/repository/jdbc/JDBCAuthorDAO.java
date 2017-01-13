package edu.msg.bookland.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.msg.bookland.model.Author;
import edu.msg.bookland.repository.AuthorDAO;
import edu.msg.bookland.repository.RepositoryException;

/**
 * Implements CRUD for AUTHOR Model
 * 
 * @author Jozsef Solomon
 */
public class JDBCAuthorDAO implements AuthorDAO {

	private ConnectionManager connectionManager;
	private static final Logger LOGGER = Logger.getLogger(JDBCAuthorDAO.class);

	/**
	 * Initialize ConnectionManager
	 * 
	 * @throws RepositoryException
	 */
	public JDBCAuthorDAO() throws RepositoryException {
		connectionManager = ConnectionManager.getInstance();
	}

	/*
	 * @see edu.msg.bookland.repository.AuthorDAO#getAllAuthors()
	 */
	@Override
	public List<Author> getAllAuthors() {
		List<Author> authorsList = new ArrayList<Author>();
		Connection con = null;
		try {
			con = connectionManager.getConnection();
			Statement stat = con.createStatement();
			ResultSet authors = stat.executeQuery("Select * from authors");
			while (authors.next()) {
				Author author = new Author();
				author.setUUID(authors.getString("uuid"));
				author.setName(authors.getString("name"));
				authorsList.add(author);

			}
			stat.close();
			authors.close();
			LOGGER.info("Authors successfully retrieved from DB");
			return authorsList;
		} catch (SQLException e) {
			LOGGER.error("Could not query authors", e);
			throw new RepositoryException("Could not query authors", e);
		} finally {
			if (con != null) {
				connectionManager.returnConnection(con);
			}
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.repository.AuthorDAO#insertAuthor(edu.msg.bookland.model
	 * .Author)
	 */
	@Override
	public void insertAuthor(Author author) {
		Connection con = null;

		try {
			con = connectionManager.getConnection();
			PreparedStatement preparedStatement = con
					.prepareStatement("Insert into authors (uuid, name) values (?, ?)");
			preparedStatement.setString(1, author.getUUID());
			preparedStatement.setString(2, author.getName());
			preparedStatement.execute();
			preparedStatement.close();
			LOGGER.info("Author successfully inserted into DB");

		} catch (SQLException e) {
			LOGGER.error("Could not insert author", e);
			throw new RepositoryException("Could not insert author", e);
		} finally {
			if (con != null) {
				connectionManager.returnConnection(con);
			}
		}
	}

	/*
	 * @see
	 * edu.msg.bookland.repository.AuthorDAO#updateAuthor(edu.msg.bookland.model
	 * .Author)
	 */
	@Override
	public void updateAuthor(Author author) {
		Connection con = null;
		try {
			con = connectionManager.getConnection();

			PreparedStatement preparedStatement = con.prepareStatement("update authors set name = ? where uuid = ?");
			preparedStatement.setString(1, author.getName());
			preparedStatement.setString(2, author.getUUID());
			preparedStatement.execute();
			preparedStatement.close();
			LOGGER.info("Author successfully updated in DB");
		} catch (SQLException e) {
			LOGGER.error("Could not update author", e);
			throw new RepositoryException("Could not update author.", e);
		} finally {
			if (con != null) {
				connectionManager.returnConnection(con);
			}
		}

	}

	/*
	 * @see
	 * edu.msg.bookland.repository.AuthorDAO#deleteAuthor(edu.msg.bookland.model
	 * .Author)
	 */
	@Override
	public void deleteAuthor(Author author) {
		Connection con = null;
		try {
			con = connectionManager.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("Delete from authors where uuid = ?");
			preparedStatement.setString(1, author.getUUID());
			preparedStatement.execute();
			preparedStatement.close();
			LOGGER.info("Author deleted");
		} catch (SQLException e) {
			LOGGER.error("Could not delete author", e);
			throw new RepositoryException("Could not delete author.", e);
		} finally {
			if (con != null) {
				connectionManager.returnConnection(con);
			}

		}

	}

	/*
	 * @see
	 * edu.msg.bookland.repository.AuthorDAO#getAuthorByUuid(java.lang.String)
	 */

	@Override
	public Author getAuthorByUuid(String uuId) {
		Connection con = null;
		Author author = new Author();
		try {
			con = connectionManager.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("select * from authors where uuid = ?");
			preparedStatement.setString(1, uuId);
			ResultSet resultset = preparedStatement.executeQuery();
			resultset.next();
			author.setUUID(resultset.getString("uuid"));
			author.setName(resultset.getString("name"));
			preparedStatement.close();
			resultset.close();
			LOGGER.info("Succesfully retrieved author by id from DB");
			return author;
		} catch (SQLException e) {
			LOGGER.error("Cannot select from authors by id", e);
			throw new RepositoryException("Cannot select from authors by id", e);
		} finally {
			if (con != null) {
				connectionManager.returnConnection(con);
			}
		}
	}

}
