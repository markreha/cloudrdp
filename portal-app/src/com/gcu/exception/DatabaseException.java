package com.gcu.exception;

/**
 * Exception in DAO's for declaring an issue regarding: 
 * 		Database Connectivity / SQL Exception
 */
public class DatabaseException extends RuntimeException
{
	private static final long serialVersionUID = 0L;
	
	public DatabaseException(Throwable e)
	{
		super(e);
	}
}