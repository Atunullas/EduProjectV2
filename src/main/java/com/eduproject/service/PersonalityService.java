package com.eduproject.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.eduproject.dao.PersonalityDao;
import com.eduproject.dto.PersonalityDTO;
import com.eduproject.model.Personality;
import com.eduproject.model.Subject;

@Service
@Transactional
public class PersonalityService {

	private static final Logger logger = LoggerFactory.getLogger(PersonalityService.class);

	@Autowired
	private PersonalityDao personalityDao;

	public void performSave(PersonalityDTO dto) {
		logger.info("Entering performSave method");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Personality model = new Personality();
		if (null != dto.getFirstName()) {
			model.setPersonFirstName(dto.getFirstName());
		}

		if (null != dto.getLastName()) {
			model.setPersonLastName(dto.getLastName());
		}

		try {
			if (dto.getPersonDOB() != null) {
				model.setPersonDOB(sdf.parse(dto.getPersonDOB()));
			}
			if (StringUtils.isEmpty(dto.getPersonDOE() != null)) {
				model.setPersonDOE(sdf.parse(dto.getPersonDOE()));
			}
		} catch (ParseException e) {
			logger.info("Exception occured while parsing the Date : " + e.getMessage());
		}
		model.setPersonGender(dto.getPersonGender());
		model.setPersonAbout(dto.getPersonAbout());
		if (null != dto.getPersonPic()) {
			byte[] content;
			try {
				content = IOUtils.toByteArray(dto.getPersonPic().getInputStream());
				model.setPersonPic(content);
			} catch (IOException e) {
				logger.info("Exception occured while setting the Profile Pic : " + e.getMessage());
			}
		}
		if (dto.getPersonSubject() != null) {
			Subject sub = new Subject();
			sub.setSubjectName(dto.getPersonSubject().getSubjectName().toUpperCase());
			model.setPersonSubject(sub);
		}
		logger.info("Persisting Personality to DB : " + model);
		personalityDao.performSave(model);
		logger.info("Exiting performSave method");
	}

	public List<PersonalityDTO> performFetchAll() {
		logger.info("Entering performFetchAll method");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Personality> result = personalityDao.performFetchAll();
		logger.info("Number of Personalites fetched from DB : " + result.size());
		List<PersonalityDTO> dtos = new ArrayList<>();
		for (Personality pers : result) {
			PersonalityDTO dto = new PersonalityDTO();
			dto.setPersonId(pers.getPersonId());
			dto.setFirstName(pers.getPersonFirstName());
			dto.setLastName(pers.getPersonLastName());
			if (!StringUtils.isEmpty(pers.getPersonDOB()))
				dto.setPersonDOB(sdf.format(pers.getPersonDOB()));
			dto.setPersonGender(pers.getPersonGender());
			if (!StringUtils.isEmpty(pers.getPersonDOE()))
				dto.setPersonDOE(sdf.format(pers.getPersonDOE()));
			dto.setPersonAbout(pers.getPersonAbout());
			dto.setBytePersonPic(pers.getPersonPic());
			dto.setPersonSubject(pers.getPersonSubject());
			dtos.add(dto);
		}
		logger.info("Exiting performFetchAll method");
		return dtos;
	}

	public List<PersonalityDTO> performFetchWithLimit(Long limit, String subject) {
		logger.info("Entering performFetchAll method");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Personality> result = personalityDao.performFetchWithLimit(limit);
		logger.info("Number of Personalites fetched from DB : " + result.size());
		List<PersonalityDTO> dtos = new ArrayList<>();
		for (Personality pers : result) {
			if (pers.getPersonSubject() != null && (subject.equalsIgnoreCase(pers.getPersonSubject().getSubjectName())
					|| "ALL".equalsIgnoreCase(subject))) {
				PersonalityDTO dto = new PersonalityDTO();
				dto.setPersonId(pers.getPersonId());
				dto.setFirstName(pers.getPersonFirstName());
				dto.setLastName(pers.getPersonLastName());
				if (!StringUtils.isEmpty(pers.getPersonDOB()))
					dto.setPersonDOB(sdf.format(pers.getPersonDOB()));
				dto.setPersonGender(pers.getPersonGender());
				if (!StringUtils.isEmpty(pers.getPersonDOE()))
					dto.setPersonDOE(sdf.format(pers.getPersonDOE()));
				dto.setPersonAbout(pers.getPersonAbout());
				dto.setBytePersonPic(pers.getPersonPic());
				dto.setPersonSubject(pers.getPersonSubject());
				dtos.add(dto);
			}
		}
		logger.info("Exiting performFetchAll method");
		return dtos;
	}

	public PersonalityDTO performFetchById(Long valueOf) {
		logger.info("Entering performFetchById method");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Personality person = personalityDao.performFetchById(valueOf);
		PersonalityDTO dto = new PersonalityDTO();
		if (null != person) {
			dto.setPersonId(person.getPersonId());
			dto.setFirstName(person.getPersonFirstName());
			dto.setLastName(person.getPersonLastName());
			if (!StringUtils.isEmpty(person.getPersonDOB()))
				dto.setPersonDOB(sdf.format(person.getPersonDOB()));
			if (!StringUtils.isEmpty(person.getPersonDOE()))
				dto.setPersonDOE(sdf.format(person.getPersonDOE()));
			dto.setPersonGender(person.getPersonGender());
			dto.setPersonAbout(person.getPersonAbout());
			dto.setBytePersonPic(person.getPersonPic());
			dto.setPersonSubject(person.getPersonSubject());
			logger.info("Exiting performFetchById method");
		}
		return dto;
	}

	public void performUpdate(PersonalityDTO dto) {
		logger.info("Entering performUpdate method");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Personality model = new Personality();
		model.setPersonId(dto.getPersonId());
		model.setPersonFirstName(dto.getFirstName());
		model.setPersonLastName(dto.getLastName());
		try {
			if (dto.getPersonDOB() != null) {
				model.setPersonDOB(sdf.parse(dto.getPersonDOB()));
			}
			if (StringUtils.isEmpty(dto.getPersonDOE() != null)) {
				model.setPersonDOE(sdf.parse(dto.getPersonDOE()));
			}
		} catch (ParseException e) {
			logger.info("Exception occured while parsing the Date : " + e.getMessage());
		}
		model.setPersonGender(dto.getPersonGender());
		model.setPersonAbout(dto.getPersonAbout());
		if (null != dto.getPersonPic()) {
			byte[] content;
			try {
				content = IOUtils.toByteArray(dto.getPersonPic().getInputStream());
				model.setPersonPic(content);
			} catch (IOException e) {
				logger.info("Exception occured while setting the Profile Pic : " + e.getMessage());
			}
		}
		if (dto.getPersonSubject() != null) {
			model.setPersonSubject(dto.getPersonSubject());
		}
		logger.info("Updating Personality to DB : " + model);
		personalityDao.performUpdate(model);
		logger.info("Exiting performUpdate method");
	}

	public void performDelete(Long personId) {
		logger.info("Entering performDelete method");
		personalityDao.performDelete(personId);
		logger.info("Exiting performDelete method");
	}
}
