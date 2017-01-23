package edu.msg.bookland.desktop.view;

import edu.msg.bookland.desktop.util.textLangProvider;

/**
 * View for custom service menu.
 * 
 * @author Simo Zoltan
 *
 */
public abstract class CustomServiceView {

	public static void menuForAdminCustomS() {
		System.out.println("Please choose an option!");
		System.out.println("1 -Borrow publication");
		System.out.println("2 -Return publication");
	}
	
	private static String getProperty(String s) {
		return textLangProvider.INSTANCE.getProperty(s);
	}

}