package com.gcu.edu.test;

import com.gcu.edu.dockerapi.DockerApi;
import com.gcu.edu.dockerapi.CreateContainerInfo;
import com.gcu.edu.dockerapi.CreateSwarmServiceInfo;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Service;
import com.github.dockerjava.api.model.SwarmNode;

import java.util.List;
import java.util.Scanner;

/**
 * A Test Client for the GCU Docker API class.
 * 
 * @author markreha
 *
 */
public class Play
{
//	static String piAddress = "tcp://172.24.19.223:2375";			// IP Address to the Docker Engine (running on the Raspberry Pi)
	static String piAddress = "tcp://10.0.1.153:2375";				// IP Address to the Docker Engine (running on the Raspberry Pi)
	static String registryUrl = "https://hub.docker.com";			// Use the Docker Hub Registry
	static String registryEmail = "mark.reha@gcu.edu";				// Docker Registry User Email Address
	static String registryUsername = "mark.reha@gcu.edu";			// Docker Registry Username
	static String registryPassword = "Starman1";					// Docker Registry Password
	static DockerApi api = null;									// Instance of the GCU Docker API Wrapper class
	static Scanner reader = new Scanner(System.in);					// Input Scanner used for the Menu

	/**
	 * Main Method that displays a Test Menu and executes the desired Docker API command.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Initialize and wait for commands
		System.out.println("Docker Client Test v1");
		System.out.println("=======> Intitializing.........");
		api = new DockerApi(piAddress, registryUrl, registryEmail, registryUsername, registryPassword);
		int cmd = -1;
		do
		{
			// Display the Menu
			System.out.println("");
			System.out.println("");
			System.out.println("===================================");
			System.out.println("===== Test Docker Client Menu =====");
			System.out.println("===================================");
			System.out.println("");
			System.out.println("      0 to Quit");
			System.out.println("      1 to List Images");
			System.out.println("      2 to List Containers");
			System.out.println("      3 to List Swarm Services");
			System.out.println("      4 to List Swarm Nodes");
			System.out.println("      5 to Create and run Nginx in a Swarm");
			System.out.println("      6 to Create and run a Tomcat 8.5 Alpine Container");
			System.out.println("      7 to Remove a Container");
			System.out.println("      8 to Remove a Swarm Service");
			System.out.println("      9 to Get a Containers Log file");
			System.out.print("  Enter a Command: ");
			
			// Get Menu Option from the user and run the specified command
			cmd = reader.nextInt();
			switch (cmd)
			{
				case 1:
					listAllImages();
					break;
				case 2:
					listAllContainers();
					break;
				case 3:
					listAllSwarmServices();
					break;
				case 4:
					listAllSwarmNodes();
					break;
				case 5:
					createNginxSwarm();
					break;
				case 6:
					createTomcatcatContainer();
					break;
				case 7:
					removeContainer();
					break;
				case 8:
					removeSwarmService();
					break;
				case 9:
					getContainerLogs();
					break;
				default:
					System.out.println("=======> Invalid command");
					break;
			}
		}while (cmd != 0);

		// Cleanup and exit
		reader.close();
		System.out.println("=======> Done");
	}
	
	// ***** Private Helper Functions *****

	/**
	 * List all the Docker Images.
	 * 
	 * @return True if successful else False if NO Images
	 */
	private static boolean listAllImages()
	{
		// Get all the Images Info and display
		List<Image> images = api.getAllImagessInfo(false);
		if (images.size() == 0)
		{
			System.out.println("=======> No Imges available");
			return false;
		}
		images.forEach(image -> System.out.println(String.format("=======> Image with an ID of %s with a Name of %s and size %.1fMb",	image.getId(), image.getRepoTags()[0], image.getSize().floatValue() / 1000000f)));
		return true;
	}

	/**
	 * List all the Docker Containers.
	 * 
	 * @return True if successful else False if NO Containers
	 */
	private static boolean listAllContainers()
	{
		// Get all the Containers Info and display
		List<Container> containers = api.getAllContainersInfo(false);
		if (containers.size() == 0)
		{
			System.out.println("=======> No Containers available");
			return false;
		}
		containers.forEach(container -> System.out.println(String.format("=======> Container with an ID of %s with an Image of %s is %s ago",	container.getId(), container.getImage(), container.getStatus())));
		return true;
	}
	
