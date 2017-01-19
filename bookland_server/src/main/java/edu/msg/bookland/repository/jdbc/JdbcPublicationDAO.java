package edu.msg.bookland.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import edu.msg.bookland.common.model.AuthorDTO;
import edu.msg.bookland.common.model.BookDTO;
import edu.msg.bookland.common.model.MagazineDTO;
import edu.msg.bookland.common.model.NewspaperDTO;
import edu.msg.bookland.common.model.PublicationDTO;
import edu.msg.bookland.repository.PublicationDAO;
import edu.msg.bookland.repository.RepositoryException;

public class JdbcPublicationDAO implements PublicationDAO{

	private static final Logger LOGGER = Logger.getLogger(JdbcPublicationDAO.class);
	
	private ConnectionManager connectionManager;
	
	public JdbcPublicationDAO() {
		
		connectionManager = ConnectionManager.getInstance();
		
	}
	
	
	public List<PublicationDTO> getAllPublications() {
		
		
		List<PublicationDTO> publications = new ArrayList<PublicationDTO>();
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
					+ "pt.name as type "
					+ "from publications p "
					+ "join publication_type pt on p.type = pt.id");
			
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

	public List<BookDTO> getAllBooks() {

		List<BookDTO> books = new ArrayList<BookDTO>();
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
					+ "from publications p "
					+ "join publication_type pton p.type = pt.id "
					+ "where pt.name = book");
			
			LOGGER.info("Books query completed");
			
			buildListFromQuery(queryResult, connection).forEach(book -> books.add((BookDTO)book));
			
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

	public List<MagazineDTO> getAllMagazines() {

		List<MagazineDTO> magazines = new ArrayList<MagazineDTO>();
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
					+ "from publications p "
					+ "join publication_type pton p.type = pt.id "
					+ "where pt.name = magazine");
			
			LOGGER.info("Magazines query completed");
			
			buildListFromQuery(queryResult, connection).forEach(magazine -> magazines.add((MagazineDTO)magazine));
			
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

	public List<NewspaperDTO> getAllNewspapers() {


		List<NewspaperDTO> newspapers = new ArrayList<NewspaperDTO>();
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
					+ "from publications p "
					+ "join publication_type pton p.type = pt.id "
					+ "where pt.name = newspaper");
			
			LOGGER.info("Newspapers query completed");
			
			buildListFromQuery(queryResult, connection).forEach(newspaper -> newspapers.add((NewspaperDTO)newspaper));
			
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

	public void insertBook(BookDTO book) {

		//ha nem l�tezik az author akkor azt a service r�tegbe kell l�trehozni
		//nem kapcsol�dnak a dao-k. ink�bb a servicek
		//itt csak olyan book-ot lehet besz�rni amelyiknek m�r l�teznek az author-jai
		//teh�t egy szinttel feljebb van ez lekezelve
		//ide �gy j�n be a book, hogy az authorok l�teznek
		
		Connection connection = null;
		
		try {

			connection = connectionManager.getConnection();
			
			insertPublication(book, connection);
			
			PreparedStatement insertBookWriterRelation = connection.prepareStatement(
					"insert into publications_authors "
					+ "(publications_uuid, authors_uuid) "
					+ "values(?, ?)");
			
			for(AuthorDTO author:book.getAuthors()) {
				
				insertBookWriterRelation.setString(1, book.getUUID());
				insertBookWriterRelation.setString(2, author.getUUID());
				insertBookWriterRelation.executeQuery();
				
				LOGGER.info("Book - Author relation inserted in connection table");
			}
			
			LOGGER.info("Book insert completed successfully");
		
		} catch(SQLException e) {
			
			LOGGER.error("Failed to insert book", e);
			throw new RepositoryException("Failed to insert book", e);
		} finally {
			
			if (connection != null) {
				
				connectionManager.returnConnection(connection);
			}
		}
	}
	

	public void insertMagazine(MagazineDTO magazine) {

		Connection connection = null;
		
		try {
			
			connection = connectionManager.getConnection();
			
			insertPublication(magazine, connection);
			
			PreparedStatement insertMagazineWriterRelation = connection.prepareStatement(
					"insert into publications_authors "
					+ "(publications_uuid, authors_uuid) "
					+ "values(?, ?)");
			
			for(AuthorDTO author:magazine.getAuthors()) {
				
				insertMagazineWriterRelation.setString(1, magazine.getUUID());
				insertMagazineWriterRelation.setString(2, author.getUUID());
				insertMagazineWriterRelation.executeQuery();
				
				LOGGER.info("Magazine - Author relation inserted in connection table");
			}
			
			LOGGER.info("Magazine insert completed successfully");
		
		} catch (SQLException e) {

			LOGGER.error("Failed to insert magazine", e);
			throw new RepositoryException("Failed to insert magazine", e);
		} finally {
			
			if (connection != null) {
				
				connectionManager.returnConnection(connection);
			}
		}
		
	}

