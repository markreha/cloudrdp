package com.gcu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.edu.dockerapi.CreateSwarmServiceInfo;
import com.gcu.edu.dockerapi.DockerApi;
import com.gcu.exception.ContainerFoundException;
import com.gcu.exception.ContainerNotFoundException;
import com.gcu.exception.ImageNotFoundException;
import com.gcu.model.Container;
import com.gcu.model.Image;
import com.gcu.model.User;
import com.gcu.service.ContainerServiceInterface;
import com.gcu.service.ImageServiceInterface;

@Controller
@SessionAttributes("token")
@RequestMapping("/container")
public class ContainerController 
{
	@Autowired
	private ContainerServiceInterface containerService;
	
	@Autowired
	private ImageServiceInterface imageService;
	
	static String piAddress = "tcp://10.0.1.153:2375";				// IP Address to the Docker Engine (running on the Raspberry Pi)
	static String registryUrl = "https://hub.docker.com";			// Use the Docker Hub Registry
	static String registryEmail = "mark.reha@gcu.edu";				// Docker Registry User Email Address
	static String registryUsername = "mark.reha@gcu.edu";			// Docker Registry Username
	static String registryPassword = "Starman1";					// Docker Registry Password
	static DockerApi api = null;									// Instance of the GCU Docker API Wrapper class

	@GetMapping("/catalog")
	public ModelAndView productCat(ModelMap model) 
	{
		try 
		{
			// Call Container Service to assemble all the containers made by the user
			List<Container> containers = containerService.getAllContainers((User) model.get("token"));
			
			// Return containerList as homepage
			ModelAndView mv = new ModelAndView("containerList");
			mv.addObject("containers", containers);
			return mv;

		}
		// Continue to containerList. JSP states the list is empty
		catch(ContainerNotFoundException e)
		{
			ModelAndView mv = new ModelAndView("containerList");
			return mv;
		}
	}
	
	@PostMapping("/createContainer")
	public ModelAndView createContainer(@Valid @ModelAttribute("container") Container container, 
			BindingResult validate, ModelMap model)
	{
		try 
		{
			if(validate.hasErrors())
			{
				return new ModelAndView("productCat");
			}
			DockerApi api = new DockerApi(piAddress, registryUrl, registryEmail, registryUsername, registryPassword);
			Image image = imageService.findImageById(container.getImageId());
			String name = container.getName();
			int replicas = 1;
			float storage = container.getStorage();
			CreateSwarmServiceInfo info = new CreateSwarmServiceInfo(image.getInstance(), container.getName(), container.getCpu(), 
					container.getStorage(), replicas, 80, 1000);
			String id = api.createSwarmService(info);
			if(id != null) {
				// Log success
				System.out.println("=======> Nginx Swarm created successfully with an ID of " + id + ".");
				// Save ID
				containerService.update(container, id);
			}
			else {
				System.out.println("=======> Nginx Swarm creation failed.");
			}
			
			// Call the container service to create the container for the user
			containerService.createContainer(container, (User) model.get("token"));
			
			// Redirect the user to the container catalog
			return new ModelAndView("redirect:/container/catalog");
		} 
		catch(ImageNotFoundException e)
		{
			ModelAndView mv = new ModelAndView("productCat");
			mv.addObject("message", "The Docker Image reference was not found.");
			return mv;
		}
		catch(ContainerFoundException e)
		{
			ModelAndView mv = new ModelAndView("productCat");
			mv.addObject("message", "The container you are trying to make already exists.");
			return mv;
		}
	}
}
