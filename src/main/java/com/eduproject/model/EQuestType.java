package com.eduproject.model;

import java.io.Serializable;

public enum EQuestType implements Serializable {
	BEST_ANS("BEST_ANS"), MUL_ANS("MUL_ANS"), TRUE_FALSE("TRUE_FALSE");

	String questType;

	private EQuestType(String type) {
		this.questType = type;
	}

	public String getUserProfileType() {
		return questType;
	}

}
