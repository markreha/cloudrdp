package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.exception.AlreadyRegisteredException;
import com.gcu.exception.BadLoginException;
import com.gcu.model.Container;
import com.gcu.model.ErrorMessage;
import com.gcu.model.User;
import com.gcu.service.UserServiceInterface;

@Controller
@RequestMapping("/login")
public class UserController {
	UserServiceInterface service;

	/**
	 * @return A form to create a User
	 */
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login", "user", new User());
	}

	/**
	 * This method at /loginUser checks if username + password found in database.
	 * Sets User in session
	 * 
	 * @param user Model of a User that got logged in
	 * @return Model and View to Display the Posted User
	 */
	@RequestMapping(path = "/loginUser", method = RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("user") User user, BindingResult result,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ModelAndView("loginUser", "user", user);
		}

		// Add user to database and return userPage. Return error if already registered.

		try {
			if (service.findUser(user)) {
				request.getSession().setAttribute("token", user);
			}
		} catch (BadLoginException e) {
			ErrorMessage error = new ErrorMessage("Login failed",
					"Username or password was incorrect. Please try again.", "login");
			return new ModelAndView("error", "error", error);
		}
		// Get all containers related to the user

		return new ModelAndView("productCat", "user", user);
	}

	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("register", "user", new User());
	}

	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/registerUser", method = RequestMethod.POST)
	public ModelAndView addUser(@Valid @ModelAttribute("userProfile") User user, BindingResult result,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ModelAndView("registerUser", "userProfile", user);
		}

		// RegisterDAO make sure that user is not already in the database
		// If they are not, put them in

		try {
			if (service.createUser(user)) {
				request.getSession().setAttribute("token", user);
			}
		} catch (AlreadyRegisteredException e) {
			String message = "That username has already been registered. Please pick a new one a try again.";
			ErrorMessage error = new ErrorMessage("Registration failed", message, "register");
			return new ModelAndView("error", "error", error);
		}
		List<Container> projects = new ArrayList<Container>();

		return new ModelAndView("userPage", "user", user);
	}

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@Autowired
	public void setUserService(UserServiceInterface service) {
		this.service = service;
	}
}
