package edu.msg.bookland.common.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * All java model classes are derived from this Base Entity class representing a
 * universally unique id except for the Borrowing model class.
 * 
 * @author Majai Robert
 * @author Simo Zoltan
 * @author Sipos Terez
 *
 */
public abstract class BaseEntityDTO implements Serializable {

	private static final long serialVersionUID = -7599158988866587821L;

	private String uuId;

	public String getUUID() {
		if (uuId == null) {
			uuId = UUID.randomUUID().toString();
		}
		return uuId;
	}

	public void setUUID(String uuID) {
		uuId = uuID;
	}

}