	public void insertNewspaper(NewspaperDTO newspaper) {

		Connection connection = null;
		
		try {
			
			connection = connectionManager.getConnection();
			
			insertPublication(newspaper, connection);
			
		} catch(SQLException e) {
			
			LOGGER.error("Failed to insert newspaper", e);
			throw new RepositoryException("Failed to insert newspaper", e);
		} finally {
			
			if (connection != null) {
				
				connectionManager.returnConnection(connection);
			}
		}
		
	}

	public void updateBook(BookDTO book) {

		doUpdatePublication(book);		
	}

	public void updateMagazine(MagazineDTO magazine) {
		
		doUpdatePublication(magazine);		
	}

	public void updateNewspaper(NewspaperDTO newspaper) {

		doUpdatePublication(newspaper);		
	}
	
	public void deleteBook(BookDTO book) {
		
		//csak akkor lehet torolni, ha nincs kikolcsonozve
		//ezt a service retegbe kell leelenorizni
		
		Connection connection = null;
		
		try {
			
			connection = connectionManager.getConnection();
			
			PreparedStatement deleteBook = connection.prepareStatement(
					"delete from publications "
					+ "where uuid = ?");
			
			deleteBook.setString(1, book.getUUID());
			deleteBook.execute();
			
			LOGGER.info("Book deleted");
			
			PreparedStatement deleteBookAuthorConnection = connection.prepareStatement(
					"delete from  publications_authros "
					+ "where publications_uuid = ?");
			
			deleteBookAuthorConnection.setString(1, book.getUUID());
			deleteBookAuthorConnection.executeQuery();
			
			LOGGER.info("Book and author connections deleted");
			
			LOGGER.info("Book deleted completed successfully");
			
		} catch(SQLException e) {
			
			LOGGER.error("Failed to delete book");
			throw new RepositoryException("Failed to delete book");
		} finally {
			
			if (connection != null) {
				
				connectionManager.returnConnection(connection);
			}
		}
	}

	public void deleteMagazine(MagazineDTO magazine) {

		//csak akkor lehet torolni, ha nincs kikolcsonozve
		//ezt a service retegbe kell leelenorizni
		
		Connection connection = null;
		
		try {
			
			connection = connectionManager.getConnection();
			
			PreparedStatement deleteMagazine = connection.prepareStatement(
					"delete from publications"
					+ "where uuid = ?");
			
			deleteMagazine.setString(1, magazine.getUUID());
			deleteMagazine.execute();
			
			LOGGER.info("Magazine deleted");
			
			PreparedStatement deleteMagazineAuthorConnection = connection.prepareStatement(
					"delete from  publications_authros"
					+ "where publications_uuid = ?");
			
			deleteMagazineAuthorConnection.setString(1, magazine.getUUID());
			deleteMagazineAuthorConnection.executeQuery();
			
			LOGGER.info("Magazine and author connections deleted");
			
			LOGGER.info("Magazine deleted completed successfully");
			
		} catch(SQLException e) {
			
			LOGGER.error("Failed to delete magazine");
			throw new RepositoryException("Failed to delete magazine");
		} finally {
			
			if (connection != null) {
				
				connectionManager.returnConnection(connection);
			}
		}
	}

	public void deleteNewspaper(NewspaperDTO newspaper) {

		Connection connection = null;
		
		try {
			
			connection = connectionManager.getConnection();
			
			PreparedStatement deleteNewspaper = connection.prepareStatement(
					"delete from publications "
					+ "where uuid = ?");
			
			deleteNewspaper.setString(1, newspaper.getUUID());
			deleteNewspaper.execute();
			
			LOGGER.info("Newspaper deleted");
			
			LOGGER.info("Newspaper deleted completed successfully");
			
		} catch(SQLException e) {
			
			LOGGER.error("Failed to delete newspaper");
			throw new RepositoryException("Failed to delete newspapaer");
		} finally {
			
			if (connection != null) {
				
				connectionManager.returnConnection(connection);
			}
		}
	}

	
	private List<PublicationDTO> buildListFromQuery(ResultSet queryResult, Connection connection) throws SQLException {
		
		List<PublicationDTO> publications = new ArrayList<PublicationDTO>();
		
		while(queryResult.next()) {

			switch(queryResult.getString("type")) {
			
			
			
			case "book": {
				
				BookDTO book = new BookDTO();
				//create book
				createPublication(book, queryResult);
				
				//get authors
				getAuthors(connection, book.getUUID()).forEach(author -> book.addAuthor(author));
				
				publications.add(book);
				
				LOGGER.info("Book added to return list");
				break;
			}
			
			case "magazine": {
				
				MagazineDTO magazine = new MagazineDTO();
				//create magazine
				createPublication(magazine, queryResult);
				
				//get authors
				getAuthors(connection, magazine.getUUID()).forEach(author -> magazine.addAuthor(author));
				
				publications.add(magazine);
				
				LOGGER.info("Magazine addet to return list");
				break;
			}
			
			case "newspaper": {
				
				NewspaperDTO newspaper = new NewspaperDTO();
				//create newspaper
				createPublication(newspaper, queryResult);
									
				publications.add(newspaper);
				
				LOGGER.info("Newspaper added to return list");
				break;
			}
				
			}				
		}
		
		return publications;
	}
	
