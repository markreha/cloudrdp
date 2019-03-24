package com.gcu.data;

import java.util.List;

import com.gcu.model.Image;

public interface ImageDAOInterface
{
	public List<Image> findAll();

	public Image find(int imageId);
}
