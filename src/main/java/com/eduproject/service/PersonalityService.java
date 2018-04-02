package com.eduproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		model.setPersonAbout(form.getPersonAbout());
		model.setPersonAge(form.getPersonAge());
		model.setPersonAlive(form.getPersonAlive());
		model.setPersonDOB(form.getPersonDOB());
		model.setPersonDOD(form.getPersonDOD());
		model.setPersonName(form.getPersonName());
		model.setPersonPic(form.getPersonPic());
		personalityDao.performSave(model);
	}

	public PersonalityDTO performFetch(Integer personId) {
		List<Personality> result = personalityDao.performFetch(personId);
		PersonalityDTO person = new PersonalityDTO();
		for (Personality pers : result) {
			person.setId(pers.getId());
			person.setPersonAbout(pers.getPersonAbout());
			person.setPersonAge(pers.getPersonAge());
			person.setPersonAlive(pers.getPersonAlive());
			person.setPersonDOB(pers.getPersonDOB());
			person.setPersonName(pers.getPersonName());
			person.setPersonDOD(pers.getPersonDOD());
			person.setPersonPic(pers.getPersonPic());
		}
		return person;
	}
}