	private void createPublication(PublicationDTO publication, ResultSet queryResult) throws SQLException{
		
		publication.setUUID(queryResult.getString("uuid"));
		publication.setTitle(queryResult.getString("title"));
		publication.setPublisher(queryResult.getString("publisher"));
		publication.setReleaseDate(queryResult.getDate("release_date"));
		publication.setNumberOfCopies(queryResult.getInt("nr_of_copies"));
		publication.setCopiesLeft(queryResult.getInt("copies_left"));
	}

	private List<AuthorDTO> getAuthors(Connection connection, String uuid) throws SQLException {
		
		List<AuthorDTO> authors = new ArrayList<AuthorDTO>();
		
		PreparedStatement prepStat = connection.prepareStatement(
				"select a.* " + 
				"from authors a " +
				"join publications_authors pa on a.uuid = pa.authors_uuid " +
				"join publications p on p.uuid = pa.publications_uuid " +
				"where p.uuid = ? ;");
		
		LOGGER.info("Authors query successful");
		
		prepStat.setString(1, uuid);
		ResultSet currentAuthors = prepStat.executeQuery();
		
		while(currentAuthors.next()) {
			
			AuthorDTO author = new AuthorDTO();
			
			author.setUUID(currentAuthors.getString("uuid"));
			author.setName(currentAuthors.getString("name"));
			
			authors.add(author);
			
		}	
		
		return authors;
	}
	
	private void insertPublication(PublicationDTO publication, Connection connection) throws SQLException{
		
		PreparedStatement insert = connection.prepareStatement(
				"insert into publications"
				+ "(uuid, title, publisher, release_date, nr_of_copies, copies_left, type"
				+ "values(?, ?, ?, ?, ?, ?, ?)");
		insert.setString(1, publication.getUUID());
		insert.setString(2, publication.getTitle());
		insert.setString(3, publication.getPublisher());
		insert.setDate(4, publication.getReleaseDate());
		insert.setInt(5, publication.getNumberOfCopies());
		insert.setInt(6, publication.getCopiesLeft());
		insert.setString(7, publication.getClass().getSimpleName().toLowerCase());
		
		insert.execute();			
		LOGGER.info(publication.getClass().getSimpleName() + " inserted");
	}

	private void doUpdatePublication(PublicationDTO publication){
		
		Connection connection = null;
		
		try {
		
			connection = connectionManager.getConnection();
			
			PreparedStatement updatePublication = connection.prepareStatement(
					"update publications"
					+ "set uuid = ?,"
					+ "set title = ?,"
					+ "set publisher = ?,"
					+ "set release_date = ?,"
					+ "set nr_of_copies = ?,"
					+ "set copies_left = ?,"
					+ "set type = ?");
			
			updatePublication.setString(1, publication.getUUID());
			updatePublication.setString(2, publication.getTitle());
			updatePublication.setString(3, publication.getPublisher());
			updatePublication.setDate(4, publication.getReleaseDate());
			updatePublication.setInt(5, publication.getNumberOfCopies());
			updatePublication.setInt(6, publication.getCopiesLeft());
			
			switch (publication.getClass().getSimpleName()) {
			
			case "Book":
				updatePublication.setInt(7, 1);
				break;
			case "Magazine":
				updatePublication.setInt(7, 2);
				break;
			case "Newspaper":
				updatePublication.setInt(7, 3);
				break;
			}
			
			updatePublication.execute();
			
			LOGGER.info(publication.getClass().getSimpleName() + " update was successfull");
		
		} catch (SQLException e) {

			LOGGER.error("Failed to update " + publication.getClass().getSimpleName());
			throw new RepositoryException("Failed to update " + publication.getClass().getSimpleName(), e);
		} finally {
			
			if (connection != null) {
				
				connectionManager.returnConnection(connection);
			}
		}		
	}


	@Override
	public List<PublicationDTO> searchPublication(String title) throws RepositoryException {

		
		return null;
	}


	@Override
	public int getCopiesLeft(String uuid) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setCopiesLeft(String uuid) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public PublicationDTO getPublicationByUuid(String uuid) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}
	
}