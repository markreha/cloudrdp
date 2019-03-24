package com.gcu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.ImageDAOInterface;
import com.gcu.exception.ImageNotFoundException;
import com.gcu.model.Image;

public class ImageService implements ImageServiceInterface
{
	/**
	 * Dependency Injected DAO Service
	 */
	@Autowired
	private ImageDAOInterface imageDAO;
	
	/**
	 *  Assemble all the images stores in the database into a list
	 *  
	 *  @return List<Image> - list of docker images
	 *  @throws ImageNotFoundException - No images were found in the database
	 */
	@Override
	public List<Image> findAllImages() throws ImageNotFoundException 
	{
		// Call the Image DAO and get all the images
		 List<Image> images = imageDAO.findAll();
		 
		 // If the list is empty, throw exception
		 if(images.isEmpty())
		 {
		 	throw new ImageNotFoundException();
		 }
		 
		 // return the list
		 return images;
	}

	@Override
	public Image findImageById(int imageId) throws ImageNotFoundException 
	{
		Image image = imageDAO.find(imageId);
		
		if(image == null)
		{
			throw new ImageNotFoundException();
		}
		
		return image;
	}
}
