package com.eduproject.bean;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eduproject.dto.QuizDTO;

@Component
public class StartQuizBean {

	private List<QuizDTO> quizQuestions;

	private int totalQuizQues;

	private int curQuesNo;

	private int questId;

	private int pointsAwarded;
	
	private List<QuizDTO> userResponse;

	private Map<Integer, Integer> questionMap;
	
	public int getPointsAwarded() {
		return pointsAwarded;
	}

	public void setPointsAwarded(int pointsAwarded) {
		this.pointsAwarded = pointsAwarded;
	}

	public Map<Integer, Integer> getQuestionMap() {
		return questionMap;
	}

	public void setQuestionMap(Map<Integer, Integer> questionMap) {
		this.questionMap = questionMap;
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

	public List<QuizDTO> getUserResponse() {
		return userResponse;
	}

	public void setUserResponse(List<QuizDTO> userResponse) {
		this.userResponse = userResponse;
	}

	public List<QuizDTO> getQuizQuestions() {
		return quizQuestions;
	}

	public void setQuizQuestions(List<QuizDTO> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}
}
