package com.eduproject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question_options")
public class Option implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id")
	private Long optionId;

	@Column(name = "option_txt", nullable = false)
	private String optionText;

	@Column(name = "option_is_ans")
	private String isAns;

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public String getIsAns() {
		return isAns;
	}

	public void setIsAns(String isAns) {
		this.isAns = isAns;
	}

	@Override
	public String toString() {
		return "Option [optionId=" + optionId + ", optionText=" + optionText + ", isAns=" + isAns + ", question=" + "]";
	}

}