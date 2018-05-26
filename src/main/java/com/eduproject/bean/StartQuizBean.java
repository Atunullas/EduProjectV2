package com.eduproject.bean;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.eduproject.dto.QuestionDTO;

@Component
public class StartQuizBean {

	private Map<Integer, QuestionDTO> quizQuestions;

	private int totalQuizQues;

	private int curQuesNo;

	private int questId;

	private int pointsAwarded;

	private Map<Integer, String> userResponse;

	public int getPointsAwarded() {
		return pointsAwarded;
	}

	public void setPointsAwarded(int pointsAwarded) {
		this.pointsAwarded = pointsAwarded;
	}

	public int getQuestId() {
		return questId;
	}

	public void setQuestId(int questId) {
		this.questId = questId;
	}

	public int getCurQuesNo() {
		return curQuesNo;
	}

	public void setCurQuesNo(int curQuesNo) {
		this.curQuesNo = curQuesNo;
	}

	public int getTotalQuizQues() {
		return totalQuizQues;
	}

	public void setTotalQuizQues(int totalQuizQues) {
		this.totalQuizQues = totalQuizQues;
	}

	public Map<Integer, String> getUserResponse() {
		return userResponse;
	}

	public void setUserResponse(Map<Integer, String> userResponse) {
		this.userResponse = userResponse;
	}

	public Map<Integer, QuestionDTO> getQuizQuestions() {
		return quizQuestions;
	}

	public void setQuizQuestions(Map<Integer, QuestionDTO> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}
}
