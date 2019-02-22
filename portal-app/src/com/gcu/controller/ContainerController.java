package com.gcu.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.model.Container;
import com.gcu.service.ContainerServiceInterface;

@Controller
@RequestMapping("/container")
public class ContainerController 
{
	@SuppressWarnings("unused")
	@Autowired
	private ContainerServiceInterface service;

	@RequestMapping(path = "/product", method = RequestMethod.GET)
	public ModelAndView productCat() {
		return new ModelAndView("productCat");
	}

	@RequestMapping(path = "/display/{id}", method = RequestMethod.GET)
	public ModelAndView displayContainer(@PathVariable String id, HttpServletRequest request) {
		// TODO: Pull Container from database or session
		Container display = new Container();
		
		if((display = (Container) request.getSession().getAttribute("token")) == null) {
			System.out.println("Creating a new container...");
			display = new Container();
			request.getSession().setAttribute("token", display);
			System.out.println("Done");
		}

		HashMap<String, String> obj = display.toHash();
		System.out.println(obj.get("name"));
		return new ModelAndView("monitor", "cont", obj);
	}

	public void setContainerService(ContainerServiceInterface service) {
		this.service = service;
	}
}
