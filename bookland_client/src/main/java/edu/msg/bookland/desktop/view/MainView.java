package edu.msg.bookland.desktop.view;

import edu.msg.bookland.desktop.util.textLangProvider;

/**
 * View for main menus.
 * 
 * @author Simo Zoltan
 *
 */
public abstract class MainView {

	public static void menuForLanguage() {
		System.out.println("Choose language!/ Valasszon nyelvet!/ Alegeti limba!");
		System.out.println("1 -English");
		System.out.println("2 -Magyar");
		System.out.println("3 -Romana");
	}

	public static void menuForUser() {
		System.out.println(getProperty("chooseAnOption"));
		System.out.println("1 -" + getProperty("searchPublication"));
		System.out.println("2 -" + getProperty("operationLanguageChange"));
	}

	public static void menuInitForAdmin() {
		System.out.println(getProperty("chooseOperationType"));
		System.out.println("1 -" + getProperty("operationCustomService"));
		System.out.println("2 -" + getProperty("operationDataAdministration"));
		System.out.println("3 -" + getProperty("operationLanguageChange"));
	}

	private static String getProperty(String s) {
		return textLangProvider.INSTANCE.getProperty(s);
	}

}