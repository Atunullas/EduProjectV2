package com.eduproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduproject.bean.StartQuizBean;
import com.eduproject.dto.QuizDTO;
import com.eduproject.model.EQuestType;
import com.eduproject.service.QuestAnsService;

@Controller
public class QuestController {

	@Autowired
	private QuestAnsService questAnsService;

	@Autowired
	private StartQuizBean quizBean;

	@RequestMapping(value = "/setQuestData.do")
	public String setQuizData(Model model) {
		String view = "quizDisclaimer";
		List<QuizDTO> quizQuestions = new ArrayList<QuizDTO>();
		List<QuizDTO> userResponse = new ArrayList<QuizDTO>();
		if (quizQuestions.size() == 0) {
			quizQuestions.addAll(questAnsService.performFetchAll());
			quizBean.setTotalQuizQues(quizQuestions.size());
		}
		quizBean.setQuizQuestions(quizQuestions);
		for (QuizDTO dto : quizQuestions) {
			QuizDTO temp = new QuizDTO();
			temp.setId(dto.getId());
			temp.setQuestion(dto.getQuestion());
			temp.setOptionA(dto.getOptionA());
			temp.setOptionB(dto.getOptionB());
			temp.setOptionC(dto.getOptionC());
			temp.setOptionD(dto.getOptionD());
			temp.setQuestType(dto.getQuestType());
			userResponse.add(temp);
		}
		quizBean.setUserResponse(userResponse);
		quizBean.setCurQuesNo(0);
		quizBean.setPointsAwarded(0);
		Map<Integer, Integer> questionMap = new HashMap<>();
		int i = 1;

		for (QuizDTO dto : quizQuestions) {
			questionMap.put(i++, dto.getId());
		}
		quizBean.setQuestionMap(questionMap);
		model.addAttribute("totQuests", quizBean.getTotalQuizQues());
		return view;
	}

	@RequestMapping(value = "/startQuest.do")
	public String startQuest(Model model) {
		String view = "error";
		int curQuestNo = quizBean.getCurQuesNo();
		model.addAttribute("curQuestNo", ++curQuestNo);
		model.addAttribute("totalQuizQues", quizBean.getTotalQuizQues());
		model.addAttribute("isFirst", true);
		QuizDTO question = questAnsService.performFetch(quizBean.getQuestionMap().get(curQuestNo));
		view = viewQuestion(question, view, model, curQuestNo);
		return view;
	}

	@RequestMapping(value = "/nextQuest.do")
	public String nextQuest(HttpServletRequest request, Model model) {
		String view = "quizComplete";
		int curQuestNo = quizBean.getCurQuesNo();
		String userAns = request.getParameter("selOpt");
		model.addAttribute("totalQuizQues", quizBean.getTotalQuizQues());
		model.addAttribute("isFirst", false);
		if (quizBean.getUserResponse().size() >= curQuestNo) {
			quizBean.getUserResponse().get(curQuestNo - 1).setAnswer(userAns);
			if (null != userAns
					&& userAns.equalsIgnoreCase(quizBean.getQuizQuestions().get(curQuestNo - 1).getAnswer())) {
				int currentScore = quizBean.getPointsAwarded();
				quizBean.setPointsAwarded(++currentScore);
			}
		}
		if (null != quizBean.getQuestionMap().get(++curQuestNo)) {
			model.addAttribute("curQuestNo", curQuestNo);
			QuizDTO question = questAnsService.performFetch(quizBean.getQuestionMap().get(curQuestNo));
			view = viewQuestion(question, view, model, curQuestNo);
		}
		model.addAttribute("score", quizBean.getPointsAwarded());
		return view;
	}

	@RequestMapping(value = "/prevQuest.do")
	public String prevQuest(Model model) {
		String view = "";
		int curQuestNo = quizBean.getCurQuesNo();
		model.addAttribute("totalQuizQues", quizBean.getTotalQuizQues());
		model.addAttribute("isFirst", --curQuestNo == 1 ? true : false);
		model.addAttribute("curQuestNo", curQuestNo);
		QuizDTO question = null;
		if (curQuestNo != 0) {
			question = quizBean.getUserResponse().get(curQuestNo - 1);
		} else {
			question = quizBean.getUserResponse().get(0);
		}
		model.addAttribute("prevAns", question.getAnswer());
		view = viewQuestion(question, view, model, curQuestNo);
		return view;
	}

	private String viewQuestion(QuizDTO question, String view, Model model, int curQuestNo) {
		if (question.getId() != null) {
			if (EQuestType.MUL_ANS.name().equals(question.getQuestType())) {
				view = "MULQuizTemplate";
			} else if (EQuestType.BEST_ANS.name().equals(question.getQuestType())) {
				view = "BESTANSQuizTemplate";
			} else if (EQuestType.TRUE_FALSE.name().equals(question.getQuestType())) {
				view = "TFQuizTemplate";
			}
			model.addAttribute("questions", question);
			quizBean.setCurQuesNo(curQuestNo);
		}
		return view;
	}
}
