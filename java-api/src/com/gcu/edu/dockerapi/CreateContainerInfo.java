package com.gcu.edu.dockerapi;

/**
 * Object Model class with all the parameters used to create a Container.
 * 
 * @author markreha
 *
 */
public class CreateContainerInfo
{
	private String image;		// The Image Name
	private String name;		// The Name of the Container
	private String portSpec;	// The formatted port specification (ContainerPort:HostPort)
	
	/**
	 * Non Default Constructor to specify a Create Container creation information.
	 * 
	 * @param image The Image Name.
	 * @param name The Name of the Container.
	 * @param portSpec The formatted port specification (ContainerPort:HostPort).
	 */
	public CreateContainerInfo(String image, String name, String portSpec)
	{
		super();
		this.image = image;
		this.name = name;
		this.portSpec = portSpec;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPortSpec()
	{
		return portSpec;
	}

	public void setPortSpec(String portSpec)
	{
		this.portSpec = portSpec;
	}
}
