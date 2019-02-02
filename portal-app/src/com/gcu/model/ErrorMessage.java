package com.gcu.model;

public class ErrorMessage extends Message {
	String title, message, link;

	/**
	 * Default constructor. Assigns generic messages and links back to home.
	 */
	public ErrorMessage() {
		super("Error", "You have encountered a generic error.", "/");
	}

	/**
	 * Non-default constructor. Takes an error title, message for the user, 
	 * and link to return user back to.
	 * @param title
	 * @param message
	 * @param link
	 */
	public ErrorMessage(String title, String message, String link) {
		super(title, message, link);
	}
}
