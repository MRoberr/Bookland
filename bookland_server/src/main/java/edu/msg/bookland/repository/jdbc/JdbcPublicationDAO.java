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
import edu.msg.bookland.model.Book;
import edu.msg.bookland.model.Magazine;
import edu.msg.bookland.model.Newspaper;
import edu.msg.bookland.model.Publication;
import edu.msg.bookland.repository.PublicationDAO;
import edu.msg.bookland.repository.RepositoryException;

public class JdbcPublicationDAO implements PublicationDAO{

	private static final Logger LOGGER = Logger.getLogger(JdbcPublicationDAO.class);
	
	private ConnectionManager connectionManager;
	
	public JdbcPublicationDAO() {
		
		connectionManager = ConnectionManager.getInstance();
		
	}
	
	
	public List<Publication> getAllPublications() {
		
		List<Publication> publications = new ArrayList<Publication>();
		Connection connection = null;
		
		try {
			
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet queryResult = statement.executeQuery(
					"select "
					+ "p.uuid, "
					+ "p.title, "
					+ "p.publisher, "
					+ "p.release_date, "
					+ "p.nr_of_copies, "
					+ "p.copies_left, "
					+ "pt.name "
					+ "from publications p"
					+ "join publication_type pton p.type = pt.id");
			
			LOGGER.info("Publications query completed");
			
			buildListFromQuery(queryResult, connection).forEach(publication -> publications.add(publication));
			
			LOGGER.info("Publications list build completed");
			return publications;
			
		} catch (SQLException e) {
			
			LOGGER.error("Couldn't query publications", e);
			throw new RepositoryException("Couldn't query users", e);			
		} finally {
			
			if (connection != null) {
				
				connectionManager.returnConnection(connection);
			}
		}
	}

	public List<Book> getAllBooks() {

		List<Book> books = new ArrayList<Book>();
		Connection connection = null;
		
		try {
			
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet queryResult = statement.executeQuery(
					"select "
					+ "p.uuid, "
					+ "p.title, "
					+ "p.publisher, "
					+ "p.release_date, "
					+ "p.nr_of_copies, "
					+ "p.copies_left, "
					+ "pt.name "
					+ "from publications p"
					+ "join publication_type pton p.type = pt.id"
					+ "where pt.name = book");
			
			LOGGER.info("Books query completed");
			
			buildListFromQuery(queryResult, connection).forEach(book -> books.add((Book)book));
			
			LOGGER.info("Books list build completed");
			return books;
			
		} catch (SQLException e) {
			
			LOGGER.info("Couldn't query books", e);
			throw new RepositoryException("Couldn't query books",e );
		} finally {
			
			if (connection != null) {
				
				connectionManager.returnConnection(connection);
			}
		}
		
	}

	public List<Magazine> getAllMagazines() {

		List<Magazine> magazines = new ArrayList<Magazine>();
		Connection connection = null;
		
		try {
			
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet queryResult = statement.executeQuery(
					"select "
					+ "p.uuid, "
					+ "p.title, "
					+ "p.publisher, "
					+ "p.release_date, "
					+ "p.nr_of_copies, "
					+ "p.copies_left, "
					+ "pt.name "
					+ "from publications p"
					+ "join publication_type pton p.type = pt.id"
					+ "where pt.name = magazine");
			
			LOGGER.info("Magazines query completed");
			
			buildListFromQuery(queryResult, connection).forEach(magazine -> magazines.add((Magazine)magazine));
			
			LOGGER.info("Magazines list build completed");
			return magazines;
			
		} catch (SQLException e) {
			
			LOGGER.info("Couldn't query magazine", e);
			throw new RepositoryException("Couldn't query magazine",e );
		} finally {
			
			if (connection != null) {
				
				connectionManager.returnConnection(connection);
			}
		}
	}

