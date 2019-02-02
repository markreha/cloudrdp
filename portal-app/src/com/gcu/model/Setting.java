package com.gcu.model;

import java.util.HashMap;

public class Setting {
	private HashMap<String, Limit> settings;

	public Setting() {
		settings = new HashMap<String, Limit>();
	}

	public Setting(String[] s, Limit[] l) {
		settings = new HashMap<String, Limit>();

		if (s.length != l.length) {
			// TODO: Error, settings and limit amount do not match
		}
		for (int i = 0; i < s.length; i++) {
			settings.put(s[i], l[i]);
		}
	}

	public Setting(HashMap<String, Limit> settings) {
		this.settings = settings;
	}

	public HashMap<String, Limit> getSettings() {
		return settings;
	}

	public void setSettings(HashMap<String, Limit> settings) {
		this.settings = settings;
	}

}
