package com.gcu.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.Container;
import com.gcu.service.ContainerServiceInterface;

@RestController
@RequestMapping("/service1")
public class DockerRest {
	ContainerServiceInterface service;

	// Spring Rest service to return all valid containers in the database.
	@GetMapping("/allcontainer")
	public List<Container> getAllContainer() {
		return service.viewContainer();
	}

	// Spring Rest service to return all valid containers in the database.
	// TODO: Pass in a model
	@GetMapping("/container")
	public Container getContainer() {
		return service.findContainer(null);
	}

	public void setContainerService(ContainerServiceInterface service) {
		this.service = service;
	}
}
