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
import com.gcu.model.Container;

public class ContainerDAO implements ContainerDAOInterface
{
	// Setter injection jdbcTemplate
	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	public void setDataSource(DataSource dataSource) 
	{
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Need this one
	 */
	@Override
	public List<Container> findAllByUsername(final String username)
	{
		try
		{
			// READ query to identify the container by username and password.
			final String query = "SELECT * FROM `containers` " + 
					"LEFT JOIN `images` ON `containers`.`i_ID` = `images`.`i_ID` " + 
					"WHERE `containers`.`u_NAME` = (?)";

			// Execute query and get result set
			List<Container> containers = jdbcTemplateObject.query(
					query,
					new PreparedStatementSetter()
					{
						@Override
						public void setValues(PreparedStatement ps) throws SQLException
						{
							ps.setString(1, username);
						}
					},
					new RowMapper<Container>()
					{
						@Override
						public Container mapRow(ResultSet rs, int rowNum) throws SQLException
						{
							Container container = new Container();
							
							container.setContainerResultSet(rs);
							container.setImageResultSet(rs);
							
							return container;
						}
					}
					);
			
			return containers;
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
	 * 
	 * @param Container
	 * @result boolean
	 * @throws DatabaseException
	 */
	@Override
	public boolean findIfExists(final Container container)
	{
		try
		{
			// READ query to identify the container by username and password.
			final String query = "SELECT * FROM `containers` WHERE `u_NAME` = ? AND `i_ID` = ?";

			// Execute query and get result set
			List<Container> containers = jdbcTemplateObject.query(
					query,
					new PreparedStatementSetter()
					{
						@Override
						public void setValues(PreparedStatement ps) throws SQLException
						{
							ps.setString(1, container.getUsername());
							ps.setInt(2, container.getImageId());
						}
					},
					new RowMapper<Container>()
					{
						@Override
						public Container mapRow(ResultSet rs, int rowNum) throws SQLException
						{
							return Container.getResultSet(rs);
						}
					}
					);
			
			// if the size of the list is anything other than 1, return false;
			if(containers.size() != 1)
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
	 * Create a new container in the database using PreparedStatement
	 * 
	 * @param Container container
	 * @return boolean
	 * @throws DatabaseException
	 */
	@Override
	public boolean create(final Container container) 
	{
		try 
		{
			// INSERT statement with container attributes
			final String query = "INSERT INTO `containers` " + Container.getSqlInsertQuery();
			
			// execute prepared statement
			int rows = jdbcTemplateObject.update(
					new PreparedStatementCreator() 
					{ 
						@Override
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
						{
							PreparedStatement ps = connection.prepareStatement(query, new String[] { "ID" } );

							ps.setString(1, container.getName());
							ps.setString(2, container.getDescription());
							ps.setString(3, container.getDockerId());
							ps.setString(4, container.getUsername());
							ps.setInt(5, container.getImageId());
							
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
	
	/**
	 * UPDATE method
	 * Updates the container's docker identification
	 * 
	 * @param Container container
	 * @param String docker
	 * @return boolean
	 * @throws DatabaseException
	 */
	public boolean update(Container container, String docker) {
		try
		{
			String sql = "UPDATE RDP.CONTAINERS SET c_DOCKERID = ? WHERE u_NAME = ? AND `i_ID` = ?";
			
			// Add the docker name of the container created
			int rows = jdbcTemplateObject.update(sql, docker, container.getUsername(), container.getImageId());
			
			// if the size of the list is anything other than 1, return false;
			if(rows == 0)
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
	 * Need this one
	 */
	@Override
	public boolean delete(Container container) {
		// TODO Auto-generated method stub
		return false;
	}
}
