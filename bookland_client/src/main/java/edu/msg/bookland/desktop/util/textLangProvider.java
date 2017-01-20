package edu.msg.bookland.desktop.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public enum textLangProvider {
	INSTANCE;

	private ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("booklandBundle",
			new Locale(""));

	public String getProperty(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return "!" + key + "!";
		}
	}

	public void setLocale(Locale locale) {
		RESOURCE_BUNDLE = ResourceBundle.getBundle("booklandBundle", locale);
	}
}
