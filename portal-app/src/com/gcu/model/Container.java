package com.gcu.model;

public class Container {
	private String name;
	private Setting setting;
	private boolean code;

	public Container() {
		name = "Empty";
	}

	public Container(String name, Setting setting, boolean code) {
		this.name = name;
		this.setting = setting;
		this.code = code;
	}
	public Container(Container copy) {
		this.name = copy.name;
		this.setting = copy.setting;
		this.code = copy.code;
	}

	public boolean isCode() {
		return code;
	}

	public void setCode(boolean code) {
		this.code = code;
	}

}
