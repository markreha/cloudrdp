package com.gcu.service;

import java.util.List;

import com.gcu.exception.ContainerFoundException;
import com.gcu.exception.ContainerNotFoundException;
import com.gcu.exception.ImageNotFoundException;
import com.gcu.model.Container;
import com.gcu.model.User;

public interface ContainerServiceInterface 
{
	/**
	 * Create a container on the Swarm and map the relationship in the database
	 * 
	 * @param Container - container / image config for deployment
	 * @param User - used to associate container to the username
	 * @result boolean - result of the execution
	 * @throws ContainerFoundException - container requested already exists
	 * @throws ImageNotFoundException 
	 * @throws DockerSwarmException - docker unsuccessfully executed request
	 */
	public boolean createContainer(Container container, User user) throws ContainerFoundException, ImageNotFoundException;

	/**
	 * Get a list of all the Containers created by the user from the database
	 * 
	 * @param User - to define what containers to return
	 * @result List<Container> - assembled list of containers
	 * @throws ContainerNotFoundException - no containers associated to user
	 */
	public List<Container> getAllContainers(User user) throws ContainerNotFoundException;
	
	/**
	 * Delete the container from the Swarm and database
	 * 
	 * @param Container - selected by user
	 * @param User - selected container, used for association
	 * @result boolean - result of the execution
	 * @throws ContainerNotFoundException - container is not mapped to user
	 * @throws DockerSwarmException - docker unsuccessfully executed request
	 */
	public boolean deleteContainer(Container container, User user) throws ContainerNotFoundException;
}
