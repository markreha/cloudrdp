package com.gcu.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.model.Container;
import com.gcu.model.UpdateModel;
import com.gcu.service.ContainerServiceInterface;

@Controller
@RequestMapping("/config")
public class ConfigController 
{
	@SuppressWarnings("unused")
	@Autowired
	private ContainerServiceInterface service;

	@RequestMapping(path = "/config/{id}", method = RequestMethod.GET)
	public ModelAndView configure(HttpServletRequest request) {
		// TODO: Pull Container from database or session
		Container display = new Container();
		
		if((display = (Container) request.getSession().getAttribute("token")) == null) {
			System.out.println("Creating a new container...");
			request.getSession().setAttribute("token", display);
			System.out.println("Done");
		}

		HashMap<String, String> obj = display.toHash();
		System.out.println(obj);
		return new ModelAndView("configure", "cont", obj);
	}

	@RequestMapping(path = "/updateSetting", method = RequestMethod.POST)
	public ModelAndView updateSettings(@Valid @ModelAttribute("update") UpdateModel update, BindingResult result,
			HttpServletRequest request) {
		Container cont = (Container) request.getSession().getAttribute("token");
		cont.getSetting().get(0).setCurrent(update.getStorage());
		cont.getSetting().get(1).setCurrent(update.getCpu());
		cont.getSetting().get(2).setCurrent(update.getRam());
		
		// Update session
		request.getSession().setAttribute("token", cont);
		HashMap<String, String> obj = cont.toHash();
		System.out.println(obj);
		return new ModelAndView("monitor", "cont", obj);
	}
}
