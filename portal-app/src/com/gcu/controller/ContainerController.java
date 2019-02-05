package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.model.Container;
import com.gcu.model.User;
import com.gcu.service.ContainerServiceInterface;

@Controller
@RequestMapping("/container")
public class ContainerController {
	ContainerServiceInterface service;
	
	@RequestMapping(path = "/product", method = RequestMethod.GET)
	public ModelAndView productCat() {
		return new ModelAndView("productCat");
	}
	
	@RequestMapping(path = "/display/{id}", method = RequestMethod.GET)
	public ModelAndView displayContainer(@PathVariable String id) {
		// Get container matching id
		Container display = new Container();
		return new ModelAndView("monitor", "cont", display);
	}
	
	public void setContainerService(ContainerServiceInterface service) {
		this.service = service;
	}
}
