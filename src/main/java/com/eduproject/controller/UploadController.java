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
import com.eduproject.model.Subject;
import com.eduproject.service.PersonalityService;
import com.eduproject.service.QuestAnsService;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	private static final String upldQuestionHeader = "QUESTION,OPTION_1,OPTION_2,OPTION_3,OPTION_4,ANSWER,QUESTION_TYPE,QUESTION_SUBJECT";

	private static final String upldPersonalityHeader = "SUBJECT_NAME";

	private static final String PERSON_CSV_FILE = "csvTemplates/uploadPersonalityCSVTemplate.csv";

	private static final String QUEST_CSV_FILE = "csvTemplates/uploadQuestionCSVTemplate.csv";
	
	private static final String SUBJECT_CSV_FILE = "csvTemplates/uploadSubjectCSVTemplate.csv";

	@Autowired
	private QuestAnsService questAnsService;

	@Autowired
	private PersonalityService personalityService;

	@RequestMapping(value = "/uploadQuest.do")
	public String uploadQuest(Model model) {
		logger.info("Entering uploadQuest method");
		model.addAttribute("allSubjects", questAnsService.performFetchAllSubjects());
		return "uploadQuestForm";
	}

	@RequestMapping(value = "/saveUploadQuest.do")
	public String saveUploadQuest(QuestionDTO dto, HttpServletRequest request, Model model) {
		logger.info("Entering saveUploadQuest method");

		String view = "uploadQuestForm";
		String[] ansOpts = request.getParameterValues("isAns");
		String subject = request.getParameter("quesSubject");
		Subject subjectObj = new Subject();
		subjectObj.setSubjectName(subject);
		if (null != ansOpts) {
			String[] optionTxts = request.getParameterValues("optionTxt");
			if (!StringUtils.isEmpty(dto.getQuestionType())
					&& dto.getQuestionType().equals(EQuestType.TRUE_FALSE.name())) {
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
				dto.setQuestionSubject(subjectObj);
				questAnsService.performSave(dto);
			} else {
				model.addAttribute("errorMessage", "Invalid Options chosen !");
				model.addAttribute("showClose", true);
				view = "error";
			}
		} else {
			model.addAttribute("errorMessage", "Invalid Options chosen !");
			model.addAttribute("showClose", true);
			view = "error";
		}
		model.addAttribute("allSubjects", questAnsService.performFetchAllSubjects());
		return view;
	}

	@RequestMapping(value = "/uploadPerson.do")
	public String uploadPerson(Model model) {
		logger.info("Entering uploadPerson method");
		model.addAttribute("allSubjects", questAnsService.performFetchAllSubjects());
		return "uploadPersonForm";
	}

	@RequestMapping(value = "/savePersonality.do")
	public String saveUploadPersonality(Model model, PersonalityDTO dto) {
		logger.info("Entering saveUploadPersonality method");
		personalityService.performSave(dto, false);
		model.addAttribute("allSubjects", questAnsService.performFetchAllSubjects());
		return "uploadPersonForm";
	}

	@RequestMapping(value = "/printPerson.pdf")
	public String printPerson(HttpServletRequest request, Model model) {
		logger.info("Entering printQuest method");
		try {
			String limitStr = request.getParameter("count");
			String subject = request.getParameter("subject");
			model.addAttribute("personalities",
					personalityService.performFetchWithLimit(Long.valueOf(limitStr), subject));
		} catch (NumberFormatException e) {
			model.addAttribute("errorMessage", "Invalid Count not a number!");
			model.addAttribute("showClose", true);
			logger.info("Number Format Exception occured not a valid number entered");
			return "error";
		}
		return "printPerson";
	}

	@RequestMapping(value = "/printQuest.pdf")
	public String printQuest(HttpServletRequest request, Model model) {
		logger.info("Entering printQuest method");
		try {
			String limitStr = request.getParameter("count");
			String subject = request.getParameter("subject");
			model.addAttribute("questions", questAnsService.performFetchWithLimit(Long.valueOf(limitStr), subject));
		} catch (NumberFormatException e) {
			model.addAttribute("errorMessage", "Invalid Count not a number!");
			model.addAttribute("showClose", true);
			logger.info("Number Format Exception occured not a valid number entered");
			return "error";
		}
		return "printQuest";
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
					String[] fieldArray = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
					QuestionDTO quesDTO = new QuestionDTO();
					quesDTO.setQuestionTxt(fieldArray[0]);

					// Setting up options
					List<OptionDTO> optDTOs = new ArrayList<>();
					quesDTO.setQuestionType(fieldArray[6]);
					Subject subject = new Subject();
					subject.setSubjectName(fieldArray[7]);
					quesDTO.setQuestionSubject(subject);
					int optionCount = 4;
					if (EQuestType.TRUE_FALSE.name().equals(quesDTO.getQuestionType())) {
						optionCount = 2;
					}

					fieldArray[5] = fieldArray[5].replaceAll("\"", "");
					for (int i = 1; i <= optionCount; i++) {
						OptionDTO optDTO = new OptionDTO();
						optDTO.setOptionTxt(fieldArray[i]);
						if (fieldArray[5].contains("" + i)) {
							optDTO.setIsAns("Y");
						} else {
							optDTO.setIsAns("N");
						}
						optDTOs.add(optDTO);
						logger.info("Iterating through each options for question -" + optDTO);
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
					if (!line.equalsIgnoreCase(upldQuestionHeader)) {
						model.addAttribute("errorMessage", "Error Occurred : Invalid File Type uploaded");
						logger.error("Error Occurred : Invalid File Type uploaded");
						return "error";
					}
				}
				isHeader = false;
			}
		} catch (NumberFormatException |

				IOException e) {
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
		int count = 0;
		try {
			String line;
			InputStream is = csvFile.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			boolean isHeader = true;
			while ((line = br.readLine()) != null) {
				logger.info("line values -" + line);
				if (!isHeader) {
					String[] fieldArray = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
					PersonalityDTO persDTO = new PersonalityDTO();
					persDTO.setFirstName(fieldArray[0]);
					persDTO.setLastName(fieldArray[1]);
					persDTO.setPersonGender(fieldArray[2]);
					persDTO.setPersonDOB(fieldArray[3]);
					persDTO.setPersonDOE(fieldArray[4]);
					persDTO.setPersonAbout(fieldArray[5]);
					Subject subject = new Subject();
					subject.setSubjectName(fieldArray[6]);
					persDTO.setPersonSubject(subject);
					logger.info("Saving value to Personality Table -" + line);
					personalityService.performSave(persDTO, true);
					++count;
				} else {
					// Check Header for the uploaded CSV
					if (!line.equalsIgnoreCase(upldPersonalityHeader)) {
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

	@RequestMapping(value = "/bulkUploadSubject.do")
	public String bulkUploadSubject(@RequestParam("csvFile") MultipartFile csvFile, Model model) {
		logger.info("Entering bulkUploadSubject method");
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
					Subject subject = new Subject();
					subject.setSubjectName(line.toUpperCase());
					logger.info("Saving value to Subject Table -" + line);
					questAnsService.performSubjectSave(subject);
					++count;
				} else {
					// Check Header for the uploaded CSV
					if (!line.equalsIgnoreCase(upldPersonalityHeader)) {
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
	
	@RequestMapping(value = "/downloadSubjectCSVFile.do")
	public void downloadSubjectCSVFile(HttpServletResponse response) {
		logger.info("Entering downloadSubjectCSVFile method");
		File file = new File(getClass().getClassLoader().getResource(SUBJECT_CSV_FILE).getFile());
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
