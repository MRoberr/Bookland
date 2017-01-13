package edu.msg.bookland.model;

/**
 * User class.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 *
 */

public class User extends BaseEntity {

	private static final long serialVersionUID = 2326611259099577676L;

	private String name;
	private String password;
	private UserType userType;
	private int loyaltyIndex;
	private String email;

	public User() {
		
	}
	
	public User(String name) {
		this.name = name;
		loyaltyIndex = 10;
		userType = UserType.READER;
	}

	public User(String name, String email, UserType userType, int loyaltyIndex) {
		super();
		this.name = name;
		this.userType = userType;
		this.loyaltyIndex = loyaltyIndex;
		this.email = email;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public int getLoyaltyIndex() {
		return loyaltyIndex;
	}

	public void setLoyaltyIndex(int loyaltyIndex) {
		this.loyaltyIndex = loyaltyIndex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User: name=" + name + ", userType=" + userType + ", loyaltyIndex=" + loyaltyIndex + ", email=" + email;
	}

}
