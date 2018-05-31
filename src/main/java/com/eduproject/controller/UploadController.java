package com.eduproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduproject.dto.PersonalityDTO;
import com.eduproject.dto.QuestionDTO;
import com.eduproject.service.PersonalityService;
import com.eduproject.service.QuestAnsService;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private QuestAnsService service;

	@Autowired
	private QuestAnsService questAnsService;

	@Autowired
	private PersonalityService personalityService;

	@RequestMapping(value = "/uploadQuest.do")
	public String uploadQuest() {
		logger.info("Entering uploadQuest method");
		return "uploadQuestForm";
	}

	@RequestMapping(value = "/saveUploadQuest.do")
	public String saveUploadQuest(QuestionDTO dto) {
		logger.info("Entering saveUploadQuest method");
		questAnsService.performSave(dto);
		return "uploadQuestForm";
	}

	@RequestMapping(value = "/uploadPerson.do")
	public String uploadPerson() {
		logger.info("Entering uploadPerson method");
		return "uploadPersonForm";
	}

	@RequestMapping(value = "/savePersonality.do")
	public String saveUploadPersonality(PersonalityDTO dto) {
		logger.info("Entering saveUploadPersonality method");
		personalityService.performSave(dto);
		return "uploadPersonForm";
	}

	@RequestMapping(value = "/printQuest.pdf")
	public String printQuest(Model model) {
		logger.info("Entering printQuest method");
		model.addAttribute("questions", service.performFetchAll());
		return "pdfView";
	}
}
