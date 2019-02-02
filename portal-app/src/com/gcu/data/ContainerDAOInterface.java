package com.gcu.data;

import java.util.List;

import com.gcu.model.Container;

public interface ContainerDAOInterface {
	public boolean createContainer(Container container);

	public Container findContainer(Container container);
	
	public List<Container> viewContainer();

	public boolean updateContainer(Container container);

	public boolean deleteContainer(Container container);
}
