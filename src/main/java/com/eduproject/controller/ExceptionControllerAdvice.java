package com.eduproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public String exceptionOccured(Exception e,Model model) {
		logger.error("ExceptionOccured" + e.getMessage());
		model.addAttribute("errorMessage", "Error Occured "+e.getMessage());
		return "error";
	}

}
