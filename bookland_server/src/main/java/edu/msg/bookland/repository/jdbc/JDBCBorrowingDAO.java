package edu.msg.bookland.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import edu.msg.bookland.model.Borrowing;
import edu.msg.bookland.repository.BorrowingDAO;
import edu.msg.bookland.repository.RepositoryException;

public class JDBCBorrowingDAO implements BorrowingDAO {

	private ConnectionManager connectionManager;
	private static final Logger LOGGER = Logger.getLogger(JDBCBorrowingDAO.class);

	public JDBCBorrowingDAO() throws RepositoryException {
		connectionManager = ConnectionManager.getInstance();
	}

	@Override
	public void insertBorrowing(Borrowing borrowing) {
		Connection con = null;
		try {
			con = connectionManager.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("Insert into publication_borrowings "
					+ "(publications_uuid, user_uuid, borrowing_date, deadline) " + "values (?,?,?,?)");
			preparedStatement.setString(1, borrowing.getPublicationId());
			preparedStatement.setString(2, borrowing.getUserId());
			preparedStatement.setDate(3, borrowing.getBorrowingDate());
			preparedStatement.setDate(4, borrowing.getDeadline());
			preparedStatement.execute();
			LOGGER.info("Insterted a borrowing");
		} catch (SQLException e) {
			LOGGER.error("Cannot insert borrowing");
			throw new RepositoryException("Cannot insert borrowing", e);
		} finally {
			if (con != null) {
				connectionManager.returnConnection(con);
			}
		}

	}

	@Override
	public void deleteBorrowing(Borrowing borrowing) {

		Connection con = null;
		try {
			con = connectionManager.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(
					"Delete from publication_borrowings where publications_uuid = ? and user_uuid = ?");
			preparedStatement.setString(1, borrowing.getPublicationId());
			preparedStatement.setString(2, borrowing.getUserId());
			preparedStatement.execute();
			LOGGER.info("Succesfully deleted a borrowing");
		} catch (SQLException e) {
			LOGGER.error("Cannot delete a borrowing");
			throw new RepositoryException("Cannot delete a borrowing", e);
		} finally {
			if (con != null) {
				connectionManager.returnConnection(con);
			}
		}
	}

}
