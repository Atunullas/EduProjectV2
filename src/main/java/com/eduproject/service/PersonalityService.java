package com.eduproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eduproject.dao.PersonalityDao;
import com.eduproject.dto.PersonalityDTO;
import com.eduproject.model.Personality;

@Service
public class PersonalityService {

	@Autowired
	private PersonalityDao personalityDao;

	public void performSave(PersonalityDTO dto) {
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
		//model.setPersonPic(dto.getPersonPic());
		personalityDao.performSave(model);
	}

	public List<PersonalityDTO> performFetchAll() {
		List<Personality> result = personalityDao.performFetchAll();
		List<PersonalityDTO> dtos = new ArrayList<>();
		for (Personality pers : result) {
			PersonalityDTO person = new PersonalityDTO();
			person.setPersonName(pers.getPersonName());
			person.setPersonDOB(pers.getPersonDOB());
			person.setPersonGender(pers.getPersonGender());
			person.setPersonDOE(pers.getPersonDOE());
			person.setPersonAbout(pers.getPersonAbout());
		//	person.setPersonPic(pers.getPersonPic());
			dtos.add(person);
		}
		return dtos;
	}
}
