package com.gcu.service;

import com.gcu.model.User;

public interface UserServiceInterface {
	public boolean createUser(User user);

	public boolean findUser(User user);

	public boolean updateUser(User user);

	public boolean deleteUser(User user);
}
