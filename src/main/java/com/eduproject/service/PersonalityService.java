package com.eduproject.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.eduproject.dao.PersonalityDao;
import com.eduproject.dto.PersonalityDTO;
import com.eduproject.model.Personality;

@Service
@Transactional
public class PersonalityService {

	private static final Logger logger = LoggerFactory.getLogger(PersonalityService.class);

	@Autowired
	private PersonalityDao personalityDao;

	public void performSave(PersonalityDTO dto) {
		logger.info("Entering performSave method");
		Personality model = new Personality();
		model.setPersonName(dto.getFirstName() + " " + dto.getLastName());
		if (dto.getPersonDOB() != null) {
			model.setPersonDOB(dto.getPersonDOB());
		}
		if (StringUtils.isEmpty(dto.getPersonDOE() != null)) {
			model.setPersonDOE(dto.getPersonDOE());
		}
		model.setPersonGender(dto.getPersonGender());
		model.setPersonAbout(dto.getPersonAbout());
		// model.setPersonPic(dto.getPersonPic());
		logger.info("Persisting Personality to DB : " + model);
		personalityDao.performSave(model);
		logger.info("Exiting performSave method");
	}

	public List<PersonalityDTO> performFetchAll() {
		logger.info("Entering performFetchAll method");
		List<Personality> result = personalityDao.performFetchAll();
		logger.info("Number of Personalites fetched from DB : " + result.size());
		List<PersonalityDTO> dtos = new ArrayList<>();
		for (Personality pers : result) {
			PersonalityDTO person = new PersonalityDTO();
			person.setPersonName(pers.getPersonName());
			person.setPersonDOB(pers.getPersonDOB());
			person.setPersonGender(pers.getPersonGender());
			person.setPersonDOE(pers.getPersonDOE());
			person.setPersonAbout(pers.getPersonAbout());
			// person.setPersonPic(pers.getPersonPic());
			dtos.add(person);
		}
		logger.info("Exiting performFetchAll method");
		return dtos;
	}
}
