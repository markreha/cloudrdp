package com.gcu.service;

import java.util.List;

import com.gcu.exception.ImageNotFoundException;
import com.gcu.model.Image;

public interface ImageServiceInterface 
{
	/**
	 *  Assemble all the images stores in the database into a list
	 *  
	 *  @return List<Image> - list of docker images
	 *  @throws ImageNotFoundException - No images were found in the database
	 */
	public List<Image> findAllImages() throws ImageNotFoundException;

	public Image findImageById(int imageId) throws ImageNotFoundException;
}
