package com.gcu.data;

import com.gcu.model.User;

public interface UserDAOInterface
{
	public User find(final User user);
	public boolean findIfExists(final User user);
	public boolean create(final User user);
	public boolean update(final User user);
}
