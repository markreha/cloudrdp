package com.gcu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.UserDAOInterface;
import com.gcu.exception.UserFoundException;
import com.gcu.exception.UserNotFoundException;
import com.gcu.model.User;

public class UserService implements UserServiceInterface
{
	/**
	 * Dependency Injection DAO Service
	 */
	@Autowired
	private UserDAOInterface userDAO;
	
	/**
	 * Checks if the username is already reserved.
	 * 	If avaialable, creates a unique user in the database
	 * 
	 * @param User - user making the request
	 * @return boolean - result of the execution 
	 * @throws UserFoundException - user already exists in the database
	 */
	@Override
	public boolean createUser(User user) throws UserFoundException
	{
		// Check if the user exists
		boolean exists = userDAO.findIfExists(user);
		
		// if the user already exists, throw an exception and exit method
		if(exists == true)
		{	
			throw new UserFoundException();
		}
		
		// Create the user
		boolean result = userDAO.create(user);
		
		// Return the result
		return result;
	}

	/**
	 * Validates the User's credentials.
	 * 
	 * @param User - the user being verified
	 * @result User - the complete model of the user stored from the database
	 * @throws UserNotFoundException - user credentials did not match with anything
	 */
	@Override
	public User findUser(User user) throws UserNotFoundException
	{
		// Calls the DAO to find the exact instance of the user
		User verified = userDAO.find(user);
		
		// if the user was not verified, throw exception
		if(verified == null)
		{
			throw new UserNotFoundException();
		}
		
		// return the verified user
		return verified;
	}

	/**
	 * Checks to determine if user exists before updating. 
	 * 	Updates a users configuration fields for Docker Swarm
	 * 
	 * @param User - user being updated
	 * @result boolean - result of the execution
	 * @throws UserNotFoundException
	 */
	@Override
	public boolean updateUser(User user) 
	{
		/*
		 * TODO: Update user information like custom configuration 
		 * 		variables for Swarm
		 * 
		 * Check if User exists in the DB
		 * 
		 * Throw exception if the user does not
		 * 
		 * Update the user config fields in the database
		 * 
		 * Return result
		 */
		
		return false;
	}
}
