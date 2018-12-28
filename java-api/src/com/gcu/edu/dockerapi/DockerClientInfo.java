package com.gcu.edu.dockerapi;

/**
 * Object Model class with all the parameters used to connect to Docker Engine.
 * 
 * @author markreha
 *
 */
public class DockerClientInfo
{
	private String piAddress;			// The IP Address to the Docker Engine
	private String registryUrl;			// The Docker Registry URL
	private String registryEmail;		// The Docker Registry User Email Address
	private String registryUsername;	// The Docker Registry Login Username
	private String registryPassword;	// The Docker Registry Login Password
	
	/**
	 * Non Default Constructor to specify Docker Engine connection information.
	 * 
	 * @param piAddress The IP Address to the Docker Engine.
	 * @param registryUrl The Docker Registry URL.
	 * @param registryEmail The Docker Registry User Email Address.
	 * @param registryUsername The Docker Registry Login Username.
	 * @param registryPassword The Docker Registry Login Password.
	 */
	public DockerClientInfo(String piAddress, String registryUrl, String registryEmail, String registryUsername, String registryPassword)
	{
		super();
		this.piAddress = piAddress;
		this.registryUrl = registryUrl;
		this.registryEmail = registryEmail;
		this.registryUsername = registryUsername;
		this.registryPassword = registryPassword;
	}

	public String getPiAddress()
	{
		return piAddress;
	}

	public void setPiAddress(String piAddress)
	{
		this.piAddress = piAddress;
	}

	public String getRegistryUrl()
	{
		return registryUrl;
	}

	public void setRegistryUrl(String registryUrl)
	{
		this.registryUrl = registryUrl;
	}

	public String getRegistryEmail()
	{
		return registryEmail;
	}

	public void setRegistryEmail(String registryEmail)
	{
		this.registryEmail = registryEmail;
	}

	public String getRegistryUsername()
	{
		return registryUsername;
	}

	public void setRegistryUsername(String registryUsername)
	{
		this.registryUsername = registryUsername;
	}

	public String getRegistryPassword()
	{
		return registryPassword;
	}

	public void setRegistryPassword(String registryPassword)
	{
		this.registryPassword = registryPassword;
	}
	
}
