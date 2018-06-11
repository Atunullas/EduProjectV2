package com.eduproject.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eduproject.dto.OptionDTO;
import com.eduproject.dto.PersonalityDTO;
import com.eduproject.dto.QuestionDTO;
import com.eduproject.model.EQuestType;
import com.eduproject.service.PersonalityService;
import com.eduproject.service.QuestAnsService;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	private static final String upldQuestionHeader = "QUESTION,OPTION_1,OPTION_2,OPTION_3,OPTION_4,ANSWER,QUESTION_TYPE";

	private static final String upldPersonalityHeader = "PERSON_NAME,PERSON_SEX,PERSON_DOB,PERSON_DOE,PERSON_ABOUT";

	private static final String PERSON_CSV_FILE = "csvTemplates/uploadPersonalityCSVTemplate.csv";

	private static final String QUEST_CSV_FILE = "csvTemplates/uploadQuestionCSVTemplate.csv";

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
	public String saveUploadQuest(QuestionDTO dto, HttpServletRequest request) {
		logger.info("Entering saveUploadQuest method");

		String view = "uploadQuestForm";
		String[] ansOpts = request.getParameterValues("isAns");
		String[] optionTxts = request.getParameterValues("optionTxt");
		if (!StringUtils.isEmpty(dto.getQuestionType()) && dto.getQuestionType().equals(EQuestType.TRUE_FALSE.name())) {
			optionTxts = new String[2];
			optionTxts[0] = "True";
			optionTxts[1] = "False";
		}
		List<OptionDTO> optDTOs = new ArrayList<>();
		if (optionTxts.length > ansOpts.length) {
			for (String x : optionTxts) {
				for (String s : ansOpts) {
					OptionDTO optDTO = new OptionDTO();
					optDTO.setOptionTxt(x);
					if (x.equals(s)) {
						optDTO.setIsAns("Y");
					} else {
						optDTO.setIsAns("N");
					}
					optDTOs.add(optDTO);
				}
			}
			dto.setOptions(optDTOs);
			questAnsService.performSave(dto);
		} else {
			view = "error";
		}
		return view;
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

	@PostMapping("/bulkUploadQuestion.do")
	public String bulkUploadQuestion(@RequestParam(name = "csvFile") MultipartFile csvFile, Model model) {
		logger.info("Entering bulkUpload method");
		BufferedReader br;
		int count = 0;
		try {
			String line;
			InputStream is = csvFile.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			boolean isHeader = true;
			while ((line = br.readLine()) != null) {
				logger.info("line values -" + line);
				if (!isHeader) {
					String[] fieldArray = line.split(",");
					QuestionDTO quesDTO = new QuestionDTO();
					quesDTO.setQuestionTxt(fieldArray[0]);

					// Setting up options
					List<OptionDTO> optDTOs = new ArrayList<>();
					quesDTO.setQuestionType(fieldArray[6]);
					int optionCount = 4;
					if (EQuestType.TRUE_FALSE.name().equals(quesDTO.getQuestionType())) {
						optionCount = 2;
					}
					for (int i = 1; i <= optionCount; i++) {
						OptionDTO optDTO = new OptionDTO();
						optDTO.setOptionTxt(fieldArray[i]);
						if (i == Integer.valueOf(fieldArray[5])) {
							optDTO.setIsAns("Y");
						} else {
							optDTO.setIsAns("N");
						}
						logger.info("Iterating through each options for question -" + optDTO);
						optDTO.setQuest(quesDTO);
						optDTOs.add(optDTO);
					}
					logger.info("Setting options to question -" + optDTOs);
					quesDTO.setOptions(optDTOs);

					logger.info("Saving value to Questions and Options Table -" + line);
					// Saving Questions
					if (EQuestType.valueOf(quesDTO.getQuestionType()) != null) {
						questAnsService.performSave(quesDTO);
					} else {
						logger.info("Invalid question Type posted  -" + quesDTO.getQuestionType());
					}
					++count;
				} else {
					// Check Header for the uploaded CSV
					if (!line.equals(upldQuestionHeader)) {
						model.addAttribute("errorMessage", "Error Occurred : Invalid File Type uploaded");
						logger.error("Error Occurred : Invalid File Type uploaded");
						return "error";
					}
				}
				isHeader = false;
			}
		} catch (NumberFormatException | IOException e) {
			logger.error("error while reading csv and put to db : " + e.getMessage());
		}
		model.addAttribute("bulkUploadMessage", "File Processed, Number of rows Processed :" + count);
		logger.info("Number of rows Processed " + count);
		return "bulkUploadComplete";
	}

	@RequestMapping(value = "/bulkUploadPersonality.do")
	public String bulkUploadPersonality(@RequestParam("csvFile") MultipartFile csvFile, Model model) {
		logger.info("Entering bulkUploadPersonality method");
		BufferedReader br;
		List<PersonalityDTO> personDTOList = new ArrayList<>();
		int count = 0;
		try {
			String line;
			InputStream is = csvFile.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			boolean isHeader = true;
			while ((line = br.readLine()) != null) {
				logger.info("line values -" + line);
				if (!isHeader) {
					String[] fieldArray = line.split(",");
					PersonalityDTO persDTO = new PersonalityDTO();
					persDTO.setPersonName(fieldArray[0]);
					persDTO.setPersonGender(fieldArray[1]);
					persDTO.setPersonDOB(fieldArray[2]);
					persDTO.setPersonDOE(fieldArray[3]);
					persDTO.setPersonAbout(fieldArray[4]);
					logger.info("Saving value to Personality Table -" + line);
					personalityService.performSave(persDTO);
					personDTOList.add(persDTO);
					++count;
				} else {
					// Check Header for the uploaded CSV
					if (!line.equals(upldPersonalityHeader)) {
						model.addAttribute("errorMessage", "Error Occurred : Invalid File Type uploaded");
						logger.error("Error Occurred : Invalid File Type uploaded");
						return "error";
					}
				}
				isHeader = false;
			}
		} catch (IOException e) {
			logger.error("error while reading csv and put to db : " + e.getMessage());
		}
		model.addAttribute("bulkUploadMessage", "File Processed, Number of rows Processed :" + count);
		logger.info("Number of rows Processed " + count);
		return "bulkUploadComplete";
	}

	@RequestMapping(value = "/downloadQuestCSVFile.do")
	public void downloadQuestCSVFile(HttpServletResponse response) {
		logger.info("Entering downloadQuestCSVFile method");
		File file = new File(getClass().getClassLoader().getResource(QUEST_CSV_FILE).getFile());
		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream;
			try {
				outputStream = response.getOutputStream();
				outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@RequestMapping(value = "/downloadPersnCSVFile.do")
	public void downloadPersnCSVFile(HttpServletResponse response) {
		logger.info("Entering downloadPersnCSVFile method");
		File file = new File(getClass().getClassLoader().getResource(PERSON_CSV_FILE).getFile());
		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream;
			try {
				outputStream = response.getOutputStream();
				outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
}
