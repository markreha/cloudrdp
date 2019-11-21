package com.gcu.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.gcu.exception.DatabaseException;
import com.gcu.model.User;
import com.gcu.utility.SqlFactory;

public class UserDAO implements UserDAOInterface
{
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) 
	{
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/**
	 * READ method
	 * validate the user by matching the exact username and password, case sensative
	 * 
	 * @param User user
	 * @return User
	 * @throws DatabaseException
	 */
	@Override
	public User find(final User user)
	{
		try
		{
			// READ query to identify the user by username and password
			final String query = SqlFactory.findQuery(User.class);
			
			// Execute query and get result set
			List<User> users = jdbcTemplateObject.query(
					query,
					new PreparedStatementSetter()
					{
						@Override
						public void setValues(PreparedStatement ps) throws SQLException
						{
							ps.setString(1, user.getUsername());
							ps.setString(2, user.getPassword());
						}
					},
					new RowMapper<User>()
					{
						@Override
						public User mapRow(ResultSet rs, int rowNum) throws SQLException
						{
							return SqlFactory.getResultSet(rs, User.class);
						}
					}
					);
			
			// Verify the list contains only one, else return null
			if(users.size() != 1)
			{
				return null;
			}
			
			// return the User model
			return users.get(0);
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * READ method
	 * check to see if the instance of the user by username exists, case insensitive for all results
	 * 
	 * @param User
	 * @result boolean
	 * @throws DatabaseException
	 */
	@Override
	public boolean findIfExists(final User user)
	{
		try
		{
			// READ query to identify the user by username and password.
			final String query = SqlFactory.findIfExistsQuery(User.class);
		
			// Execute query and get effected rows
			List<User> users = jdbcTemplateObject.query(
					query,
					new PreparedStatementSetter()
					{
						@Override
						public void setValues(PreparedStatement ps) throws SQLException
						{
							ps.setString(1, user.getUsername());
						}
					},
					new RowMapper<User>()
					{
						@Override
						public User mapRow(ResultSet rs, int rowNum) throws SQLException
						{
							return SqlFactory.getResultSet(rs, User.class);
						}
					}
					);

			// if the size of the list is anything other than 1, return false;
			if(users.size() != 1)
			{
				return false;
			}
			
			// Return that one instance of the container exists
			return true;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * CREATE method
	 * Create a new user in the database using PreparedStatement
	 * 
	 * @param User user
	 * @return boolean
	 * @throws DatabaseException
	 */
	@Override
	public boolean create(final User user) 
	{
		try 
		{
			// INSERT statement with user attributes
			final String query = SqlFactory.getSqlInsertQuery(User.class);
			
			// execute prepared statement
			int rows = jdbcTemplateObject.update(
					new PreparedStatementCreator() 
					{ 
						@Override
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
						{
							PreparedStatement ps = connection.prepareStatement(query, new String[] { "ID" } );
							ps.setString(1, user.getUsername());
							ps.setString(2, user.getPassword());
							return ps; 
						}
					},
					new GeneratedKeyHolder()
					);
			
			// If affected rows is one, create was successful
			if(rows == 1)
			{
				return true;
			}
			// if now rows were affected, create failed.
			return false;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
	
	@Override
	public boolean update(User user) 
	{
		/*
		 *  TODO: Update user datatable fields of custom configuration 
		 *  	variables for Swarm
		 */
		
		return false;
	}
}
