package com.eduproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduproject.form.UploadQuestionForm;
import com.eduproject.service.QuestAnsService;

@Controller
public class UploadController {

	@Autowired
	private QuestAnsService questAnsService;

	@RequestMapping(value = "/uploadQuest.do")
	public String uploadQuest() {
		return "uploadQuestForm";
	}

	@RequestMapping(value = "/saveUploadQuest.do")
	public String saveUploadQuest(UploadQuestionForm form) {
		questAnsService.performSave(form);
		return "uploadQuestForm";
	}

	@RequestMapping(value = "/uploadPerson.do")
	public String uploadPerson() {
		return "uploadPersonForm";
	}
}
