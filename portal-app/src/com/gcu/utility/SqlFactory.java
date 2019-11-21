package com.gcu.utility;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.model.Container;
import com.gcu.model.Image;
import com.gcu.model.User;

public class SqlFactory 
{
	public static String findQuery(Object obj) throws SQLException
	{
		String sql = "";
		
		if (obj.equals(Container.class))
		{
 			sql = "SELECT * FROM `containers` " + 
					"LEFT JOIN `images` ON `containers`.`i_ID` = `images`.`i_ID` " + 
					"WHERE `containers`.`u_NAME` = (?)";
		}
		else if (obj.equals(Image.class))
		{
 			sql = "SELECT * FROM `images` WHERE `i_ID` = ?";
		}
		else if (obj.equals(User.class))
		{
 			sql = "SELECT * FROM `users` WHERE BINARY `u_NAME` = ? AND BINARY `u_PASSWORD` = ?";
		}
		
		return sql;
	}
	
	public static String findIfExistsQuery(Object obj) throws SQLException
	{
		String sql = "";
		
		if (obj.equals(Container.class))
		{
			sql = "SELECT * FROM `containers` WHERE `u_NAME` = ? AND `i_ID` = ?";
		}
		else if (obj.equals(User.class))
		{
			sql = "SELECT * FROM `users` WHERE UPPER(`u_NAME`) LIKE UPPER(?)";
		}
		
		return sql;
	}
	
	public static String findAllQuery(Object obj) throws SQLException
	{
		String sql = "";
		
		if (obj.equals(Image.class))
		{
			sql = "SELECT * FROM `images` LIMIT 1000";
		}
		
		return sql;
	}
	
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
		if(obj.equals(Container.class))
		{
			sql = "INSERT INTO `containers` (c_NAME, c_DESCRIPTION, c_DOCKERID, u_NAME, i_ID) VALUES "
					+ "(?, ?, ?, ?, ?)";
		}
		// If object is of instance Image
		else if (obj.equals(Image.class))
		{
			sql = "INSERT INTO `images` "
					+ "(i_ID, i_INSTANCE, i_NAME, i_VERSION, i_PORT, i_TIER, i_CPU, i_RAM, i_STORAGE) VALUES "
					+ "(NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
		}
		// If object is instance of User
		else if (obj.equals(User.class))
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
		if(obj.equals(Container.class))
		{
			sql = "UPDATE `containers` SET c_DOCKERID = ? WHERE  u_NAME = ? AND i_ID = ?";
		}
		// If object is of instance Image
		else if (obj.equals(Image.class))
		{
			sql = "UPDATE `images` SET i_INSTANCE = ?, i_NAME = ?, i_VERSION = ?, i_PORT = ?, i_TIER = ?, i_CPU = ?, "
					+ "i_RAM = ?, i_STORAGE = ? WHERE i_ID = ?";
		}
		// If object is instance of User
		else if (obj.equals(User.class))
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
		if (obj.equals(Container.class))
		{
			return (T) new Container(
					srs.getString("c_NAME"),
					srs.getString("c_DESCRIPTION"),
					srs.getString("c_DOCKERID"),
					srs.getString("u_NAME"),
					srs.getInt("i_ID")
					);
		}
		else if (obj.equals(Image.class))
		{
			return (T) new Image(
					srs.getInt("i_ID"),
					srs.getString("i_INSTANCE"),
					srs.getString("i_NAME"),
					srs.getString("i_VERSION"),
					srs.getInt("i_PORT"),
					srs.getString("i_TIER"),
					srs.getFloat("i_CPU"),
					srs.getBigDecimal("i_RAM"),
					srs.getInt("i_STORAGE")
					);
		}
		else if (obj.equals(User.class))
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
		if (obj.equals(Container.class))
		{
			return (T) new Container(
					rs.getString("c_NAME"),
					rs.getString("c_DESCRIPTION"),
					rs.getString("c_DOCKERID"),
					rs.getString("u_NAME"),
					rs.getInt("i_ID")
					);
		}
		else if (obj.equals(Image.class))
		{
			return (T) new Image(
					rs.getInt("i_ID"),
					rs.getString("i_INSTANCE"),
					rs.getString("i_NAME"),
					rs.getString("i_VERSION"),
					rs.getInt("i_PORT"),
					rs.getString("i_TIER"),
					rs.getFloat("i_CPU"),
					rs.getBigDecimal("i_RAM"),
					rs.getInt("i_STORAGE")
					);
		}
		else if (obj.equals(User.class))
		{
			return (T) new User(
					rs.getString("u_NAME"),
					rs.getString("u_PASSWORD")
					);
		}
		
		return null;
	}

}
