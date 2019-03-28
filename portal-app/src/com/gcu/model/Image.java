package com.gcu.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Image
{
	protected int imageId;
	private String instance;
	private String version;
	private String tier;
	private float cpu;
	private BigDecimal ram;
	private int storage;
	
	public Image() {}
	
	public Image(int imageId, String instance, String version, String tier, float cpu, BigDecimal ram, int storage)
	{
		this.imageId = imageId;
		this.instance = instance;
		this.version = version;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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
		return "Image [instance=" + instance + ", version=" + version + ", tier=" + tier + ", cpu=" + cpu + ", ram=" + ram
				+ ", storage=" + storage + "]";
	}

	/** ======== Data Service Utilities ========== **/
	
	public static String getSqlInsertQuery() 
	{
		String query = "(i_ID, "
				+ "i_INSTANCE, "
				+ "i_VERSION, "
				+ "i_TIER, "
				+ "i_CPU, "
				+ "i_RAM, "
				+ "i_STORAGE) VALUES (";
		
		for(int i = 0; i < Image.class.getDeclaredFields().length; i++)
		{
			query = (i < Image.class.getDeclaredFields().length-1) ? "?," : "?)";
		}
		
		return query;
	}
	
	public static String getSqlUpdateQuery(Image image)
	{
		return 
			  "i_ID = ?, "
			+ "i_INSTANCE = ?, "
			+ "i_VERSION = ?, "
			+ "i_TIER = ?, "
			+ "i_CPU = ?, "
			+ "i_RAM = ?, "
			+ "i_STORAGE = ?";
	}

	public static Image getSqlRowSet(SqlRowSet srs)
	{
		return new Image(
				srs.getInt("i_ID"),
				srs.getString("i_INSTANCE"),
				srs.getString("i_VERSION"),
				srs.getString("i_TIER"),
				srs.getFloat("i_CPU"),
				srs.getBigDecimal("i_RAM"),
				srs.getInt("i_STORAGE")
				);
	}
	
	public static Image getResultSet(ResultSet rs) throws SQLException
	{
		return new Image(
				rs.getInt("i_ID"),
				rs.getString("i_INSTANCE"),
				rs.getString("i_VERSION"),
				rs.getString("i_TIER"),
				rs.getFloat("i_CPU"),
				rs.getBigDecimal("i_RAM"),
				rs.getInt("i_STORAGE")
				);
	}
	
	public void setImageResultSet(ResultSet rs) throws SQLException
	{
		this.imageId = rs.getInt("images.i_ID");
		this.instance = rs.getString("images.i_INSTANCE");
		this.version = rs.getString("images.i_VERSION");
		this.tier = rs.getString("images.i_TIER");
		this.cpu = rs.getFloat("images.i_CPU");
		this.ram = rs.getBigDecimal("images.i_RAM");
		this.storage = rs.getInt("images.i_STORAGE");
	}
}
