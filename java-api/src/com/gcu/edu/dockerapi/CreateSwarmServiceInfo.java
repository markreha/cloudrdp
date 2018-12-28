package com.gcu.edu.dockerapi;

/**
 * Object Model class with all the parameters used to create a Swarm Service.
 * 
 * @author markreha
 *
 */
public class CreateSwarmServiceInfo
{
	private String image;			// The Image Name
	private String name;			// The Swarm Service Name
	private float cpus;				// The CPU's in a percent, the Docker API requires a CPU quota in units of 10 to the 9th CPUs, default is 500000, so take the desired CPU's and divide by / 1000000000
	private int memMBytes;			// The Memory in Mbytes, the Docker API requires bytes so multiply this by 1000000
	private int replicas;			// The number of replicas to create in the Swarm
	private int targetPort;			// The Target Port inside the Container
	private int publishPort;		// The Published Port outside the Container
	
	/**
	 * Non Default Constructor to specify a Swarm Service creation information.
	 * 
	 * @param image The Image Name.
	 * @param name The Swarm Service Name.
	 * @param cpus The CPU's in a percent, the Docker API requires a CPU quota in units of 10 to the 9th CPUs, default is 500000, so take the desired CPU's and divide by / 1000000000.
	 * @param memMBytes The Memory in Mbytes, the Docker API requires bytes so multiply this by 1000000.
	 * @param replicas The number of replicas to create in the Swarm.
	 * @param targetPort The Target Port inside the Container.
	 * @param publishPort The Published Port outside the Container.
	 */
	public CreateSwarmServiceInfo(String image, String name, float cpus, int memMBytes, int replicas, int targetPort, int publishPort)
	{
		super();
		this.image = image;
		this.name = name;
		this.cpus = cpus;
		this.memMBytes = memMBytes;
		this.replicas = replicas;
		this.targetPort = targetPort;
		this.publishPort = publishPort;
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

	public float getCpus()
	{
		return cpus;
	}

	public void setCpus(float cpus)
	{
		this.cpus = cpus;
	}

	public int getMemMBytes()
	{
		return memMBytes;
	}

	public void setMemMBytes(int memMBytes)
	{
		this.memMBytes = memMBytes;
	}

	public int getReplicas()
	{
		return replicas;
	}

	public void setReplicas(int replicas)
	{
		this.replicas = replicas;
	}

	public int getTargetPort()
	{
		return targetPort;
	}

	public void setTargetPort(int targetPort)
	{
		this.targetPort = targetPort;
	}

	public int getPublishPort()
	{
		return publishPort;
	}

	public void setPublishPort(int publishPort)
	{
		this.publishPort = publishPort;
	}
	
}
