
package edu.msg.bookland.desktop.view;

import edu.msg.bookland.desktop.util.textLangProvider;

/**
 * View for data administration menu.
 * 
 * @author Simo Zoltan
 * @author Szocs Csilla
 *
 */
public abstract class DataAdministrationView {	
	
	public static void menuForAdminDataA() {
		System.out.println(getProperty("chooseAnOption"));
		System.out.println("1 -" + getProperty("operationUserManagement"));
		System.out.println("2 -" + getProperty("operationAuthorManagement"));
		System.out.println("3 -" + getProperty("operationPublicationManagement"));
		//System.out.println("4 -Borrowing management");
	}
	
	public static void menuForAdminDataAUsers() {
		System.out.println(getProperty("chooseAnOption"));
		System.out.println("1 -Insert User");
		System.out.println("2 -Update User");
		System.out.println("3 -Delete User");
		//System.out.println("5 -Show all Users");
	}
	
	public static void menuForAdminDataAAuthors() {
		System.out.println(getProperty("chooseAnOption"));
		System.out.println("1 -Search for Author");
		System.out.println("2 -Insert Author");
		System.out.println("3 -Update Author");
		System.out.println("4 -Delete Author");
		//System.out.println("5 -Show all Authors");
	}
	
	public static void menuForAdminDataABorrowings() {
		System.out.println(getProperty("chooseAnOption"));
		System.out.println("1 -Search for Borrowing");
		System.out.println("2 -Insert Borrowing");
		System.out.println("3 -Update Borrowing");
		System.out.println("4 -Delete Borrowing");
		//System.out.println("5 -Show all Borrowings");
	}
	
	public static void menuForAdminDataAPublications() {
		System.out.println(getProperty("chooseAnOption"));
		System.out.println("1 -Search for Publication");
		System.out.println("2 -Insert Publication");
		System.out.println("3 -Update Publication");
		System.out.println("4 -Delete Publication");
		//System.out.println("5 -Show all Publications");
	}
	
	private static String getProperty(String s) {
		return textLangProvider.INSTANCE.getProperty(s);
	}
	
}