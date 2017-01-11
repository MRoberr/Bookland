import edu.msg.bookland.repository.jdbc.JdbcUserDAO;

public class main {

	public static void main(String[] args) {
		JdbcUserDAO u = new JdbcUserDAO();
		System.out.println(u.getAllUsers());

	}

}
