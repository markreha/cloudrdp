package com.gcu.edu.dockerapi;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.CreateServiceResponse;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ContainerSpec;
import com.github.dockerjava.api.model.EndpointSpec;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.PortConfig;
import com.github.dockerjava.api.model.ResourceRequirements;
import com.github.dockerjava.api.model.ResourceSpecs;
import com.github.dockerjava.api.model.Service;
import com.github.dockerjava.api.model.ServiceModeConfig;
import com.github.dockerjava.api.model.ServiceReplicatedModeOptions;
import com.github.dockerjava.api.model.ServiceSpec;
import com.github.dockerjava.api.model.SwarmNode;
import com.github.dockerjava.api.model.TaskSpec;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.LogContainerResultCallback;

/**
 * Facade Class over the actual Docker Java API.
 *  
 * @author markreha
 *
 */
public class DockerApi
{
	private DockerClientInfo info;
	private DockerClient dockerClient;
	private Logger logger = LoggerFactory.getLogger(DockerApi.class);
	
	/**
	 * Non Default Constructor that makes a connection to the Docker Engine using the Docker Java API and provided connectivity parameters.
	 * 
	 * @param piAddress The IP Address to the Docker Engine.
	 * @param registryUrl The Docker Registry URL.
	 * @param registryEmail The Docker Registry User Email Address.
	 * @param registryUsername The Docker Registry Login Username.
	 * @param registryPassword The Docker Registry Login Password.
	 */
	public DockerApi(String piAddress, String registryUrl, String registryEmail, String registryUsername, String registryPassword)
	{
		// Initialize the Docker Api
		info = new DockerClientInfo(piAddress, registryUrl, registryEmail, registryUsername, registryPassword);
		initialize();
	}
	
	/**
	 * Return a List of all Docker Images.
	 * 
	 * @param showAll If true will return all Images else will return filtered list.
	 * @return List of Image (see JavaDoc at com.github.dockerjava.api.model.Image).
	 */
	public List<Image> getAllImagessInfo(boolean showAll)
	{
		List<Image> iamges = new ArrayList<Image>();
		
		// Get a list of all Docker Contains and display on the Console
		logger.debug("=======> Getting list of Docker Images....");
		iamges = dockerClient.listImagesCmd().withShowAll(showAll).exec();

		// Return Images
		return iamges;
	}

	/**
	 * Return a List of all Docker Containers.
	 * 
	 * @param showAll If true will return all Containers else will return filtered list.
	 * @return List of Container (see JavaDoc at com.github.dockerjava.api.model.Container).
	 */
	public List<Container> getAllContainersInfo(boolean showAll)
	{
		List<Container> containers = new ArrayList<Container>();
		
		// Get a list of all Docker Contains and display on the Console
		logger.debug("=======> Getting list of Docker Containers....");
		containers = dockerClient.listContainersCmd().withShowAll(showAll).exec();

		// Return Containers
		return containers;
	}

	/**
	 * Return a List of all Docker Swarm Services.
	 * 
	 * @return List of Service (see JavaDoc at com.github.dockerjava.api.model.Service).
	 */
	public List<Service> getAllSwarmServicesInfo()
	{
		List<Service> containersServiceList = new ArrayList<Service>();
		
		// Get a list of all Docker Swarm Services and display on the Console
		logger.debug("=======> Getting list of Docker Swarm Services....");
		containersServiceList = dockerClient.listServicesCmd().exec();

		// Return Swarm Services Info
		return containersServiceList;
	}

	/**
	 * Return a List of all Docker Swarm Nodes.
	 * 
	 * @return List of SwarmNode (see JavaDoc at com.github.dockerjava.api.model.SwarmNode).
	 */
	public List<SwarmNode> getAllSwarmNodesInfo()
	{
		List<SwarmNode> containersNodeList = new ArrayList<SwarmNode>();
		
		// Get a list of all Docker Swarm Nodes and display on the Console
		logger.debug("=======> Getting list of Docker Swarm Nodes....");
		containersNodeList = dockerClient.listSwarmNodesCmd().exec();

		// Return Swarm Nodes Info
		return containersNodeList;
	}
	
