package com.gcu.model;

public class UpdateModel {
	private float storage;
	private float cpu;
	private float ram;

	public UpdateModel() {
		storage = 5;
		cpu = 5;
		ram = 5;
	}
	public UpdateModel(float storage, float cpu, float ram) {
		this.storage = storage;
		this.cpu = cpu;
		this.ram = ram;
	}

	public float getStorage() {
		return storage;
	}

	public void setStorage(float storage) {
		this.storage = storage;
	}

	public float getCpu() {
		return cpu;
	}

	public void setCpu(float cpu) {
		this.cpu = cpu;
	}

	public float getRam() {
		return ram;
	}

	public void setRam(float ram) {
		this.ram = ram;
	}

}
