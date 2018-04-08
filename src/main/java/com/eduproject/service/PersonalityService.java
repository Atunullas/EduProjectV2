package com.eduproject.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
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
		model.setPersonAbout(form.getPersonAbout());
		model.setPersonAge(Integer.valueOf(form.getPersonAge()));
		model.setPersonAlive(Boolean.valueOf(form.getPersonAlive()));
		if(form.getPersonDOB()!=null) {
			model.setPersonDOB(Date.valueOf(form.getPersonDOB()));	
		}
		if(StringUtils.isEmpty(form.getPersonDOD()!=null)) {
		model.setPersonDOD(Date.valueOf(form.getPersonDOD()));
		}
		model.setPersonName(form.getPersonName());
		File file = new File("c:\\EduProject\\" + form.getPersonPic());
		if (!file.exists()) {
			try {
				file.createNewFile();
				model.setPersonPic(form.getPersonPic());
			} catch (IOException e) {
				e.getMessage();
			}
		}

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
			File file = new File("c:\\EduProject\\" + pers.getPersonPic());
			if (file.exists()) {
				person.setPersonPic(pers.getPersonPic());
			}
		}
		return person;
	}
}
