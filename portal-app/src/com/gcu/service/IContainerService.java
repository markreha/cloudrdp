package com.gcu.service;

import com.gcu.model.Container;

public interface IContainerService {
	public boolean createContainer(Container container);

	public Container findContainer(Container container);

	public boolean updateContainer(Container container);

	public boolean deleteContainer(Container container);
}
