package com.gcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.exception.ImageNotFoundException;
import com.gcu.model.Container;
import com.gcu.model.Image;
import com.gcu.service.ImageServiceInterface;

@Controller
@RequestMapping("/image")
public class ImageController
{
	@Autowired
	private ImageServiceInterface imageService;
	
	@GetMapping("/catalog")
	public ModelAndView imageCatalog()
	{
		try
		{
			List<Image> images = imageService.findAllImages();
			
			ModelAndView mv = new ModelAndView("productCat");
			mv.addObject("images", images);
			mv.addObject("container", new Container());
			return mv;
		}
		catch(ImageNotFoundException e)
		{
			ModelAndView mv = new ModelAndView("productCat");
			mv.addObject("message", "No Docker Images are currently available");
			return mv;
		}
	}
	
}
