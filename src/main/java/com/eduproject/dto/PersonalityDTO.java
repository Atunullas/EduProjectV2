package com.eduproject.dto;

import java.util.Date;

public class PersonalityDTO {

	private int id;

	private String personName;

	private int personAge;

	private String personGender;

	private Date personDOB;

	private Date personDOE;

	private String personAbout;

	private String personPic;

	public String getPersonPic() {
		return personPic;
	}

	public void setPersonPic(String personPic) {
		this.personPic = personPic;
	}

	public int getId() {
		return id;
	}

	public String getPersonName() {
		return personName;
	}

	public String getPersonGender() {
		return personGender;
	}

	public Date getPersonDOB() {
		return personDOB;
	}

	public Date getPersonDOE() {
		return personDOE;
	}

	public String getPersonAbout() {
		return personAbout;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonAge() {
		return personAge;
	}

	public void setPersonAge(int personAge) {
		this.personAge = personAge;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public void setPersonGender(String personGender) {
		this.personGender = personGender;
	}

	public void setPersonDOB(Date personDOB) {
		this.personDOB = personDOB;
	}

	public void setPersonDOE(Date personDOE) {
		this.personDOE = personDOE;
	}

	public void setPersonAbout(String personAbout) {
		this.personAbout = personAbout;
	}

}
