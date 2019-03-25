package com.gcu.data;

import java.util.List;

import com.gcu.model.Container;

public interface ContainerDAOInterface
{
	public List<Container> findAllByUsername(final String username);
	
	public boolean findIfExists(Container container);
	
	public boolean create(Container container);
	
	public boolean update(Container container, String docker);
	
	public boolean delete(Container container);
}
