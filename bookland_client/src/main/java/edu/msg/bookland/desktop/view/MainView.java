package edu.msg.bookland.desktop.view;

public abstract class MainView {

	public static void menuForUser() {
		System.out.println("Please choose an option!");	
		System.out.println("1-Search for publication");
	}
	public static void menuInitForAdmin() {
		System.out.println("Please choose operation type!");
		System.out.println("1 -Custom Service");
		System.out.println("2 -Data Administration");
	}
	
}