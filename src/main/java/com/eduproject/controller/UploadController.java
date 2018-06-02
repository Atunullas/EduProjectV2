package com.eduproject.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.eduproject.dto.PersonalityDTO;
import com.eduproject.dto.QuestionDTO;
import com.eduproject.service.PersonalityService;
import com.eduproject.service.QuestAnsService;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

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
		model.addAttribute("questions", questAnsService.performFetchAll());
		return "pdfView";
	}

	@RequestMapping(value = "/bulkUpload.view")
	public String bulkUploadView() {
		logger.info("Entering bulkUploadView method");
		return "bulkUploadView";
	}

	@RequestMapping(value = "/bulkUpload.do")
	public String bulkUpload(@RequestPart MultipartFile csvfile) {
		logger.info("Entering bulkUpload method");
		BufferedReader br;
		List<String> result = new ArrayList<>();
		try {
			String line;
			InputStream is = csvfile.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				logger.info("line values -" + line);
				result.add(line);
			}
		} catch (IOException e) {
			System.out.println("error while reading csv and put to db : " + e.getMessage());
		}
		return "bulkUploadComplete";
	}
}
