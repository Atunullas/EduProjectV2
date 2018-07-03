package com.eduproject.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "personality")
public class Personality implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private Long personId;

	@Column(name = "person_firstname", nullable = false)
	private String personFirstName;

	@Column(name = "person_lastname", nullable = false)
	private String personLastName;

	@Column(name = "person_sex", nullable = false)
	private String personGender;

	@Column(name = "person_dob", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date personDOB;

	@Column(name = "person_doe")
	@Temporal(TemporalType.TIMESTAMP)
	private Date personDOE;

	@Column(name = "person_about")
	private String personAbout;

	@Lob
	@Column(name = "person_pic")
	private byte[] personPic;

	@ManyToOne(cascade = CascadeType.ALL)
	private Subject personSubject;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getPersonFirstName() {
		return personFirstName;
	}

	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}

	public String getPersonLastName() {
		return personLastName;
	}

	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
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

	public Subject getPersonSubject() {
		return personSubject;
	}

	public void setPersonSubject(Subject personSubject) {
		this.personSubject = personSubject;
	}

	@Override
	public String toString() {
		return "Personality [personId=" + personId + ", personFirstName=" + personFirstName + ", personLastName="
				+ personLastName + ", personGender=" + personGender + ", personDOB=" + personDOB + ", personDOE="
				+ personDOE + ", personAbout=" + personAbout + ", personPic=" + Arrays.toString(personPic)
				+ ", personSubject=" + personSubject + "]";
	}

}