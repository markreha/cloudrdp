package com.gcu.model;

public class Message {
	String title, message, link;

	/**
	 * Default constructor. Assigns generic messages and links back to home.
	 */
	public Message() {
		title = "Generic Message";
		message = "You have encountered a generic message.";
		link = "/";
	}

	/**
	 * Non-default constructor. Takes a title, message for the user, and link to
	 * return user back to.
	 * 
	 * @param title
	 * @param message
	 * @param link
	 */
	public Message(String title, String message, String link) {
		super();
		this.title = title;
		this.message = message;
		this.link = link;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
}
