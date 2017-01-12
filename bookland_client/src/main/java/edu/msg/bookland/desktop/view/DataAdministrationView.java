package edu.msg.bookland.desktop.view;

public abstract class DataAdministrationView {	
	
	public static void menuForAdminDataA() {
		System.out.println("Please choose an option!");
		System.out.println("1 -Publication management");
		System.out.println("2 -Author management");
		System.out.println("3 -User management");
		System.out.println("4 -Borrowing management");
	}
	
	public static void menuForAdminCustomS() {
		System.out.println("Please choose an option!");
		System.out.println("1 -Search for publication");
		System.out.println("2 -Borrow publication");
		System.out.println("3 -Return publication");
	}
	
	public static void menuForAdminDataAUsers() {
		System.out.println("Please choose an option!");
		System.out.println("1 -Search for User");
		System.out.println("2 -Insert User");
		System.out.println("3 -Update User");
		System.out.println("4 -Delete User");
		//System.out.println("5 -Show all Users");
	}
	
	public static void menuForAdminDataAAuthors() {
		System.out.println("Please choose an option!");
		System.out.println("1 -Search for Author");
		System.out.println("2 -Insert Author");
		System.out.println("3 -Update Author");
		System.out.println("4 -Delete Author");
		//System.out.println("5 -Show all Authors");
	}
	
	public static void menuForAdminDataABorrowings() {
		System.out.println("Please choose an option!");
		System.out.println("1 -Search for Borrowing");
		System.out.println("2 -Insert Borrowing");
		System.out.println("3 -Update Borrowing");
		System.out.println("4 -Delete Borrowing");
		//System.out.println("5 -Show all Borrowings");
	}
	
	public static void menuForAdminDataAPublications() {
		System.out.println("Please choose an option!");
		System.out.println("1 -Search for Publication");
		System.out.println("2 -Insert Publication");
		System.out.println("3 -Update Publication");
		System.out.println("4 -Delete Publication");
		//System.out.println("5 -Show all Publications");
	}
	
}