	/**
	 * List all the Docker Swarm Services.
	 * 
	 * @return True if successful else False if NO Services
	 */
	private static boolean listAllSwarmServices()
	{
		// Get all the Swarm Services and display
		List<Service> servicesInfo = api.getAllSwarmServicesInfo();
		if (servicesInfo.size() == 0)
		{
			System.out.println("=======> No Swarm Services available");
			return false;
		}
		servicesInfo.forEach(serviceInfo -> System.out.println(String.format("=======> Service with a ID of %s\n"
																		   + "         and Created At %s\n"
																		   + "         with Name of %s\n"
																		   + "         with Image of %s\n" 
																		   + "         with Command of %s\n"
																		   + "         with CPU of %.2f\n" 
																		   + "         with Memory of %dMb\n"
																		   + "         with Replicas of %s\n"
																		   + "         with Mode of %s\n"
																		   + "         with Ports of %s=>%s\n",
																						serviceInfo.getId(), 
																						serviceInfo.getCreatedAt(), 
																						serviceInfo.getSpec().getName(),
																						serviceInfo.getSpec().getTaskTemplate().getContainerSpec().getImage(),
																						serviceInfo.getSpec().getTaskTemplate().getContainerSpec().getCommand(),
																						serviceInfo.getSpec().getTaskTemplate().getResources().getLimits().getNanoCPUs().floatValue() / 1000000000f,
																						serviceInfo.getSpec().getTaskTemplate().getResources().getLimits().getMemoryBytes() / 1000000,
																						serviceInfo.getSpec().getMode().getReplicated().getReplicas(),
																						serviceInfo.getSpec().getEndpointSpec().getPorts().get(0).getPublishMode(),
																						serviceInfo.getSpec().getEndpointSpec().getPorts().get(0).getTargetPort(),
																						serviceInfo.getSpec().getEndpointSpec().getPorts().get(0).getPublishedPort() )));
		return true;
	}
	
	/**
	 * List all the Docker Swarm Nodes.
	 * 
	 */
	private static void listAllSwarmNodes()
	{
		// Get all the Swarm Nodes and display
		List<SwarmNode> nodesInfo = api.getAllSwarmNodesInfo();
		if (nodesInfo.size() == 0)
		{
			System.out.println("=======> No Swarm Nodes available");
			return;
		}
		nodesInfo.forEach(nodeInfo -> System.out.println("=======> Node with an ID: " + nodeInfo.getId() + " with a State of " + nodeInfo.getStatus().getState()));
	}
	
	/**
	 * Create a Docker Swarm Service running the Nginx Image.
	 * 
	 */
	private static void createNginxSwarm()
	{
		// Create a Nginx Service in a Swarm
		CreateSwarmServiceInfo info = new CreateSwarmServiceInfo("nginx", "webapp1", .5f, 250, 1, 80, 1000);
		boolean ok = api.createSwarmService(info);
		if(ok)
			System.out.println("=======> Nginx Swarm created successfully.");
		else
			System.out.println("=======> Nginx Swarm creation failed.");
	}
	
	/**
	 * Create a Docker Container running the Tomcat 8.5 Alpine Image.
	 * 
	 */
	private static void createTomcatcatContainer()
	{
		// Create a Tomcat Container
		CreateContainerInfo info = new CreateContainerInfo("tomcat:8.5-alpine", "tomcat8.5", "8080:8080");
		boolean ok = api.createContainer(info);
		if(ok)
			System.out.println("=======> Tomcat Container created successfully.");
		else
			System.out.println("=======> Tomcat Container creation failed.");
	}
	
	/**
	 * Prompt user for a Container ID and then remove the Docker Container.
	 * 
	 */
	private static void removeContainer()
	{
		// Display all Containers and prompt the user for which one to remove
		if(!listAllContainers())
			return;
		System.out.print("Enter Container ID to remove: ");
		String id = reader.next();

		// Destroy the Container
		if(!api.removeContainer(id))
			System.out.print("=======> Container ID not found");
	}

	/**
	 * Prompt user for a Swarm Service Name and then remove the Docker Swarm Service.
	 * 
	 */
	private static void removeSwarmService()
	{
		// Display all Swarm Services and prompt the user for which one to remove
		if(!listAllSwarmServices())
			return;
		System.out.print("Enter Swarm Name to remove: ");
		String name = reader.next();

		// Destroy the Swarm Service
		if(!api.removeSwarmService(name))
			System.out.print("=======> Swarm Name not found");
	}

	/**
	 * Display the last 1000 lines of a Containers Logs.
	 * 
	 */
	private static void getContainerLogs()
	{
		// Display all Containers and prompt the user for which one to get logs for
		List<Container> containers = api.getAllContainersInfo(true);
		if (containers.size() == 0)
		{
			System.out.println("=======> No Containers available");
			return;
		}
		containers.forEach(container -> System.out.println(String.format("=======> Container with an ID of %s with an Image of %s is %s ago",	container.getId(), container.getImage(), container.getStatus())));
		System.out.print("Enter Container ID to get logs for: ");
		String id = reader.next();
		
		// Get Containers logs (last 1000 lines) and display them
		List<String>logs = api.getContainerLogs(id, 1000);
		logs.forEach(line -> System.out.println(line));
	}
}
