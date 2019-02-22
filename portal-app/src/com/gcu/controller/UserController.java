package com.gcu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.exception.UserErrorException;
import com.gcu.exception.UserFoundException;
import com.gcu.exception.UserNotFoundException;
import com.gcu.model.ErrorMessage;
import com.gcu.model.User;
import com.gcu.service.UserServiceInterface;

@Controller
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserServiceInterface userService;

	/**
	 * Nav the user to the login form view
	 * 
	 * @return ModelAndView login
	 */
	@GetMapping("/login")
	public ModelAndView login() 
	{
		// MAV login with blank user model
		return new ModelAndView("login", "user", new User());
	}

	/**
	 * Checks if username + password found in database.
	 * Sets User in session.
	 * 
	 * @param User user
	 * @param BindingResult - validation
	 * @HttpServletRequest - session (can be substituted for ModelMap)
	 * @return ModelAndView productCat
	 */
//	@PostMapping("/loginUser")
	@RequestMapping(path = "/loginUser", method = RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("user") User user, 
			BindingResult result, HttpServletRequest request) 
	{
		try 
		{
			// Validate the Form. Return if form is invalid.
			if (result.hasErrors())
			{
				return new ModelAndView("login", "user", user);
			}
			// Call User Service to find the user 
			user = userService.findUser(user);
			// Save the user in the session
			request.getSession().setAttribute("token", user);
			
			// Get all containers related to the user
			return new ModelAndView("productCat", "user", user);
		} 
		// Catches UserNotFoundException and return to login view
		catch (UserNotFoundException e) 
		{
			ErrorMessage error = new ErrorMessage("Login failed", "Username or password was incorrect. Please try again.", "user/login");
			return new ModelAndView("error", "error", error);
		}
	}

	/**
	 * Navigate the user to the register form view
	 * 
	 * @return MdoelAndView register
	 */
	@GetMapping("/register")
	public ModelAndView register() 
	{
		// MAV register with blank user model
		return new ModelAndView("register", "user", new User());
	}

	/**
	 * Registers the user by submitting the request the user service
	 * 
	 * @param User user
	 * @param BindingResult - validation
	 * @param HttpServletRequest - session
	 * @return ModelAndView userPage
	 */
	@PostMapping("/registerUser")
	public ModelAndView addUser(@Valid @ModelAttribute("userProfile") User user, 
			BindingResult result, HttpServletRequest request) 
	{
		try 
		{
			// Validate the form. Return to the Register page if form is invalid
			if (result.hasErrors()) 
			{
				return new ModelAndView("registerUser", "userProfile", user);
			}
			// Call the User Service to create the user
			userService.createUser(user);
			
			// Nav the user ot the userpage
			return new ModelAndView("userPage", "user", user);
		} 
		// Catches the UserFoundException for invalid request
		catch (UserFoundException e) 
		{
			String message = "That username has already been registered. Please pick a new one a try again.";
			ErrorMessage error = new ErrorMessage("Registration failed", message, "user/register");
			return new ModelAndView("error", "error", error);
		}
		// Catches the UserErrorException for Server error
		catch (UserErrorException e)
		{
			String message = "There was an error registering your account. Please try again.";
			ErrorMessage error = new ErrorMessage("Server error", message, "user/register");
			return new ModelAndView("error", "error", error);
		}
	}

	/**
	 * Logs the user out. Redireting them to the root. Destroys the session.
	 * 
	 * @param HttpServletRequest - session
	 * @return String root view
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) 
	{
		// Unbind all the mapping, destroying the session
		request.getSession().invalidate();
		// redirect to the root of the app
		return "redirect:/";
	}
}
