package com.eduproject.dto;

import java.util.List;

import com.eduproject.model.Subject;

public class QuestionDTO {

	private Long questionId;

	private String questionTxt;

	private String questionType;

	private Subject questionSubject;

	private List<OptionDTO> options;

	private boolean scored;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
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

	public List<OptionDTO> getOptions() {
		return options;
	}

	public void setOptions(List<OptionDTO> options) {
		this.options = options;
	}

	public boolean isScored() {
		return scored;
	}

	public void setScored(boolean scored) {
		this.scored = scored;
	}

	public Subject getQuestionSubject() {
		return questionSubject;
	}

	public void setQuestionSubject(Subject questionSubject) {
		this.questionSubject = questionSubject;
	}

	@Override
	public String toString() {
		return "QuestionDTO [questionId=" + questionId + ", questionTxt=" + questionTxt + ", questionType="
				+ questionType + ", questionSubject=" + questionSubject + ", options=" + options + ", scored=" + scored
				+ "]";
	}

}
