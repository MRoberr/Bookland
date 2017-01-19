package edu.msg.bookland.server.busines_logic;

/**
 * @author Terez Sipos
 */
public class BusinesLogicException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinesLogicException() {
		super();
	}

	public BusinesLogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinesLogicException(String message) {
		super(message);
	}

}
