package edu.msg.bookland.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	
	@Transient
	private static final long serialVersionUID = -7599158988866587821L;
	
	/**
	 * {@link baseEntity#uuId} Represents a unique id.
	 */
	@Id
	@Column(name = "uuid", nullable = true, unique = true)
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
