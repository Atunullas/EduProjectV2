package com.eduproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduproject.dto.QuizDTO;
import com.eduproject.model.EQuestType;
import com.eduproject.service.QuestAnsService;

@Controller
public class QuestController {

	@Autowired
	private QuestAnsService questAnsService;

	@RequestMapping(value = { "/startQuest.do", "/nextQuest.do" })
	public String startQuest(HttpServletRequest request, Model model) {
		String view = "error";
		String questId = request.getParameter("curQuestionID");
		Integer questNo = 0;
		if (!StringUtils.isEmpty(questId)) {
			questNo = Integer.valueOf(questId);
		}
		model.addAttribute("questionId", ++questNo);
		QuizDTO question = questAnsService.performFetch(questNo);
		if (questNo != 0) {
			if (question.getId() != null) {
				if (EQuestType.MUL_ANS.name().equals(question.getQuestType())) {
					view = "MULQuizTemplate";
				} else if (EQuestType.BEST_ANS.name().equals(question.getQuestType())) {
					view = "BESTANSQuizTemplate";
				} else if (EQuestType.TRUE_FALSE.name().equals(question.getQuestType())) {
					view = "TFQuizTemplate";
				}
				model.addAttribute("questions", question);
			}
			if (question.getId() == null) {
				view = "quizComplete";
			}

		}
		return view;
	}
}
