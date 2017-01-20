package edu.msg.bookland.server.business_logic.basic;

import java.util.List;

import edu.msg.bookland.server.business_logic.ArticleBL;
import edu.msg.bookland.server.business_logic.BusinesLogicException;
import edu.msg.bookland.server.model.Author;
import edu.msg.bookland.server.repository.ArticleDAO;

/**
 * Implements methods of {@link ArticleDAO}
 * 
 * @author Sipos Terez
 *
 */
public class BasicArticleBL implements ArticleBL {

	@Override
	public List<Author> getAllAuthors() throws BusinesLogicException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertAuthor(Author author) throws BusinesLogicException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAuthor(Author author) throws BusinesLogicException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAuthor(String id) throws BusinesLogicException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Author> searchAuthor(String name) throws BusinesLogicException {
		// TODO Auto-generated method stub
		return null;
	}

}
