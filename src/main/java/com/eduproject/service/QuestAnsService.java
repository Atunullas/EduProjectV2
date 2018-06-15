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
import org.springframework.util.StringUtils;

import com.eduproject.dao.QuestAnsDao;
import com.eduproject.dto.OptionDTO;
import com.eduproject.dto.QuestionDTO;
import com.eduproject.model.Option;
import com.eduproject.model.Question;
import com.eduproject.model.Subject;

@Service
@Transactional
public class QuestAnsService {

	private static final Logger logger = LoggerFactory.getLogger(QuestAnsService.class);

	@Autowired
	private QuestAnsDao questAnsDao;

	public void performSave(QuestionDTO dto) {
		logger.info("Entering performSave Question method");
		Question question = new Question();
		question.setQuestionTxt(dto.getQuestionTxt());
		question.setQuestionType(dto.getQuestionType());
		List<Option> options = new ArrayList<>();
		for (OptionDTO optDto : dto.getOptions()) {
			Option option = new Option();
			option.setOptionText(optDto.getOptionTxt());
			option.setIsAns(optDto.getIsAns());
			options.add(option);
		}
		question.setQuestionSubject(dto.getQuestionSubject());
		question.setOptions(options);
		logger.info("Persisting question to Database" + question);
		questAnsDao.performSave(question);
		logger.info("Exiting performSave method");
	}

	public QuestionDTO performFetch(Long questId) {
		logger.info("Entering performFetch method by question Id " + questId);
		Question ques = questAnsDao.performFetchById(questId);
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
		return quizQuest;
	}

	public List<QuestionDTO> performFetchAll() {
		logger.info("Entering performFetchAll method");
		List<Question> questAnsList = questAnsDao.performFetchAll();
		logger.info("Number of Questions fetched from DB : " + questAnsList.size());
		List<QuestionDTO> quizDTOList = new ArrayList<>();
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
			quizQuest.setQuestionSubject(ques.getQuestionSubject());
			quizQuest.setOptions(optionList);
			quizDTOList.add(quizQuest);
		}
		logger.info("Exiting performFetchAll method");
		return quizDTOList;
	}

	public Map<Integer, QuestionDTO> performFetchWithLimit(Long limit, String subject) {
		logger.info("Entering performFetchWithLimit method");
		List<Question> questAnsList = questAnsDao.performFetchWithLimit(limit);
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
			if (!StringUtils.isEmpty(subject) && ques.getQuestionSubject() != null
					&& (subject.equalsIgnoreCase(ques.getQuestionSubject().getSubjectName())
							|| ("ALL".equals(ques.getQuestionSubject().getSubjectName())))) {
				quizQuest.setQuestionSubject(ques.getQuestionSubject());
			}
			quizQuest.setOptions(optionList);
			quizDTOList.put(index++, quizQuest);
		}
		logger.info("Exiting performFetchWithLimit method");
		return quizDTOList;

	}

	public List<QuestionDTO> performFetchListWithLimit(Long limit, String subject) {
		logger.info("Entering performFetchListWithLimit method");
		List<Question> questAnsList = questAnsDao.performFetchWithLimit(limit);
		List<QuestionDTO> result = new ArrayList<>();
		logger.info("Number of Questions fetched from DB : " + questAnsList.size());
		if (!StringUtils.isEmpty(subject)) {
			for (Question ques : questAnsList) {
				if (ques.getQuestionSubject() != null
						&& (subject.equalsIgnoreCase(ques.getQuestionSubject().getSubjectName())
								|| "ALL".equalsIgnoreCase(subject))) {
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
					quizQuest.setQuestionSubject(ques.getQuestionSubject());
					quizQuest.setOptions(optionList);
					result.add(quizQuest);
				}
			}
		}
		logger.info("Exiting performFetchListWithLimit method");
		return result;

	}

	public List<Subject> performFetchAllSubjects() {
		logger.info("Entering performFetchAllSubjects method");
		List<Subject> subjectList = questAnsDao.performFetchAllSubjects();
		logger.info("Exiting performFetchAllSubjects method");
		return subjectList;
	}

	public QuestionDTO performFetchById(Long valueOf) {
		logger.info("Entering performFetchById method");
		Question question = questAnsDao.performFetchById(valueOf);
		QuestionDTO dto = new QuestionDTO();
		List<OptionDTO> optionList = new ArrayList<>();
		if (null != question) {
			dto.setQuestionId(question.getQuestionId());
			dto.setQuestionTxt(question.getQuestionTxt());
			dto.setQuestionType(question.getQuestionType());
			dto.setQuestionSubject(question.getQuestionSubject());
			for (Option opt : question.getOptions()) {
				OptionDTO option = new OptionDTO();
				option.setOptionId(opt.getOptionId());
				option.setOptionTxt(opt.getOptionText());
				option.setIsAns(opt.getIsAns());
				optionList.add(option);
			}
			dto.setOptions(optionList);
		}
		logger.info("Exiting performFetchById method");
		return dto;
	}

	public void performUpdate(QuestionDTO dto) {
		logger.info("Entering performUpdate Question method");
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
		question.setQuestionSubject(dto.getQuestionSubject());
		question.setOptions(options);
		logger.info("Persisting question to Database" + question);
		questAnsDao.performSave(question);
		logger.info("Exiting performUpdate method");
	}
	
	public void performDelete(Long questId) {
		logger.info("Entering performDelete Question method");
		questAnsDao.performDelete(questId);
		logger.info("Exiting performDelete method");
	}

}
