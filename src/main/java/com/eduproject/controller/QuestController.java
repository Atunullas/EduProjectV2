package com.eduproject.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eduproject.bean.StartQuizBean;
import com.eduproject.dto.QuestionDTO;
import com.eduproject.model.EQuestType;
import com.eduproject.service.QuestAnsService;

@Controller
public class QuestController {

	@Autowired
	private QuestAnsService questAnsService;

	@Autowired
	private StartQuizBean quizBean;

	@RequestMapping(value = "/setQuestData.do", method = RequestMethod.GET)
	public String setQuizData(Model model) {
		String view = "quizDisclaimer";
		Map<Integer, QuestionDTO> quizQuestions = new HashMap<>();
		if (quizQuestions.size() == 0) {
			quizQuestions.putAll(questAnsService.performFetchAll());
			quizBean.setTotalQuizQues(quizQuestions.size());
		}
		quizBean.setQuizQuestions(quizQuestions);
		quizBean.setCurQuesNo(0);
		quizBean.setPointsAwarded(0);
		model.addAttribute("noOfTotalQuizQues", quizBean.getTotalQuizQues());
		return view;
	}

	@RequestMapping(value = "/startQuest.do", method = RequestMethod.POST)
	public String startQuest(Model model) {
		String view = "error";
		int curQuestNo = 0;
		quizBean.setCurQuesNo(curQuestNo);
		model.addAttribute("curQuestNo", curQuestNo);
		model.addAttribute("noOfTotalQuizQues", quizBean.getTotalQuizQues());
		try {
			QuestionDTO question = quizBean.getQuizQuestions().get(curQuestNo);
			view = viewQuestion(question, model, curQuestNo);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return view;
	}

	@RequestMapping(value = "/nextQuest.do")
	public String nextQuest(HttpServletRequest request, Model model) {
		String view = "quizComplete";
		int curQuestNo = quizBean.getCurQuesNo();
		String[] userAns = request.getParameterValues("selOpt");
		String ans = "";
		if (null != userAns) {
			// comma separating the user responses provided
			ans = getSubmittedAns(userAns);
		}

		// Setting submitted user response for the Question to the Bean
		quizBean.getUserResponse().put(curQuestNo, ans);

		if (null != quizBean.getQuizQuestions().get(++curQuestNo)) {
			model.addAttribute("curQuestNo", curQuestNo);
			try {
				view = viewQuestion(quizBean.getQuizQuestions().get(curQuestNo), model, curQuestNo);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				view = "error";
			}
		}
		model.addAttribute("noOfTotalQuizQues", quizBean.getTotalQuizQues());
		if (curQuestNo == 0) {
			model.addAttribute("isFirst", true);
		} else {
			model.addAttribute("isFirst", false);
		}
		model.addAttribute("score", quizBean.getPointsAwarded());
		return view;
	}

	@RequestMapping(value = "/prevQuest.do", method = RequestMethod.POST)
	public String prevQuest(HttpServletRequest request, Model model) {
		String view = "error";
		int curQuestNo = quizBean.getCurQuesNo();
		String[] userAns = request.getParameterValues("selOpt");
		String ans = "";
		if (null != userAns) {
			ans = getSubmittedAns(userAns);
		}
		if (curQuestNo - 1 >= 0) {
			// Persisting previous user response to the question
			quizBean.getUserResponse().put(curQuestNo, ans);

			// Checking for previous question
			if (--curQuestNo == 0) {
				model.addAttribute("isFirst", true);
			} else {
				model.addAttribute("isFirst", false);
			}

			// Fetching previous Question Details
			QuestionDTO question = quizBean.getQuizQuestions().get(curQuestNo);
			model.addAttribute("curQuestNo", curQuestNo);
			model.addAttribute("prevAns", quizBean.getUserResponse().get(curQuestNo));
			view = viewQuestion(question, model, curQuestNo);
		}
		model.addAttribute("noOfTotalQuizQues", quizBean.getTotalQuizQues());
		return view;
	}

	private String viewQuestion(QuestionDTO question, Model model, int curQuestNo) {
		String view = null;
		if (question.getQuestionId() != null) {
			if (EQuestType.MUL_ANS.name().equals(question.getQuestionType())) {
				view = "multipleQuestView";
			} else if (EQuestType.BEST_ANS.name().equals(question.getQuestionType())) {
				view = "bestAnsQuestView";
			} else if (EQuestType.TRUE_FALSE.name().equals(question.getQuestionType())) {
				view = "truFalseQuestView";
			}
			model.addAttribute("questions", question);
			quizBean.setCurQuesNo(curQuestNo);
		}
		return view;
	}

	private String getSubmittedAns(String[] userAns) {
		String ans = "";
		boolean firstIteration = true;
		for (String s : userAns) {
			if (firstIteration) {
				ans = s;
				firstIteration = false;
				continue;
			}
			if (!firstIteration) {
				ans = ans + "," + s;
			}
		}
		return ans;
	}
}
