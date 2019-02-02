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

	public Limit() {
		lower = 0;
		preset = 5;
		upper = 10;
	}

	public Limit(float lower, float preset, float upper) {
		this.lower = lower;
		this.preset = preset;
		this.upper = upper;
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
}
