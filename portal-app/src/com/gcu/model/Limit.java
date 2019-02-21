package com.gcu.model;

/**
 * A simple model to encapsulate the different limits of settings
 * 
 * @author Mark
 *
 */
public class Limit {
	private float lower;
	private float preset;
	private float upper;
	private float current;

	public Limit() {
		lower = 0;
		preset = 5;
		upper = 10;
		setCurrent(preset);
	}

	public Limit(float lower, float preset, float upper) {
		this.lower = lower;
		this.preset = preset;
		this.upper = upper;
		this.setCurrent(this.preset);
	}
	
	public Limit(float lower, float preset, float upper, float current) {
		this.lower = lower;
		this.preset = preset;
		this.upper = upper;
		this.current = current;
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
}
