package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gcu.model.Container;

public class ContainerDAO implements ContainerDAOInterface{
	
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	public void setDataSource(DataSource dataSource) 
	{
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean createContainer(Container container) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Container findContainer(Container container) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Container> viewContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateContainer(Container container) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteContainer(Container container) {
		// TODO Auto-generated method stub
		return false;
	}
}
