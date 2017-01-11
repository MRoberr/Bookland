package edu.msg.bookland.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


public enum PropertyProvider {
	INSTANCE;

	private final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("edu.msg.bookland.res.bookland");
	private static final Logger LOGGER = Logger.getLogger(PropertyProvider.class);

	public String getProperty(String property) {
		try {
			return RESOURCE_BUNDLE.getString(property);
		} catch (MissingResourceException e) {
			LOGGER.error("missing resource exception", e);
			return "!" + property + "!";
		}
	}
}
