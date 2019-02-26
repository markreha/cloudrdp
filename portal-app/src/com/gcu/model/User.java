package com.gcu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class User 
{
	private String username;
	private String password;

	public User() 
	{
		username = "";
		password = "";
	}

	public User(String username, String password) {
		super();
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

	public static User getSqlRowSet(SqlRowSet srs)
	{
		return new User(
				srs.getString("USERNAME"),
				srs.getString("PASSWORD")
				);
	}
	
	public static String getSqlParams()
	{
		return 	  "USERNAME, "
				+ "PASSWORD";
	}
	
	public static String getSqlValues(User user)
	{
		return  "'" + user.getUsername() + "', " +
				"'" + user.getPassword();
	}
	
	public static PreparedStatement prepareStatement(Connection connection, String query, User user) throws SQLException
	{	
		PreparedStatement ps = connection.prepareStatement(query, new String[] { "ID" } );
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		return ps;
	}
	
}
