package com.gcu.model;

import java.util.HashMap;

/**
 * Stores the name and limit of each 
 * @author Mark
 *
 */
public class Setting {
	private String name;
	private Limit limits;

	public Setting() {
		name = "";
		limits = new Limit();
	}

	public Setting(String name, Limit limit) {
		this.name = name;
		this.limits = limit;
	}

	public Setting(Setting copy) {
		this.name = copy.name;
		this.limits = copy.limits;
	}
}
