package com.eduproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduproject.dao.QuestAnsDao;
import com.eduproject.dto.QuizDTO;
import com.eduproject.form.UploadQuestionForm;
import com.eduproject.model.EQuestType;
import com.eduproject.model.QuestAns;

@Service
public class QuestAnsService {

	@Autowired
	private QuestAnsDao questAnsDao;

	public void performSave(UploadQuestionForm form) {
		QuestAns model = new QuestAns();
		if(EQuestType.TRUE_FALSE.name().equals(form.getQuestType())) {
			model.setQuestion(form.getQuestion());
			model.setQuestType(form.getQuestType());
			model.setOptionA("True");
			model.setOptionB("False");
			model.setAnswer(form.getTruFalAns());
		} else {
			model.setQuestion(form.getQuestion());
			model.setQuestType(form.getQuestType());
			model.setOptionA(form.getOptionA());
			model.setOptionB(form.getOptionB());
			model.setOptionC(form.getOptionC());
			model.setOptionD(form.getOptionD());
			model.setAnswer(form.getAns());
		}
		questAnsDao.performSave(model);
	}

	public QuizDTO performFetch(Integer questId) {
		List<QuestAns> result = questAnsDao.performFetch(questId);
		QuizDTO quiz = new QuizDTO();
		for (QuestAns ques : result) {
			quiz.setId(ques.getId());
			quiz.setOptionA(ques.getOptionA());
			quiz.setOptionB(ques.getOptionB());
			quiz.setOptionC(ques.getOptionC());
			quiz.setOptionD(ques.getOptionD());
			quiz.setQuestion(ques.getQuestion());
			quiz.setQuestType(ques.getQuestType());
		}
		return quiz;
	}

}
