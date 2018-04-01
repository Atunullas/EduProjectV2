package com.eduproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("roles")
public class LoginController {

	@Autowired
	MessageSource messageSource;

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/home.do" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		return "home";
	}
	
	@RequestMapping(value = { "/service.do" }, method = RequestMethod.GET)
	public String servicePage(ModelMap model) {
		return "services";
	}

}
