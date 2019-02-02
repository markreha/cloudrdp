package com.gcu.service;

import java.util.List;

import com.gcu.model.Container;

public interface ContainerServiceInterface {
	public boolean createContainer(Container container);

	public Container findContainer(Container container);
	
	public List<Container> viewContainer();

	public boolean updateContainer(Container container);

	public boolean deleteContainer(Container container);
}
