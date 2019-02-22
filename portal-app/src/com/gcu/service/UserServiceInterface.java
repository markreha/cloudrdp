package com.gcu.service;

import com.gcu.exception.UserErrorException;
import com.gcu.exception.UserFoundException;
import com.gcu.exception.UserNotFoundException;
import com.gcu.model.User;

public interface UserServiceInterface 
{
	public boolean createUser(User user) throws UserErrorException, UserFoundException;

	public User findUser(User user) throws UserNotFoundException;

	public boolean updateUser(User user);

	public boolean deleteUser(User user);
}
