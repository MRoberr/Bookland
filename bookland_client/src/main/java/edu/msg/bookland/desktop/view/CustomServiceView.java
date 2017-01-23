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
		System.out.println(getProperty("chooseAnOption"));
		System.out.println("1 -" + getProperty("operationBorrowPublication"));
		System.out.println("2 -" + getProperty("operationReturnPublication"));
	}
	
	private static String getProperty(String s) {
		return textLangProvider.INSTANCE.getProperty(s);
	}

}