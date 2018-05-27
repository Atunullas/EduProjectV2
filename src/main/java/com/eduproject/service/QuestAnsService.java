package com.eduproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduproject.dao.QuestAnsDao;
import com.eduproject.dto.OptionDTO;
import com.eduproject.dto.QuestionDTO;
import com.eduproject.model.Option;
import com.eduproject.model.Question;

@Service
public class QuestAnsService {

	@Autowired
	private QuestAnsDao questAnsDao;

	public void performSave(QuestionDTO dto) {
		Question question = new Question();
		question.setQuestionId(dto.getQuestionId());
		question.setQuestionTxt(dto.getQuestionTxt());
		question.setQuestionType(dto.getQuestionType());
		Option option = new Option();
		for (OptionDTO optDto : dto.getOptions()) {
			option.setOptionId(optDto.getOptionId());
			option.setOptionText(optDto.getOptionTxt());
			option.setIsAns(optDto.getIsAns());
		}
		questAnsDao.performSave(question);
	}

	public QuestionDTO performFetch(Integer questId) {
		return questAnsDao.performFetchById(questId);
	}

	public Map<Integer, QuestionDTO> performFetchAll() {
		List<Question> questAnsList = questAnsDao.performFetchAll();
		Map<Integer, QuestionDTO> quizDTOList = new HashMap<>();
		int index = 0;
		for (Question ques : questAnsList) {
			QuestionDTO quizQuest = new QuestionDTO();
			OptionDTO option = new OptionDTO();
			quizQuest.setQuestionId(ques.getQuestionId());
			quizQuest.setQuestionTxt(ques.getQuestionTxt());
			quizQuest.setQuestionType(ques.getQuestionType());
			for (Option opt : ques.getOptions()) {
				option.setOptionId(opt.getOptionId());
				option.setOptionTxt(opt.getOptionText());
				option.setIsAns(opt.getIsAns());
			}
			quizDTOList.put(index++, quizQuest);
		}
		return quizDTOList;
	}
}
