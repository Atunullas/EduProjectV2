package com.eduproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eduproject.model.Subject;
import com.eduproject.service.QuestAnsService;

@Controller
public class LoginController {

	@Autowired
	private QuestAnsService questAnsService;

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/home.do" }, method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}

	@RequestMapping(value = { "/service.do" }, method = RequestMethod.GET)
	public String servicePage(Model model) {
		List<Subject> allSubjects = questAnsService.performFetchAllSubjects();
		model.addAttribute("allSubjects", allSubjects);
		return "services";
	}

}
