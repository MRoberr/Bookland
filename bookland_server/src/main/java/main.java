import edu.msg.bookland.repository.jdbc.UserDAO;

public class main {

	public static void main(String[] args) {
		UserDAO u = new UserDAO();
		System.out.println(u.getAllUsers());

	}

}
