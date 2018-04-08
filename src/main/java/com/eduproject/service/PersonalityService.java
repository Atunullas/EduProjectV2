package com.eduproject.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eduproject.dao.PersonalityDao;
import com.eduproject.dto.PersonalityDTO;
import com.eduproject.form.UploadPersonalityForm;
import com.eduproject.model.Personality;

@Service
public class PersonalityService {

	@Autowired
	private PersonalityDao personalityDao;

	public void performSave(UploadPersonalityForm form) {
		Personality model = new Personality();
		model.setPersonName(form.getFirstName() + " " + form.getLastName());
		if (form.getPersonDOB() != null) {
			model.setPersonDOB(Date.valueOf(form.getPersonDOB()));
		}
		if (StringUtils.isEmpty(form.getPersonDOE() != null)) {
			model.setPersonDOE(Date.valueOf(form.getPersonDOE()));
		}
		model.setPersonGender(form.getPersonGender());
		model.setPersonAbout(form.getPersonAbout());
		model.setPersonPic(form.getPersonPic());
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
			person.setPersonPic(pers.getPersonPic());
			dtos.add(person);
		}
		return dtos;
	}
}
