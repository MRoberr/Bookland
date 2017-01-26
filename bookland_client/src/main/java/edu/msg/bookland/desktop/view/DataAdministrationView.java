
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
	}

	public static void menuForAdminDataAUsers() {
		System.out.println(getProperty("chooseAnOption"));
		System.out.println("1 -" + getProperty("optionInsertUser"));
		System.out.println("2 -" + getProperty("optionUpdateUser"));
		System.out.println("3 -" + getProperty("optionDeleteUser"));
		System.out.println("4 -" + getProperty("optionShowAllUsers"));
		System.out.println("5 -" + getProperty("optionSearchForUser"));
	}

	public static void menuForAdminDataAUsersUpdate() {
		System.out.println(getProperty("chooseAnOption"));
		System.out.println("1 -" + getProperty("optionUpdateJustName"));
		System.out.println("2 -" + getProperty("optionUpdateJustEmail"));
		System.out.println("3 -" + getProperty("optionUpdateJustPassword"));
		System.out.println("4 -" + getProperty("optionUpdateAll"));
	}

	public static void menuForAdminDataAAuthors() {
		System.out.println(getProperty("chooseAnOption"));
		System.out.println("1 -" + getProperty("optionSearchForAuthor"));
		System.out.println("2 -" + getProperty("optionInsertAuthor"));
		System.out.println("3 -" + getProperty("optionUpdateAuthor"));
		System.out.println("4 -" + getProperty("optionDeleteAuthor"));
		System.out.println("5 -" + getProperty("optionShowAllAuthors"));
	}

	public static void menuForAdminDataAPublications() {
		System.out.println(getProperty("chooseAnOption"));
		System.out.println("1 -" + getProperty("optionSearchForPublication"));
		System.out.println("2 -" + getProperty("optionInsertPublication"));
		System.out.println("3 -" + getProperty("optionUpdatePublication"));
		System.out.println("4 -" + getProperty("optionDeletePublication"));
		System.out.println("5 -" + getProperty("optionShowAllPublications"));
	}

	private static String getProperty(String s) {
		return textLangProvider.INSTANCE.getProperty(s);
	}

}