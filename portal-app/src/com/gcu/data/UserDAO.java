package com.gcu.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.AlreadyRegisteredException;
import com.gcu.exception.BadLoginException;
import com.gcu.model.User;

public class UserDAO implements UserDAOInterface {
	
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) 
	{
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	private User currentUser;
	private long profileID;
	private String sqlProfile;
	private String sqlUser;
	
	/**
	 * CREATE method
	 * Create a new user in the database
	 */
	@Override
	public boolean createUser(User user) {
		
		currentUser = user;
		// Find if user exists
		String uniqueSql = "SELECT COUNT(*) FROM `user` WHERE `u_username` = ?";
		int uniqueRowsCount = jdbcTemplateObject.queryForObject(uniqueSql, new Object[] { user.getUsername() },
				Integer.class);
		
		if (uniqueRowsCount > 0) {
			throw new AlreadyRegisteredException();
		}
		
//		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		// Insert User Profile and get last inserted ID
		sqlProfile = String.format("INSERT INTO `user` (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, DATE_OF_BIRTH, ADDRESS) VALUES(%s,%s,%s,%s,%s,%s)");

		jdbcTemplateObject.update(sqlProfile);

		// Insert User and get last inserted ID
		sqlUser = "INSERT INTO `user` (`USERNAME`, `PASSWORD`, `USER_PROFILE_ID`) VALUES(?,?,?)";

		jdbcTemplateObject.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sqlUser, new String[] { "ID" });
				ps.setString(1, currentUser.getUsername());
				ps.setString(2, currentUser.getPassword());
				ps.setInt(3, (int)profileID);
				return ps;
			}
		}, new GeneratedKeyHolder());

		return true;
	}

	/**
	 * READ method
	 * Find if the user credentials return a registered user.
	 * 
	 * @param User user
	 * @return boolean
	 * @throws BadLoginException
	 */
//	@Override
//	public boolean findUser(User user) {
//		String sql = "SELECT * FROM `user` WHERE `username` = ? AND `password` = ?";
//
//		SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, user.getUsername(), user.getPassword());
//		if (srs.next()) {
//			
//			// Whats the point of this
//			int id = srs.getInt("u_id");
//			id += id;
//			return true;
//			
//		} else {
//			// Business Logic should not be validated here
//			throw new BadLoginException();
//		}
//	}
	
	/**
	 * READ Method
	 * Validation Login query checks if username exists in the database, case sensitive.
	 * 
	 * @param User user
	 * @return User user || null
	 * @throws DatabaseException
	 */
	@Override
	public boolean findUser(User user)
	{
		try
		{
			// READ query to identify the user by username and password.
			String sql = "SELECT * FROM `user` WHERE "
					+ "USERNAME = '"+user.getUsername()+"' "
					+ "AND PASSWORD = '"+user.getPassword()+"'";
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			
			// Goes to the Last Row of the Results
			srs.beforeFirst();
			srs.last();
			
			// Checks the Size of the Results. If anything other than 1, return null
			if(srs.getRow() != 1)
			{
				return false;
			}
			
			// Last Row should still be the First, and return the user
//			return User.getSqlRowSet(srs);
			return true;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
//			throw new DatabaseException(e);
			throw new BadLoginException();
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