	public List<Newspaper> getAllNewspapers() {


		List<Newspaper> newspapers = new ArrayList<Newspaper>();
		Connection connection = null;
		
		try {
			
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet queryResult = statement.executeQuery(
					"select "
					+ "p.uuid, "
					+ "p.title, "
					+ "p.publisher, "
					+ "p.release_date, "
					+ "p.nr_of_copies, "
					+ "p.copies_left, "
					+ "pt.name "
					+ "from publications p"
					+ "join publication_type pton p.type = pt.id"
					+ "where pt.name = newspaper");
			
			LOGGER.info("Newspapers query completed");
			
			buildListFromQuery(queryResult, connection).forEach(newspaper -> newspapers.add((Newspaper)newspaper));
			
			LOGGER.info("Newspapers list build completed");
			return newspapers;
			
		} catch (SQLException e) {
			
			LOGGER.info("Couldn't query newspapers", e);
			throw new RepositoryException("Couldn't query newspapers",e );
		} finally {
			
			if (connection != null) {
				
				connectionManager.returnConnection(connection);
			}
		}
	}

	public void insertBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	public void insertMagazine(Magazine magazine) {
		// TODO Auto-generated method stub
		
	}

	public void insertNewspaper(Newspaper newspaper) {
		// TODO Auto-generated method stub
		
	}

	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	public void updateMagazine(Magazine magazine) {
		// TODO Auto-generated method stub
		
	}

	public void updateNewspaper(Newspaper newspaper) {
		// TODO Auto-generated method stub
		
	}

	public void deletePublication(Publication publication) {
		// TODO Auto-generated method stub
		
	}

	public void deleteBook(String uuid) {
		// TODO Auto-generated method stub
		
	}

	public void deleteMagazine(String uuid) {
		// TODO Auto-generated method stub
		
	}

	public void deleteNewspaper(String uuid) {
		// TODO Auto-generated method stub
		
	}

	public void insertPublication() {
		// TODO Auto-generated method stub
		
	}
	
	private List<Publication> buildListFromQuery(ResultSet queryResult, Connection connection) throws SQLException {
		
		List<Publication> publications = new ArrayList<Publication>();
		
		while(queryResult.next()) {

			switch(queryResult.getString("type")) {
			
			case "Book": {
				
				Book book = new Book();
				//create book
				createPublication(book, queryResult);
				
				//get authors
				getAuthors(connection, book.getUUID()).forEach(author -> book.addAuthor(author));
				
				publications.add(book);
				
				LOGGER.info("Book added to return list");
				break;
			}
			
			case "Magazine": {
				
				Magazine magazine = new Magazine();
				//create magazine
				createPublication(magazine, queryResult);
				
				//get authors
				getAuthors(connection, magazine.getUUID()).forEach(author -> magazine.addAuthor(author));
				
				publications.add(magazine);
				
				LOGGER.info("Magazine addet to return list");
				break;
			}
			
			case "Newspaper": {
				
				Newspaper newspaper = new Newspaper();
				//create newspaper
									
				publications.add(newspaper);
				
				LOGGER.info("Newspaper added to return list");
				break;
			}
				
			}				
		}
		
		return publications;
	}
	
	private void createPublication(Publication publication, ResultSet queryResult) throws SQLException{
		
		publication.setUUID(queryResult.getString("uuid"));
		publication.setTitle(queryResult.getString("title"));
		publication.setPublisher(queryResult.getString("publisher"));
		publication.setReleaseDate(queryResult.getDate("release_date"));
		publication.setNumberOfCopies(queryResult.getInt("nr_of_copies"));
		publication.setCopiesLeft(queryResult.getInt("copies_left"));
	}

	private List<Author> getAuthors(Connection connection, String uuid) throws SQLException {
		
		List<Author> authors = new ArrayList<Author>();
		
		PreparedStatement prepStat = connection.prepareStatement(
				"select a.*" + 
				"from authors a" +
				"join publications_authors pa on a.uuid = pa.authors_uuid" +
				"join publications p on p.uuid = pa.publications_uuid" +
				"where p.uuid = ? ;");
		
		LOGGER.info("Authors query successful");
		
		prepStat.setString(1, uuid);
		ResultSet currentAuthors = prepStat.executeQuery();
		
		while(currentAuthors.next()) {
			
			Author author = new Author();
			
			author.setUUID(currentAuthors.getString("uuid"));
			author.setName(currentAuthors.getString("name"));
			
			authors.add(author);
			
		}	
		
		return authors;
	}

}
