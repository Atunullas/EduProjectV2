package com.eduproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduproject.form.UploadPersonalityForm;
import com.eduproject.form.UploadQuestionForm;
import com.eduproject.service.PersonalityService;
import com.eduproject.service.QuestAnsService;

@Controller
public class UploadController {

	@Autowired
	private QuestAnsService questAnsService;
	
	@Autowired
	private PersonalityService personalityService;

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
	
	@RequestMapping(value = "/savePersonality.do")
	public String saveUploadPersonality(@ModelAttribute UploadPersonalityForm form) {
		personalityService.performSave(form);
		return "uploadQuestForm";
	}
}
