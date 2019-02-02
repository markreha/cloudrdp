package com.gcu.service;

import java.util.List;

import com.gcu.data.ContainerDAOInterface;
import com.gcu.model.Container;

public class ContainerService implements ContainerServiceInterface {
	ContainerDAOInterface data;

	@Override
	public boolean createContainer(Container container) {
		return data.createContainer(container);
	}

	@Override
	public Container findContainer(Container container) {
		return data.findContainer(container);
	}

	@Override
	public List<Container> viewContainer() {
		return data.viewContainer();
	}
	
	@Override
	public boolean updateContainer(Container container) {
		return data.updateContainer(container);
	}

	@Override
	public boolean deleteContainer(Container container) {
		return data.deleteContainer(container);
	}
}
