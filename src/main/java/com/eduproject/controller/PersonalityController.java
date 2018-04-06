package com.eduproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduproject.dto.PersonalityDTO;
import com.eduproject.service.PersonalityService;

@Controller
public class PersonalityController {

	@Autowired
	private PersonalityService personalityService;

	@RequestMapping(value = { "/startPerson.do", "/nextPerson.do" })
	public String startQuest(HttpServletRequest request, Model model) {
		String view = "viewPersonality";
		PersonalityDTO person = personalityService.performFetch(1);
		model.addAttribute("person", person);
		return view;
	}
}
