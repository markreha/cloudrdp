package com.gcu.utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.model.Container;
import com.gcu.model.Image;
import com.gcu.model.User;

public class SqlFactory 
{
	/**
	 * Create a SQL Insert Query that corresponds with the object that was passed to the method.
	 * 
	 * @param obj Object that requires an insert query
	 * @return Returns the SQL query in the form of a String
	 * @throws SQLException
	 */
	public static String getSqlInsertQuery(Object obj) throws SQLException
	{
		String sql = "";
		
		// If object is of instance Container
		if(obj.getClass().isInstance(Container.class))
		{
			sql = "INSERT INTO `containers` (c_NAME, c_DESCRIPTION, c_DOCKERID, u_NAME, i_ID) VALUES "
					+ "(?, ?, ?, ?, ?)";
		}
		// If object is of instance Image
		else if (obj.getClass().isInstance(Image.class))
		{
			sql = "INSERT INTO `images` "
					+ "(i_ID, i_INSTANCE, i_VERSION, i_TIER, i_CPU, i_RAM, i_STORAGE) VALUES "
					+ "(NULL, ?, ?, ?, ?, ?, ?)";
		}
		// If object is instance of User
		else if (obj.getClass().isInstance(User.class))
		{
			sql = "INSERT INTO `users` (u_NAME, u_PASSWORD) VALUES (?, ?)";
		}
		
		return sql;
	}
	
	/**
	 * Create a SQL Update Query that corresponds with the object that was passed to the method.
	 * 
	 * @param obj Object that requires an update query
	 * @return Returns the SQL query in the form of a String
	 * @throws SQLException
	 */
	public static String getSqlUpdateQuery(Object obj) throws SQLException
	{
		String sql = "";
		
		// If object is of instance Container
		if(obj.getClass().isInstance(Container.class))
		{
			sql = "UPDATE `containers` SET c_DOCKERID = ? WHERE  u_NAME = ? AND i_ID = ?";
		}
		// If object is of instance Image
		else if (obj.getClass().isInstance(Image.class))
		{
			sql = "UPDATE `images` SET i_INSTANCE = ?, i_VERSION = ?, i_TIER = ?, i_CPU = ?, i_RAM = ?, i_STORAGE = ? "
					+ "WHERE i_ID = ?";
		}
		// If object is instance of User
		else if (obj.getClass().isInstance(User.class))
		{
			sql = "UPDATE `users` SET u_NAME = ?, u_PASSWORD = ? WHERE u_ID = ?";
		}
		
		return sql;
	}
	
	public static String getSqlDeleteQuery(Object obj) throws SQLException
	{
		String sql = "";
		
		return sql;
	}
	
	/**
	 * Gets an object from a SqlRowSet. This object corresponds to the object that was passed to the method
	 * 
	 * @param srs SqlRowSet that was passed to the method
	 * @param obj Object that was passed to the method
	 * @return Returns the object that was found in the SqlRowSet
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSqlRowSet(SqlRowSet srs, Object obj) throws SQLException
	{
		if (obj.getClass().isInstance(Container.class))
		{
			return (T) new Container(
					srs.getString("c_NAME"),
					srs.getString("c_DESCRIPTION"),
					srs.getString("c_DOCKERID"),
					srs.getString("u_NAME"),
					srs.getInt("i_ID")
					);
		}
		else if (obj.getClass().isInstance(Image.class))
		{
			return (T) new Image(
					srs.getInt("i_ID"),
					srs.getString("i_INSTANCE"),
					srs.getString("i_VERSION"),
					srs.getString("i_TIER"),
					srs.getFloat("i_CPU"),
					srs.getBigDecimal("i_RAM"),
					srs.getInt("i_STORAGE")
					);
		}
		else if (obj.getClass().isInstance(User.class))
		{
			return (T) new User(
					srs.getString("u_NAME"),
					srs.getString("u_PASSWORD")
					);
		}
		
		return null;
	}
	
	/**
	 * Gets an object from a ResultSet. This object corresponds to the object that was passed to the method
	 * 
	 * @param rs ResultSet that was passed to the method
	 * @param obj Object that was passed to the method
	 * @return Returns the object that was found in the ResultSet
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getResultSet(ResultSet rs, Object obj) throws SQLException
	{
		if (obj.getClass().isInstance(Container.class))
		{
			return (T) new Container(
					rs.getString("c_NAME"),
					rs.getString("c_DESCRIPTION"),
					rs.getString("c_DOCKERID"),
					rs.getString("u_NAME"),
					rs.getInt("i_ID")
					);
		}
		else if (obj.getClass().isInstance(Image.class))
		{
			return (T) new Image(
					rs.getInt("i_ID"),
					rs.getString("i_INSTANCE"),
					rs.getString("i_VERSION"),
					rs.getString("i_TIER"),
					rs.getFloat("i_CPU"),
					rs.getBigDecimal("i_RAM"),
					rs.getInt("i_STORAGE")
					);
		}
		else if (obj.getClass().isInstance(User.class))
		{
			return (T) new User(
					rs.getString("u_NAME"),
					rs.getString("u_PASSWORD")
					);
		}
		
		return null;
	}

}
