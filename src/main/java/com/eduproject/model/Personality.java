package com.eduproject.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONALITY")
public class Personality implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "PERSON_NAME", nullable = false)
	private String personName;

	@Column(name = "PERSON_SEX", nullable = false)
	private String personGender;

	@Column(name = "PERSON_DOB", nullable = false)
	private Date personDOB;

	@Column(name = "PERSON_DOE")
	private Date personDOE;

	@Column(name = "PERSON_ABOUT")
	private String personAbout;

	@Column(name = "PERSON_PIC")
	private String personPic;

	public Integer getId() {
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

	public String getPersonPic() {
		return personPic;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setPersonPic(String personPic) {
		this.personPic = personPic;
	}

}