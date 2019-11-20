package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Container extends Image
{
	@NotNull
	@Size(min=0, max=32)
	private String name;
	
	@NotNull
	@Size(min=0, max=200)
	private String description;
	
	@NotNull
	@Size(min=0, max=32)
	private String dockerId;
	
	@NotNull
	@Size(min=0, max=32)
	private String username;
	
	public Container() {}
	
	public Container(String name, String description, String dockerId) 
	{
		this.name = name;
		this.description = description;
		this.dockerId = dockerId;
	}
	
	public Container(String name, String description, String dockerId, String username, int imageId) 
	{
		this.name = name;
		this.description = description;
		this.dockerId = dockerId;
		this.username = username;
		this.imageId = imageId;
	}
	
	public Container(String name, String description, int imageId)
	{
		this.name = name;
		this.description = description;
		this.imageId = imageId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDockerId() {
		return dockerId;
	}

	public void setDockerId(String dockerId) {
		this.dockerId = dockerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

}
