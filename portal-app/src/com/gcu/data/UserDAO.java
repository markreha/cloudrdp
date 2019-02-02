package com.gcu.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.AlreadyRegisteredException;
import com.gcu.exception.BadLoginException;
import com.gcu.model.User;

public class UserDAO implements UserDAOInterface {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	private User currentUser;
	private long profileID;
	private String sqlProfile;
	private String sqlUser;
	
	@Override
	public boolean createUser(User user) {
		currentUser = user;
		// Find if user exists
		String uniqueSql = "SELECT COUNT(*) FROM RDP_CLOUD.USER WHERE u_username = ?";
		int uniqueRowsCount = jdbcTemplateObject.queryForObject(uniqueSql, new Object[] { user.getUsername() },
				Integer.class);
		if (uniqueRowsCount > 0) {
			throw new AlreadyRegisteredException();
		}
		KeyHolder keyHolder = new GeneratedKeyHolder();
		// Insert User Profile and get last inserted ID
		sqlProfile = String.format("INSERT INTO RDP_CLOUD.USER(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, DATE_OF_BIRTH, ADDRESS) VALUES(%s,%s,%s,%s,%s,%s)");

		jdbcTemplateObject.update(sqlProfile);

		// Insert User and get last inserted ID
		sqlUser = "INSERT INTO FLIGHT_MANAGER.USERS(USERNAME, PASSWORD, USER_PROFILE_ID) VALUES(?,?,?)";

		jdbcTemplateObject.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sqlUser, new String[] { "ID" });
				ps.setString(1, currentUser.getUsername());
				ps.setString(2, currentUser.getPassword());
				ps.setInt(3, (int)profileID);
				return ps;
			}
		}, keyHolder);

		return true;
	}

	@Override
	public boolean findUser(User user) {
		String sql = "SELECT * FROM RDP_CLOUD.USER WHERE u_id = ? AND u_password = ?";

		SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, user.getUsername(), user.getPassword());
		if (srs.next()) {
			int id = srs.getInt("u_id");
			return true;
		} else {
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

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
}
