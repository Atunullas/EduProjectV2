package com.eduproject.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduproject.bean.PersonalityBean;
import com.eduproject.dto.PersonalityDTO;
import com.eduproject.service.PersonalityService;

@Controller
public class PersonalityController {

	private static final Logger logger = LoggerFactory.getLogger(PersonalityController.class);

	@Autowired
	private PersonalityService personalityService;

	@Autowired
	private PersonalityBean personBean;

	@RequestMapping(value = "/startPerson.do")
	public String startPerson(HttpServletRequest request, Model model) {
		logger.info("Enter startPerson method");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String view = "error";
		String limit = request.getParameter("count");
		try {
			personBean.setAllPersons(personalityService.performFetchWithLimit(Integer.valueOf(limit)));
		} catch (NumberFormatException e) {
			model.addAttribute("errorMessage", "Invalid Count not a number!");
			logger.info("Number Format Exception occured not a valid number entered");
			return view;
		}
		if (personBean.getAllPersons().size() > 0) {
			personBean.setCurQuestion(1);
			PersonalityDTO personDTO = personBean.getAllPersons().get(0);
			try {
				personDTO.setPersonAge(calculateAge(sdf.parse(personDTO.getPersonDOB())));
			} catch (ParseException e) {
				logger.info("Parse Exception Occured while calculating Age");
				return view;
			}
			model.addAttribute("person", personDTO);
			byte[] encoded = Base64.encodeBase64(personDTO.getBytePersonPic());
			String encodedString = new String(encoded);
			model.addAttribute("image", encodedString);

			model.addAttribute("curPerson", personBean.getCurQuestion());
			model.addAttribute("totPerson", personBean.getAllPersons().size());
			view = "viewPersonality";
		} else {
			model.addAttribute("errorMessage", "No Noble Personalities found, Please upload!");
			model.addAttribute("showClose", true);
			model.addAttribute("isWindowMode", true);
			logger.info("No Noble Personalities found, Please upload!");
			return view;
		}
		model.addAttribute("isWindowMode", true);
		logger.info("Exiting startPerson method");
		model.addAttribute("showClose", true);
		return view;
	}

	@RequestMapping(value = "/nextPerson.do")
	public String nextPerson(HttpServletRequest request, Model model) {
		logger.info("Enter nextPerson method");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String personId = request.getParameter("id");
		String view = "error";
		int id = 1;
		try {
			id = Integer.valueOf(personId);
		} catch (NumberFormatException e) {
			logger.info("NumberFormatException Exception Occured while Parsing the Personality ID" + e.getMessage());
		}
		if (personBean.getAllPersons().size() > id) {
			PersonalityDTO personDTO = personBean.getAllPersons().get(id);
			try {
				personDTO.setPersonAge(calculateAge(sdf.parse(personDTO.getPersonDOB())));
			} catch (ParseException e) {
				return view;
			}
			model.addAttribute("person", personDTO);
			model.addAttribute("curPerson", personBean.getCurQuestion() + 1);
			model.addAttribute("totPerson", personBean.getAllPersons().size());
			view = "viewPersonality";
		} else {
			view = "personalityComplete";
		}
		model.addAttribute("isWindowMode", true);
		logger.info("Exiting nextPerson method");
		return view;
	}

	private int calculateAge(Date dob) {
		logger.info("Enter calculateAge method");
		Calendar cal = Calendar.getInstance();
		cal.setTime(dob);
		Calendar today = Calendar.getInstance();
		int curYear = today.get(Calendar.YEAR);
		int dobYear = cal.get(Calendar.YEAR);
		int age = curYear - dobYear;
		int curMonth = today.get(Calendar.MONTH);
		int dobMonth = cal.get(Calendar.MONTH);
		if (dobMonth > curMonth) {
			age--;
		} else if (dobMonth == curMonth) {
			int curDay = today.get(Calendar.DAY_OF_MONTH);
			int dobDay = cal.get(Calendar.DAY_OF_MONTH);
			if (dobDay > curDay) {
				age--;
			}
		}
		logger.info("Exiting calculateAge method");
		return age;
	}
}
