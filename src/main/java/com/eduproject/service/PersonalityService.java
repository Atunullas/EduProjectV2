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

@Service
@Transactional
public class PersonalityService {

	private static final Logger logger = LoggerFactory.getLogger(PersonalityService.class);

	@Autowired
	private PersonalityDao personalityDao;

	public void performSave(PersonalityDTO dto) {
		logger.info("Entering performSave method");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Personality model = new Personality();
		model.setPersonName(dto.getFirstName() + " " + dto.getLastName());
		try {
			if (dto.getPersonDOB() != null) {
				model.setPersonDOB(sdf.parse(dto.getPersonDOB()));
			}
			if (StringUtils.isEmpty(dto.getPersonDOE() != null)) {
				model.setPersonDOE(sdf.parse(dto.getPersonDOE()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
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
		logger.info("Persisting Personality to DB : " + model);
		personalityDao.performSave(model);
		logger.info("Exiting performSave method");
	}

	public List<PersonalityDTO> performFetchAll() {
		logger.info("Entering performFetchAll method");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Personality> result = personalityDao.performFetchAll();
		logger.info("Number of Personalites fetched from DB : " + result.size());
		List<PersonalityDTO> dtos = new ArrayList<>();
		for (Personality pers : result) {
			PersonalityDTO dto = new PersonalityDTO();
			dto.setPersonName(pers.getPersonName());
			if (!StringUtils.isEmpty(pers.getPersonDOB()))
				dto.setPersonDOB(sdf.format(pers.getPersonDOB()));
			dto.setPersonGender(pers.getPersonGender());
			if (!StringUtils.isEmpty(pers.getPersonDOE()))
				dto.setPersonDOE(sdf.format(pers.getPersonDOE()));
			dto.setPersonAbout(pers.getPersonAbout());
			dto.setBytePersonPic(pers.getPersonPic());
			dtos.add(dto);
		}
		logger.info("Exiting performFetchAll method");
		return dtos;
	}
	
	public List<PersonalityDTO> performFetchWithLimit(Integer limit) {
		logger.info("Entering performFetchAll method");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Personality> result = personalityDao.performFetchWithLimit(limit);
		logger.info("Number of Personalites fetched from DB : " + result.size());
		List<PersonalityDTO> dtos = new ArrayList<>();
		for (Personality pers : result) {
			PersonalityDTO dto = new PersonalityDTO();
			dto.setPersonName(pers.getPersonName());
			if (!StringUtils.isEmpty(pers.getPersonDOB()))
				dto.setPersonDOB(sdf.format(pers.getPersonDOB()));
			dto.setPersonGender(pers.getPersonGender());
			if (!StringUtils.isEmpty(pers.getPersonDOE()))
				dto.setPersonDOE(sdf.format(pers.getPersonDOE()));
			dto.setPersonAbout(pers.getPersonAbout());
			dto.setBytePersonPic(pers.getPersonPic());
			dtos.add(dto);
		}
		logger.info("Exiting performFetchAll method");
		return dtos;
	}
}
