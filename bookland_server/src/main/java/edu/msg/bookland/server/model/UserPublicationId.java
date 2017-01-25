package edu.msg.bookland.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;

@Embeddable
public class UserPublicationId implements Serializable {

	@Transient
	private static final long serialVersionUID = 4847075231038618569L;

	@Column(name = "user_uuid")
	private String userId;

	@Column(name = "publications_uuid")
	private String publicationId;

	public UserPublicationId() {

	}

	public UserPublicationId(String userId, String publicationId) {
		this.userId=userId;
		this.publicationId=publicationId;
	}

	public void setUserId(String uuid) {

		userId = uuid;
	}

	public void SetPublicationId(String uuid) {

		publicationId = uuid;
	}

	public String getUserId() {

		return userId;
	}

	public String getPublicationId() {

		return publicationId;
	}

	@Override
	public int hashCode() {

		return (int) userId.hashCode() + (int) publicationId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		}

		if (obj == null) {
			return false;

		}
		if (!(obj instanceof UserPublicationId)) {
			return false;
		}

		UserPublicationId pk = (UserPublicationId) obj;
		return pk.userId.equals(userId) && pk.publicationId.equals(publicationId);
	}

}
