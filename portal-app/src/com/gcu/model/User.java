package com.gcu.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class User 
{
	@NotNull
	@Size(min=0, max=32)
	private String username;
	
	@NotNull
	@Size(min=0, max=32)
	private String password;

	public User() {}

	public User(String username, String password) 
	{
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	/** ======== Data Service Utilities ========== **/
	
	public static String getSqlInsertQuery() 
	{
		return "(u_NAME, u_PASSWORD) VALUES (?, ?)";
	}
	
	public static User getSqlRowSet(SqlRowSet srs)
	{
		return new User(
				srs.getString("u_NAME"),
				srs.getString("u_PASSWORD")
				);
	}

	public static User getResultSet(ResultSet rs) throws SQLException
	{
		return new User(
				rs.getString("u_NAME"),
				rs.getString("u_PASSWORD")
				);
	}
}
