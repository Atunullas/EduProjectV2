package com.eduproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduproject.dao.QuestAnsDao;

@Controller
public class AnswerCheckController {

	@Autowired
	private QuestAnsDao questAnsDao;

	@RequestMapping(value = "/submitAns.do")
	public String submitAns(Model model) {
		model.addAttribute("User", "Arun");
		model.addAttribute("questions", questAnsDao.performFetch(Integer.valueOf("2")));
		return "quizTemplate";
	}
}
