package com.eduproject.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class PersonalityDTO {

	private int personId;

	private String firstName;

	private String lastName;

	private String personName;

	private int personAge;

	private String personGender;

	private Date personDOB;

	private Date personDOE;

	private String personAbout;

	private MultipartFile personPic;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getPersonAge() {
		return personAge;
	}

	public void setPersonAge(int personAge) {
		this.personAge = personAge;
	}

	public String getPersonGender() {
		return personGender;
	}

	public void setPersonGender(String personGender) {
		this.personGender = personGender;
	}

	public Date getPersonDOB() {
		return personDOB;
	}

	public void setPersonDOB(Date personDOB) {
		this.personDOB = personDOB;
	}

	public Date getPersonDOE() {
		return personDOE;
	}

	public void setPersonDOE(Date personDOE) {
		this.personDOE = personDOE;
	}

	public String getPersonAbout() {
		return personAbout;
	}

	public void setPersonAbout(String personAbout) {
		this.personAbout = personAbout;
	}

	public MultipartFile getPersonPic() {
		return personPic;
	}

	public void setPersonPic(MultipartFile personPic) {
		this.personPic = personPic;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
