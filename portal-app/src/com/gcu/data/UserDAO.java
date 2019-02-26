package com.gcu.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.DatabaseException;
import com.gcu.model.User;

public class UserDAO implements UserDAOInterface 
{
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) 
	{
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
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
	public boolean createUser(final User user) 
	{
		try 
		{
			// INSERT statement with user attributes
			final String query = "INSERT INTO `user` (" + User.getSqlParams() + ") VALUES (?,?)";
			// execute query
			int rows = jdbcTemplateObject.update(new PreparedStatementCreator() 
			{
				// initialize a statement
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
				{
					// Setup the statement using User model
					return User.prepareStatement(connection, query, user);
				}
			}, new GeneratedKeyHolder());
			
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

	/**
	 * READ method
	 * Find a user 
	 * 
	 * @param User user
	 * @return User
	 * @throws DatabaseException
	 */
	@Override
	public User findUser(User user)
	{
		try
		{
			// READ query to identify the user by username and password.
			String query = "SELECT * FROM `user` WHERE "
					+" BINARY `USERNAME` = '" + user.getUsername() + "'"
							+ " AND BINARY `PASSWORD` = '" + user.getPassword() + "'";

			// Execute query and get result set
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(query);
			// Goes to the Last Row of the Results
			srs.last();
			// if the Last Row is not the only index, user is not unique or exists
			if(srs.getRow() != 1)
			{
				return null;
			}
			// Last Row should still be the First, return the user
			return User.getSqlRowSet(srs);
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
}
