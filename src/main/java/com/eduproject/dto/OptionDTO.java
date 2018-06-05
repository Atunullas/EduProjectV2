package com.eduproject.dto;

public class OptionDTO {

	private Integer optionId;

	private String optionTxt;

	private String isAns;

	private int questionId;

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getOptionTxt() {
		return optionTxt;
	}

	public void setOptionTxt(String optionTxt) {
		this.optionTxt = optionTxt;
	}

	public String getIsAns() {
		return isAns;
	}

	public void setIsAns(String isAns) {
		this.isAns = isAns;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "OptionDTO [optionId=" + optionId + ", optionTxt=" + optionTxt + ", isAns=" + isAns + ", questionId="
				+ questionId + "]";
	}

}