	/**
	 * Create a Docker Swarm Service.
	 * 
	 * @param info The Docker Swarm Service (see JavaDoc at com.gcu.edu.dockerapi.CreateSwarmServiceInfo).
	 * @return Service ID else returns null if Service was not created.
	 */
	public String createSwarmService(CreateSwarmServiceInfo info)
	{
		// Create a Nginx in a Swarm
		logger.debug("=======> Creating Service in a Swarm....");
		
		// Configure the Container Image
		ContainerSpec containerSpec = new ContainerSpec()
				.withImage(info.getImage());				
		// Configure the CPU's and Memory Limits
		ResourceSpecs limits = new ResourceSpecs()
				.withMemoryBytes(info.getMemMBytes() * 1000000)
				.withNanoCPUs((long)(info.getCpus() * 1000000000));
		ResourceRequirements requirements = new ResourceRequirements()
				.withLimits(limits);
		// Configure the number of Replicas
		ServiceReplicatedModeOptions replicated = new ServiceReplicatedModeOptions()
				.withReplicas(info.getReplicas());
		// Configure the Target Port and the Published Port
		List<PortConfig> ports = new ArrayList<PortConfig>();
		PortConfig portConfig = new PortConfig();
		portConfig.withTargetPort(info.getTargetPort());
		portConfig.withPublishedPort(info.getPublishPort());
		ports.add(portConfig);		

		// Set the Image, CPU's, Memory Limits, number of Replicas, and Published Port in the ServiceModeConfig, TaskSpec, and EndpointSpec
		ServiceModeConfig mode = new ServiceModeConfig()
				.withReplicated(replicated);
		TaskSpec taskTemplate = new TaskSpec()
				.withContainerSpec(containerSpec)
				.withResources(requirements);
		EndpointSpec endpointSpec = new EndpointSpec()
				.withPorts(ports);
		
		// Set the ServiceSpec with the Name, ServiceModeConfig, EndpointSpec, and the TaskSpec 
		ServiceSpec spec = new ServiceSpec()
				.withName(info.getName())
				.withMode(mode)
				.withEndpointSpec(endpointSpec)
				.withTaskTemplate(taskTemplate);
		
		// Create a Service in the Swarm
		CreateServiceResponse serviceReponse = dockerClient.createServiceCmd(spec).exec();
		logger.debug("=======> Completed with a Service ID of " + serviceReponse.getId());
		
		// Return Service ID
		return serviceReponse.getId();
	}

	/**
	 * Create a Docker Container.
	 * 
	 * @param info The Docker Container (see JavaDoc at com.gcu.edu.dockerapi.CreateContainerInfo).
	 * @return Container ID else returns null if Container was not created.
	 */
	public String createContainer(CreateContainerInfo info)
	{
		// Create a Container with desired Image, Name, and Port Spec
		logger.debug("=======> Creating Container....");
		try
		{
			CreateContainerResponse containerResponse = dockerClient.createContainerCmd(info.getImage()).withName(info.getName()).withPortSpecs(info.getPortSpec()).exec();
			logger.debug("=======> Completed with a Container ID of " + containerResponse.getId());
	
			// Start the Container
			logger.debug("=======> Starting Container....");
			dockerClient.startContainerCmd(containerResponse.getId()).exec();
			logger.debug("=======> Completed");
			
			// Return Container ID
			return containerResponse.getId();
		}
		catch(NotFoundException e)
		{
			logger.debug("=======> Container not found");
		}
		
		// Return null for failure
		return null;
	}

