package com.eduproject.form;

import java.sql.Blob;
import java.sql.Date;

public class UploadPersonalityForm {
	private String personName;

	private Integer personAge;

	private Date personDOB;

	private Boolean personAlive;

	private Date personDOD;

	private String personAbout;

	private Blob personPic;

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

	public Blob getPersonPic() {
		return personPic;
	}

	public void setPersonPic(Blob personPic) {
		this.personPic = personPic;
	}

}
