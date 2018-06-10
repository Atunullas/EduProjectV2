package com.eduproject.dto;

public class OptionDTO {

	private Long optionId;

	private String optionTxt;

	private String isAns;

	private QuestionDTO quest;

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
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

	public QuestionDTO getQuest() {
		return quest;
	}

	public void setQuest(QuestionDTO quest) {
		this.quest = quest;
	}

	@Override
	public String toString() {
		return "OptionDTO [optionId=" + optionId + ", optionTxt=" + optionTxt + ", isAns=" + isAns + ", quest=" + quest
				+ "]";
	}

}
