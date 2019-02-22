package com.gcu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.UserDAOInterface;
import com.gcu.exception.UserErrorException;
import com.gcu.exception.UserFoundException;
import com.gcu.exception.UserNotFoundException;
import com.gcu.model.User;

public class UserService implements UserServiceInterface {
	
	@Autowired
	private UserDAOInterface dao;
	
	/**
	 * Calls DAO to check if user already exists before creating.
	 * Create user if an instance of the user is not found.
	 * 
	 * @param User user
	 * @return boolean
	 * @throws UserErrorException, UserFoundException
	 */
	@Override
	public boolean createUser(User user) throws UserErrorException, UserFoundException
	{
		// Check if the user exists
		User exists = dao.findUser(user);
		
		// Throw an exception if a user is found
		if(exists != null)
		{	
			throw new UserFoundException();
		}
		
		// Create the user
		boolean success = dao.createUser(user);
		
		// Throw exception if theres a server error adding user to DB
		if(success == false)
		{
			throw new UserErrorException();
		}
		
		// Return true that user was successfully created.
		return true;
	}

	@Override
	public User findUser(User user) throws UserNotFoundException
	{
		User verified = dao.findUser(user);
		
		if(verified == null)
		{
			throw new UserNotFoundException();
		}
		
		return verified;
	}

	@Override
	public boolean updateUser(User user) {
		return dao.updateUser(user);
	}

	@Override
	public boolean deleteUser(User user) {
		return dao.deleteUser(user);
	}
}
