package com.gcu.exception;

public class BadLoginException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadLoginException() {
		// TODO Auto-generated constructor stub
	}

	public BadLoginException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BadLoginException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BadLoginException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BadLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
