package edu.msg.bookland.desktop;

public class RequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequestException() {
		super();
	}

	public RequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestException(String message) {
		super(message);
	}

}