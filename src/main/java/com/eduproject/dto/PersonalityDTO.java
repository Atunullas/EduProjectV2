package com.eduproject.dto;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class PersonalityDTO {

	private Long personId;

	private String firstName;

	private String lastName;

	private String personName;

	private int personAge;

	private String personGender;

	private String personDOB;

	private String personDOE;

	private String personAbout;

	private MultipartFile personPic;

	private byte[] bytePersonPic;

	private String personSubject;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
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

	public String getPersonDOB() {
		return personDOB;
	}

	public void setPersonDOB(String personDOB) {
		this.personDOB = personDOB;
	}

	public String getPersonDOE() {
		return personDOE;
	}

	public void setPersonDOE(String personDOE) {
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

	public byte[] getBytePersonPic() {
		return bytePersonPic;
	}

	public void setBytePersonPic(byte[] bytePersonPic) {
		this.bytePersonPic = bytePersonPic;
	}

	public String getPersonSubject() {
		return personSubject;
	}

	public void setPersonSubject(String personSubject) {
		this.personSubject = personSubject;
	}

	@Override
	public String toString() {
		return "PersonalityDTO [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", personName=" + personName + ", personAge=" + personAge + ", personGender=" + personGender
				+ ", personDOB=" + personDOB + ", personDOE=" + personDOE + ", personAbout=" + personAbout
				+ ", personPic=" + personPic + ", bytePersonPic=" + Arrays.toString(bytePersonPic) + ", personSubject="
				+ personSubject + "]";
	}

}
