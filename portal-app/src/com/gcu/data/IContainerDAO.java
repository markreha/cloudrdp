package com.gcu.data;

import com.gcu.model.Container;

public interface IContainerDAO {
	public boolean createContainer(Container container);

	public Container findContainer(Container container);

	public boolean updateContainer(Container container);

	public boolean deleteContainer(Container container);
}