	/**
	 * Remover a Docker Container.
	 * 
	 * @param containerId The ID of the Container to remove.
	 * @return True if the Container was removed else returns false if Container was not removed or the ID was invalid.
	 */
	public boolean removeContainer(String containerId)
	{
		// Stop Container if it is running
		try
		{
			if (dockerClient.inspectContainerCmd(containerId).exec().getState().getRunning())
			{
				logger.debug("=======> Stopping Container....");
				dockerClient.stopContainerCmd(containerId).exec();
				logger.debug("=======> Completed");
			}
		}
		catch(NotFoundException e)
		{
			return false;
		}

		// Destroy the Container
		logger.debug("=======> Removing Container....");
		try
		{
			dockerClient.removeContainerCmd(containerId).exec();
		}
		catch(NotFoundException e)
		{
			return false;
		}
		logger.debug("=======> Completed");
		return true;
	}
	
	/**
	 * Remover a Docker Swarm Service.
	 * 
	 * @param serviceName The Name of the Swarm Service to remove.
	 * @return True if the Swarm Service was removed else returns false if Swarm Service was not removed or the Name was invalid.
	 */
	public boolean removeSwarmService(String serviceName)
	{
		// Destroy the Swarm Service
		logger.debug("=======> Removing Swarm Service....");
		try
		{
			dockerClient.removeServiceCmd(serviceName).exec();
		}
		catch(NotFoundException e)
		{
			return false;
		}
		logger.debug("=======> Completed");
		return true;
	}
	
	/**
	 * Return a Containers Logs.
	 * 
	 * @param containerId Container ID to get logs for.
	 * @param lastNumber The last number of lines in the log file to retrieve.
	 * @return List of String that contains the last number of lines from the log file.
	 */
	public List<String> getContainerLogs(String containerId, int lastNumber)
	{
		// Get Containers logs (last N lines) and display them
		return getDockerLogs(containerId, lastNumber);
	}

	// ***** Private Helper Functions *****

	/**
	 * Private helper function to initialize and connect to the Docker Engine.
	 * 
	 */
	private void initialize()
	{
		// Create a Docker Client to run all the Docker Commands
		logger.debug("=======> Connecting to Docker with Docker Client.........");
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().withRegistryUrl(info.getRegistryUrl()).withRegistryEmail(info.getRegistryEmail())
				.withRegistryUsername(info.getRegistryUsername()).withRegistryPassword(info.getRegistryPassword()).withDockerCertPath("/home/pi/.docker/certs").withDockerConfig("/home/pi/.docker/")
				.withDockerTlsVerify(false).withDockerHost(info.getPiAddress()).build();
		logger.debug("=======> Getting Docker Client....");
		dockerClient = DockerClientBuilder.getInstance(config).build();
		Info info = dockerClient.infoCmd().exec();
		logger.debug("=======> Docker Client Info is:" + info);
	}
	
	/**
	 * Private helper function to actually get log statements from a Container.
	 * 
	 * @param containerId Container ID to get logs for.
	 * @param lastNumber The last number of lines in the log file to retrieve.
	 * @return List of String that contains the last number of lines from the log file.
	 */
	private List<String> getDockerLogs(String containerId, int lastNumber)
	{
		List<String> logs = new ArrayList<>();

		// Get Containers Logs (last N lines)
		LogContainerCmd logContainerCmd = dockerClient.logContainerCmd(containerId);
		logContainerCmd.withStdOut(true).withStdErr(true);
//		logContainerCmd.withSince( lastLogTime ); // UNIX timestamp (integer) to filter logs. Specifying a timestamp will only output log-entries since that timestamp.
		logContainerCmd.withTail(lastNumber);
		logContainerCmd.withTimestamps(true);
		try
		{
			logContainerCmd.exec(new LogContainerResultCallback()
			{
				@Override
				public void onNext(Frame item)
				{
					logs.add(item.toString());
				}
			}).awaitCompletion();
		} 
		catch (InterruptedException e)
		{
			logger.error("Interrupted Exception!" + e.getMessage());
		}
//		lastLogTime = (int) (System.currentTimeMillis() / 1000) + 5; // assumes at least a 5 second wait between calls to getDockerLogs

		// Return log lines
		return logs;
	}
}
