package com.gcu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.UserDAOInterface;
import com.gcu.model.User;

public class UserService implements UserServiceInterface {
	
	@Autowired
	private UserDAOInterface dao;
	
	@Override
	public boolean createUser(User user) {
		return dao.createUser(user);
	}

	@Override
	public boolean findUser(User user) {
		return dao.findUser(user);
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
