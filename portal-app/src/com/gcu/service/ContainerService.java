package com.gcu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.ContainerDAOInterface;
import com.gcu.edu.dockerapi.CreateSwarmServiceInfo;
import com.gcu.edu.dockerapi.DockerApi;
import com.gcu.exception.ContainerFoundException;
import com.gcu.exception.ContainerNotFoundException;
import com.gcu.exception.DockerSwarmException;
import com.gcu.exception.ImageNotFoundException;
import com.gcu.model.Container;
import com.gcu.model.Image;
import com.gcu.model.User;

public class ContainerService implements ContainerServiceInterface {
	/**
	 * Dependency Injected DAO Service
	 */
	@Autowired
	private ContainerDAOInterface containerDAO;

	@Autowired
	private ImageServiceInterface imageService;

	private String piAddress = "tcp://172.24.84.164:2375"; // IP Address to the Docker Engine (running on the Raspberry Pi)
	private String registryUrl = "https://hub.docker.com"; // Use the Docker Hub Registry
	private String registryEmail = "mark.reha@gcu.edu"; // Docker Registry User Email Address
	private String registryUsername = "mark.reha@gcu.edu"; // Docker Registry Username
	private String registryPassword = "Starman1"; // Docker Registry Password

	/**
	 * Create a container on the Swarm and map the relationship in the database
	 * 
	 * @param Container - container / image config for deployment
	 * @param User      - used to associate container to the username
	 * @result boolean - result of the execution
	 * @throws ContainerFoundException - container requested already exists
	 * @throws ImageNotFoundException
	 * @throws DockerSwarmException    - docker unsuccessfully executed request
	 */
	@Override
	public boolean createContainer(Container container, User user)
			throws ContainerFoundException, ImageNotFoundException {
		// Validate the Image being called for exists. Exceptions handled in
		// imageService
		Image image = imageService.findImageById(container.getImageId());

		// Set the username to the container
		container.setUsername(user.getUsername());

		// find if an instance of this container and image combo exist to the user
		boolean containerExists = containerDAO.findIfExists(container);

		// If exists, throw exception and exit method
		if (containerExists == true) {
			throw new ContainerFoundException();
		}
		DockerApi api = new DockerApi(piAddress, registryUrl, registryEmail, registryUsername, registryPassword);
		String name = container.getName();
		int replicas = 1;
		float storage = container.getStorage();
		CreateSwarmServiceInfo info = new CreateSwarmServiceInfo(image.getInstance().toLowerCase(), name,
				container.getCpu(), container.getStorage(), replicas, 80, 1000);
		String id = api.createSwarmService(info);
		if (id != null) {
			// Log success
			System.out.println("=======> Nginx Swarm created successfully with an ID of " + id + ".");
			// Save ID
			update(container, id);
		} else {
			System.out.println("=======> Nginx Swarm creation failed.");
		}
		/*
		 * TODO: Get the Docker Swarm Generated DockerID. Current DockerID is randomly
		 * generated
		 * 
		 * 
		 * // Request API to create the container and return the DockerID String
		 * dockerId = API.createSwarmService(container);
		 * 
		 * // If service did not return an ID, throw exception and exit method
		 * if(dockerId == null) { throw new DockerSwarmException(); }
		 */

		// Random Number generator for the DockerId
		String dockerId = new String();
		for (int i = 20; i > 0; i--) {
			dockerId += Integer.toString((int) Math.random() * 9);
		}

		// Set the new DockerId to the container
		container.setDockerId(dockerId);

		// Call Container DAO to map the relationship between container and the user
		boolean result = containerDAO.create(container);

		// Return the result
		return result;
	}

	/**
	 * Get a list of all the Containers created by the user from the database
	 * 
	 * @param User - to define what containers to return
	 * @result List<Container> - assembled list of containers
	 * @throws ContainerNotFoundException - no containers associated to user
	 */
	@Override
	public List<Container> getAllContainers(User user) throws ContainerNotFoundException {
		// Call Container DAO to assemble a list of all containers associated to the
		// username
		List<Container> containers = containerDAO.findAllByUsername(user.getUsername());

		// If list is empty, throw exception
		if (containers.isEmpty()) {
			throw new ContainerNotFoundException();
		}

		// return the list of containers
		return containers;
	}

	@Override
	public boolean update(Container container, String docker) {
		return containerDAO.update(container, docker);
	}

	/**
	 * Delete the container from the Swarm and database
	 * 
	 * @param Container - selected by user
	 * @param User      - selected container, used for association
	 * @result boolean - result of the execution
	 * @throws ContainerNotFoundException - container is not mapped to user
	 * @throws DockerSwarmException       - docker unsuccessfully executed request
	 */
	@Override
	public boolean deleteContainer(Container container, User user) throws ContainerNotFoundException {
		// Set the username to the container
		container.setUsername(user.getUsername());

		// Find if that the container being deleted exists
		boolean exists = containerDAO.findIfExists(container);

		// If container does not exist, throw exception and exit method
		if (exists == false) {
			throw new ContainerNotFoundException();
		}

		/*
		 * TODO: Request the API to delete the Container from the Swarm
		 * 
		 * // Request API to delete the container boolean success =
		 * API.deleteSwarmService(container.dockerId);
		 * 
		 * // If the API did not delete the container from Swarm, throw exception if
		 * (success == false) { throw new DockerSwarmException(); }
		 * 
		 */

		// Call Container DAO to delete the container relationship
		boolean result = containerDAO.delete(container);

		// Return the result
		return result;
	}
}
