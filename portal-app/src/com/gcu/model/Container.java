package com.gcu.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Container extends Image
{
	@NotNull
	@Size(min=0, max=32)
	private String name;
	
	@NotNull
	@Size(min=0, max=200)
	private String description;
	
	@NotNull
	@Size(min=0, max=32)
	private String dockerId;
	
	@NotNull
	@Size(min=0, max=32)
	private String username;
	
	public Container() {}
	
	public Container(String name, String description, String dockerId) 
	{
		this.name = name;
		this.description = description;
		this.dockerId = dockerId;
	}
	
	public Container(String name, String description, String dockerId, String username, int imageId) 
	{
		this.name = name;
		this.description = description;
		this.dockerId = dockerId;
		this.username = username;
		this.imageId = imageId;
	}
	
	public Container(String name, String description, int imageId)
	{
		this.name = name;
		this.description = description;
		this.imageId = imageId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDockerId() {
		return dockerId;
	}

	public void setDockerId(String dockerId) {
		this.dockerId = dockerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	/** ======== Data Service Utilities ========== 
	 * @throws SQLException **/
	
//	public static PreparedStatementCreator preparedStatement(final String query, final Container container) throws SQLException
//	{
//		return new PreparedStatementCreator() 
//		{ 
//			@Override
//			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
//			{
//				PreparedStatement ps = connection.prepareStatement(query, new String[] { "ID" } );
//
//				ps.setString(1, container.getName());
//				ps.setString(2, container.getDockerId());
//				ps.setString(3, container.getDescription());
//				ps.setString(4, container.getUsername());
//				
//				return ps; 
//			}
//		};
//	}
	
	public static PreparedStatement preparedStatement(PreparedStatement ps, Container container) throws SQLException
	{
		ps.setString(1, container.getName());
		ps.setString(2, container.getDescription());
		ps.setString(3, container.getDockerId());
		ps.setString(4, container.getUsername());
		ps.setInt(5, container.getImageId());
		return ps;
	}
	
	public static String getSqlInsertQuery() 
	{
		String query = "(c_NAME, "
				+ "c_DESCRIPTION, "
				+ "c_DOCKERID, "
				+ "u_NAME, "
				+ "i_ID) VALUES "
				+ "(?,?,?,?,?)";
		
		return query;
	}
	
	public static String getSqlUpdateQuery(Container container)
	{
		return 
			  "c_NAME = ?, "
			+ "c_DESCRIPTION = ?, "
			+ "c_DOCKERID = ?, "
			+ "u_NAME = ?, "
			+ "i_ID = ?";
	}

	public static Container getSqlRowSet(SqlRowSet srs)
	{
		return new Container(
				srs.getString("c_NAME"),
				srs.getString("c_DESCRIPTION"),
				srs.getString("c_DOCKERID"),
				srs.getString("u_NAME"),
				srs.getInt("i_ID")
				);
	}
	
	public static Container getResultSet(ResultSet rs) throws SQLException
	{
		return new Container(
				rs.getString("c_NAME"),
				rs.getString("c_DESCRIPTION"),
				rs.getString("c_DOCKERID"),
				rs.getString("u_NAME"),
				rs.getInt("i_ID")
				);
	}
	
	public void setContainerResultSet(ResultSet rs) throws SQLException
	{
		this.name = rs.getString("containers.c_NAME");
		this.description = rs.getString("containers.c_DESCRIPTION");
		this.dockerId = rs.getString("containers.c_DOCKERID");
		this.username = rs.getString("containers.u_NAME");
		this.imageId = rs.getInt("containers.i_ID");
	}
}
