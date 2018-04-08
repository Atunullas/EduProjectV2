package com.eduproject.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.eduproject.dto.PersonalityDTO;

@Component
public class PersonalityBean {

	private List<PersonalityDTO> allPersons;

	public List<PersonalityDTO> getAllPersons() {
		return allPersons;
	}

	public void setAllPersons(List<PersonalityDTO> allPersons) {
		this.allPersons = allPersons;
	}
}
