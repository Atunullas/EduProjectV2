package com.eduproject.model;

import java.io.Serializable;
import java.sql.Blob;
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

	@Column(name = "PERSON_AGE", nullable = false)
	private Integer personAge;

	@Column(name = "PERSON_DOB", nullable = false)
	private Date personDOB;

	@Column(name = "PERSON_ALIVE", nullable = false)
	private Boolean personAlive;

	@Column(name = "PERSON_DOD")
	private Date personDOD;

	@Column(name = "PERSON_ABOUT")
	private String personAbout;

	@Column(name = "PERSON_PIC")
	private Blob personPic;

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

	public Blob getPersonPic() {
		return personPic;
	}

	public void setPersonPic(Blob personPic) {
		this.personPic = personPic;
	}

}