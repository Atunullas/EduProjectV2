package com.eduproject.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.eduproject.dto.PersonalityDTO;

@Component
public class PersonalityBean {

	private List<PersonalityDTO> allPersons;
	
	private int curQuestion;

	public int getCurQuestion() {
		return curQuestion;
	}

	public void setCurQuestion(int curQuestion) {
		this.curQuestion = curQuestion;
	}

	public List<PersonalityDTO> getAllPersons() {
		return allPersons;
	}

	public void setAllPersons(List<PersonalityDTO> allPersons) {
		this.allPersons = allPersons;
	}
}
