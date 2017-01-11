package edu.msg.bookland.model;

public class User extends BaseEntity {

	private static final long serialVersionUID = 2326611259099577676L;

	private String name;
	private String password;
	private UserType userType;
	private int loyaltyIndex;

	public User(String name) {

		this.name = name;
		loyaltyIndex = 10;
		userType = UserType.READER;
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

}
