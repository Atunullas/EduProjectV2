package com.eduproject.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "personality")
public class Personality implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private Integer personId;

	@Column(name = "person_name", nullable = false)
	private String personName;

	@Column(name = "person_sex", nullable = false)
	private String personGender;

	@Column(name = "person_dob", nullable = false)
	private Date personDOB;

	@Column(name = "person_doe")
	private Date personDOE;

	@Column(name = "person_about")
	private String personAbout;

	@Lob
	@Column(name = "person_pic")
	private byte[] personPic;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
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

	public byte[] getPersonPic() {
		return personPic;
	}

	public void setPersonPic(byte[] personPic) {
		this.personPic = personPic;
	}

	@Override
	public String toString() {
		return "Personality [personId=" + personId + ", personName=" + personName + ", personGender=" + personGender
				+ ", personDOB=" + personDOB + ", personDOE=" + personDOE + ", personAbout=" + personAbout
				+ ", personPic=" + personPic + "]";
	}

}