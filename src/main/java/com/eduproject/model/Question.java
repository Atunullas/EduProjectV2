package com.eduproject.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Integer questionId;

	@Column(name = "question_txt", nullable = false)
	private String questionTxt;

	@Column(name = "question_type", nullable = false)
	private String questionType;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question")
	private List<Option> options;

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestionTxt() {
		return questionTxt;
	}

	public void setQuestionTxt(String questionTxt) {
		this.questionTxt = questionTxt;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionTxt=" + questionTxt + ", questionType=" + questionType
				+ ", options=" + options + "]";
	}

}