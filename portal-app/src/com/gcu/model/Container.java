package com.gcu.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

public class Container {
	private int id;
	private String name;
	// List of products used. Tomcat, SQL database, ect.
	private List<String> image;
	private List<Setting> settings;
	private float storage;
	// Running or not. False by default
	private boolean code;

	public Container() {
		id = 0;
		name = "Empty";
		image = new ArrayList<String>();
		image.add("none");
		settings = new ArrayList<Setting>();
		settings.add(new Setting());
		settings.add(new Setting());
		settings.add(new Setting());
		storage = 0;
		code = false;
	}

	public Container(int id, String name, List<String> image, List<Setting> setting, float storage, boolean code) {
		this.id = id;
		this.name = name;
		this.image = new ArrayList<String>();
		for (int i = 0; i < image.size(); i++) {
			this.image.add(image.get(i));
		}
		this.settings = new ArrayList<Setting>();
		for (int i = 0; i < setting.size(); i++) {
			this.settings.add(setting.get(i));
		}
		this.storage = storage;
		this.code = false;
	}

	public Container(Container copy) {
		this.id = copy.id;
		this.name = copy.name;
		this.image = new ArrayList<String>();
		for (int i = 0; i < copy.image.size(); i++) {
			this.image.add(copy.image.get(i));
		}
		this.settings = new ArrayList<Setting>();
		for (int i = 0; i < copy.settings.size(); i++) {
			this.settings.add(copy.settings.get(i));
		}
		this.storage = copy.storage;
		this.code = copy.code;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<String> getImage() {
		return image;
	}

	public List<Setting> getSetting() {
		return settings;
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

	// Converts a Container to a HashMap
	public HashMap<String, String> toHash() {
		HashMap<String, String> obj = new HashMap<String, String>();
		obj.put("id", getId() + "");
		obj.put("name", getName());
		obj.put("imagelen", image.size() + "");
		for (int i = 0; i < image.size(); i++) {
			obj.put("image" + i, image.get(i));
		}
		obj.put("setlen", settings.size() + "");
		for (int i = 0; i < settings.size(); i++) {
			obj.put("setname" + i, settings.get(i).getName());
			obj.put("lower" + i, settings.get(i).getLower() + "");
			obj.put("preset" + i, settings.get(i).getPreset() + "");
			obj.put("upper" + i, settings.get(i).getUpper() + "");
			obj.put("current" + i, settings.get(i).getCurrent() + "");
		}
		obj.put("storage", getStorage() + "");
		obj.put("code", isCode() + "");
		return obj;
	}
}
