package com.eduproject.form;

import org.springframework.web.multipart.MultipartFile;

public class UploadPersonalityForm {
	private String personName;

	private String personAge;

	private String personDOB;

	private String personAlive;

	private String personDOD;

	private String personAbout;

	private MultipartFile personPic;

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonAge() {
		return personAge;
	}

	public void setPersonAge(String personAge) {
		this.personAge = personAge;
	}

	public String getPersonDOB() {
		return personDOB;
	}

	public void setPersonDOB(String personDOB) {
		this.personDOB = personDOB;
	}

	public String getPersonAlive() {
		return personAlive;
	}

	public void setPersonAlive(String personAlive) {
		this.personAlive = personAlive;
	}

	public String getPersonDOD() {
		return personDOD;
	}

	public void setPersonDOD(String personDOD) {
		this.personDOD = personDOD;
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

}
