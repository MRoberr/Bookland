package edu.msg.bookland.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * PropertyProvider to get properties from file using ResourceBundle
 * @author Terez Sipos
 */
public class PropertyProvider {

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("bookland");
	private static final Logger LOGGER = Logger.getLogger(PropertyProvider.class);
	
	private PropertyProvider(){		
	}
/**
 * get property using ResourceBundle
 * @param property - name of property
 * @return value of the property
 */
	public static String getProperty(String property) {
		try {
			return RESOURCE_BUNDLE.getString(property);
		} catch (MissingResourceException e) {
			LOGGER.error("missing resource exception", e);
			return "!" + property + "!";
		}
	}
}
