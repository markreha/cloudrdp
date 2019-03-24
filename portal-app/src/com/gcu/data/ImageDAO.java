package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.DatabaseException;
import com.gcu.model.Image;

public class ImageDAO implements ImageDAOInterface
{
	// Setter injection jdbcTemplate
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) 
	{
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/**
	 * Assembles all the Images from the database into a list
	 * 
	 * @result List<Image> - list of all the images
	 */
	@Override
	public List<Image> findAll() 
	{
		try
		{
			// Init the image list
			List<Image> images = new ArrayList<Image>();
			
			// READ query to identify the user by username and password.
			String query = "SELECT * FROM `images` LIMIT 1000";

			// Execute query and get result set
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(query);
			
			// Iterate through the result set and add to the list
			while(srs.next())
			{
				images.add(Image.getSqlRowSet(srs));
			}
			
			// Last Row should still be the First, return the user
			return images;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}

	@Override
	public Image find(int imageId) 
	{
		try
		{
			// READ query to identify the user by username and password.
			String query = "SELECT * FROM `images` WHERE `i_ID` = " + imageId;

			// Execute query and get result set
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(query);
			
			// Move to the last row
			srs.last();
			
			// Checks the Size of the Results. If anything other than 1, return null
			if(srs.getRow() != 1)
			{
				return null;
			}
			
			// Last Row should still be the First, return the user
			return Image.getSqlRowSet(srs);
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
}
