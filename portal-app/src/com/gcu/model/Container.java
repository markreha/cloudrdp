package com.gcu.model;

public class Container {
	private String name;
	// List of products used. Tomcat, SQL database, ect.
	private String[] image;
	private Setting[] settings;
	private float storage;
	// Running or not.  False by default
	private boolean code;

	public Container() {
		name = "Empty";
		image = new String[1];
		image[0] = "none";
		settings = new Setting[3];
		settings[0] = new Setting();
		settings[1] = new Setting();
		settings[2] = new Setting();
		storage = 0;
		code = false;
	}

	public Container(String name, String[] image, Setting[] setting, float storage, boolean code) {
		this.name = name;
		this.image = new String[image.length];
		for(int i = 0; i < image.length; i++) {
			this.image[i] = image[i];
		}
		this.settings = new Setting[setting.length];
		for(int i = 0; i < setting.length; i++) {
			this.settings[i] = setting[i];
		}
		this.storage = storage;
		this.code = false;
	}
	public Container(Container copy) {
		this.name = copy.name;
		this.image = new String[copy.image.length];
		for(int i = 0; i < copy.image.length; i++) {
			this.image[i] = copy.image[i];
		}
		this.settings = new Setting[copy.settings.length];
		for(int i = 0; i < copy.settings.length; i++) {
			this.settings[i] = copy.settings[i];
		}
		this.storage = copy.storage;
		this.code = copy.code;
	}

	public boolean isCode() {
		return code;
	}

	public void setCode(boolean code) {
		this.code = code;
	}

	public float getStorage() {
		return storage;
	}

	public void setStorage(float storage) {
		this.storage = storage;
	}

}
