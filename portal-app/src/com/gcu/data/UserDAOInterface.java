package com.gcu.data;

import com.gcu.model.User;

public interface UserDAOInterface {
	public boolean createUser(User user);

	public boolean findUser(User user);

	public boolean updateUser(User user);

	public boolean deleteUser(User user);
}
