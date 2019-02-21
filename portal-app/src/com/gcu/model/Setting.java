package com.gcu.model;

/**
 * Stores the name and limit of each
 * 
 * @author Mark
 *
 */
public class Setting {
	private String name;
	private String version;
	private float lower;
	private float preset;
	private float upper;
	private float current;

	public Setting() {
		name = "a";
		version = "1.0.a";
		lower = 0;
		preset = 5;
		upper = 10;
		setCurrent(preset);
	}

	public Setting(String name, String version, float lower, float preset, float upper) {
		this.name = name;
		this.version = version;
		this.lower = lower;
		this.preset = preset;
		this.upper = upper;
		setCurrent(preset);
	}

	public Setting(Setting copy) {
		this.name = copy.name;
		this.lower = copy.lower;
		this.preset = copy.preset;
		this.upper = copy.upper;
		setCurrent(copy.preset);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public float getLower() {
		return lower;
	}

	public void setLower(float lower) {
		this.lower = lower;
	}

	public float getPreset() {
		return preset;
	}

	public void setPreset(float preset) {
		this.preset = preset;
	}

	public float getUpper() {
		return upper;
	}

	public void setUpper(float upper) {
		this.upper = upper;
	}

	public float getCurrent() {
		return current;
	}

	public void setCurrent(float current) {
		this.current = current;
	}

	public String getName() {
		return name;
	}
}
