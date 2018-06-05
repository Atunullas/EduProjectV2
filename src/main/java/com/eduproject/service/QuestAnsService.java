package com.eduproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduproject.dao.QuestAnsDao;
import com.eduproject.dto.OptionDTO;
import com.eduproject.dto.QuestionDTO;
import com.eduproject.model.Option;
import com.eduproject.model.Question;

@Service
@Transactional
public class QuestAnsService {

	private static final Logger logger = LoggerFactory.getLogger(QuestAnsService.class);

	@Autowired
	private QuestAnsDao questAnsDao;

	public void performSave(QuestionDTO dto) {
		logger.info("Entering performSave method");
		Question question = new Question();
		question.setQuestionId(dto.getQuestionId());
		question.setQuestionTxt(dto.getQuestionTxt());
		question.setQuestionType(dto.getQuestionType());
		List<Option> options = new ArrayList<>();
		for (OptionDTO optDto : dto.getOptions()) {
			Option option = new Option();
			option.setOptionId(optDto.getOptionId());
			option.setOptionText(optDto.getOptionTxt());
			option.setIsAns(optDto.getIsAns());
			options.add(option);
		}
		question.setOptions(options);
		logger.info("Persisting question to Database" + question);
		questAnsDao.performSave(question);
		logger.info("Exiting performSave method");
	}

	public QuestionDTO performFetch(Integer questId) {
		logger.info("Entering performFetch method by question Id " + questId);
		return questAnsDao.performFetchById(questId);
	}

	public Map<Integer, QuestionDTO> performFetchAll() {
		logger.info("Entering performFetchAll method");
		List<Question> questAnsList = questAnsDao.performFetchAll();
		logger.info("Number of Questions fetched from DB : " + questAnsList.size());
		Map<Integer, QuestionDTO> quizDTOList = new HashMap<>();
		int index = 0;
		for (Question ques : questAnsList) {
			QuestionDTO quizQuest = new QuestionDTO();
			List<OptionDTO> optionList = new ArrayList<>();
			quizQuest.setQuestionId(ques.getQuestionId());
			quizQuest.setQuestionTxt(ques.getQuestionTxt());
			quizQuest.setQuestionType(ques.getQuestionType());
			for (Option opt : ques.getOptions()) {
				OptionDTO option = new OptionDTO();
				option.setOptionId(opt.getOptionId());
				option.setOptionTxt(opt.getOptionText());
				option.setIsAns(opt.getIsAns());
				optionList.add(option);
			}
			quizQuest.setOptions(optionList);
			quizDTOList.put(index++, quizQuest);
		}
		logger.info("Exiting performFetchAll method");
		return quizDTOList;
	}
}
