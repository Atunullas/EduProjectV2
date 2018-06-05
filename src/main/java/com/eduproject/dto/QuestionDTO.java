package com.eduproject.dto;

import java.util.List;

public class QuestionDTO {

	private Integer questionId;

	private String questionTxt;

	private String questionType;

	private List<OptionDTO> options;

	private boolean scored;

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

	@Override
	public String toString() {
		return "QuestionDTO [questionId=" + questionId + ", questionTxt=" + questionTxt + ", questionType="
				+ questionType + ", options=" + options + ", scored=" + scored + "]";
	}

}
