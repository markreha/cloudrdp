package com.gcu.service;

import com.gcu.exception.UserFoundException;
import com.gcu.exception.UserNotFoundException;
import com.gcu.model.User;

public interface UserServiceInterface 
{
	/**
	 * Checks if the username is already reserved.
	 * 	If avaialable, creates a unique user in the database
	 * 
	 * @param User - user making the request
	 * @return boolean - result of the execution 
	 * @throws UserFoundException - user already exists in the database
	 */
	public boolean createUser(User user) throws UserFoundException;

	/**
	 * Validates the User's credentials.
	 * 
	 * @param User - the user being verified
	 * @result User - the complete model of the user stored from the database
	 * @throws UserNotFoundException - user credentials did not match with anything
	 */
	public User findUser(User user) throws UserNotFoundException;

	/**
	 * Checks to determine if user exists before updating. 
	 * 	Updates a users configuration fields for Docker Swarm
	 * 
	 * @param User - user being updated
	 * @result boolean - result of the execution
	 * @throws UserNotFoundException
	 */
	public boolean updateUser(User user);
}
