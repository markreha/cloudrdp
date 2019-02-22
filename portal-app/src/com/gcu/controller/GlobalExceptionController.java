package com.gcu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.gcu.exception.DatabaseException;
import com.gcu.model.ErrorMessage;

/**
 * Global Exception Handler Controller that catches all uncaught exceptions
 */
@ControllerAdvice
public class GlobalExceptionController 
{
	/**
	 * 500 Status Errors
	 * Catches DatabaseExceptions and assembles an error page for Server Side Stuff.
	 * 
	 * @param Exception e
	 * @return ModelAndView
	 */
	@ExceptionHandler(DatabaseException.class)
	public ModelAndView handleDatabaseException(Exception e) 
	{
		// Set the view for the error page
		ModelAndView mv = new ModelAndView("error");
		ErrorMessage error = new ErrorMessage("Status 500 Page", e.toString(),"");
		mv.addObject("error", error);
		// Return MAV with error
		return mv;
	}
	
	/**
	 * 404 Status Errors
	 * Catches when a request URI does not exist
	 * 
	 * @return ModelAndView
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoHandlerFoundException(Exception e) 
	{
		// Set the view for the error page
		ModelAndView mv = new ModelAndView("error");
		ErrorMessage error = new ErrorMessage("Status 404 Page", e.toString(),"");
		mv.addObject("error", error);
		// Return MAV with error
		return mv;
    }
}