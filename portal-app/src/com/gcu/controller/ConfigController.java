package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.service.ContainerServiceInterface;

@Controller
@RequestMapping("/config")
public class ConfigController {
	ContainerServiceInterface service;
	
	@RequestMapping(path = "/config", method = RequestMethod.GET)
	public ModelAndView configure() {
		return new ModelAndView("productCat");
	}
	
	public void setContainerService(ContainerServiceInterface service) {
		this.service = service;
	}
}
