package com.eduproject.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class PersonalityDTO {

	private Integer id;

	private String personName;

	private Integer personAge;

	private Date personDOB;

	private Boolean personAlive;

	private Date personDOD;

	private String personAbout;

	private MultipartFile personPic;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getPersonAge() {
		return personAge;
	}

	public void setPersonAge(Integer personAge) {
		this.personAge = personAge;
	}

	public Date getPersonDOB() {
		return personDOB;
	}

	public void setPersonDOB(Date personDOB) {
		this.personDOB = personDOB;
	}

	public Boolean getPersonAlive() {
		return personAlive;
	}

	public void setPersonAlive(Boolean personAlive) {
		this.personAlive = personAlive;
	}

	public Date getPersonDOD() {
		return personDOD;
	}

	public void setPersonDOD(Date personDOD) {
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
