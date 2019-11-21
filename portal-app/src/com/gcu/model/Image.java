package com.gcu.model;

import java.math.BigDecimal;

public class Image
{
	protected int imageId;
	private String instance;
	private String name;
	private String version;
	private int port;
	private String tier;
	private float cpu;
	private BigDecimal ram;
	private int storage;
	
	public Image() {}
	
	public Image(int imageId, String instance, String name, String version, int port, String tier, 
			float cpu, BigDecimal ram, int storage) 
	{
		this.imageId = imageId;
		this.instance = instance;
		this.name = name;
		this.version = version;
		this.port = port;
		this.tier = tier;
		
		// Might have to constrain db defined decimal cap
		this.cpu = cpu;
		this.ram = ram;
		
		this.storage = storage;
	}

	
	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public float getCpu() {
		return cpu;
	}

	public void setCpu(float cpu) {
		this.cpu = cpu;
	}

	public BigDecimal getRam() {
		return ram;
	}

	public void setRam(BigDecimal ram) {
		this.ram = ram;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	@Override
	public String toString() {
		return "Image [instance=" + instance + ", name=" + name + ", version=" + version + ", port=" + port + 
				", tier=" + tier + ", cpu=" + cpu + ", ram=" + ram + ", storage=" + storage + "]";
	}

}
