package com.eduproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eduproject.bean.StartQuizBean;
import com.eduproject.dto.OptionDTO;
import com.eduproject.dto.QuestionDTO;
import com.eduproject.model.EQuestType;
import com.eduproject.model.Subject;
import com.eduproject.service.QuestAnsService;

@Controller
public class QuestController {

	private static final Logger logger = LoggerFactory.getLogger(QuestController.class);

	@Autowired
	private QuestAnsService questAnsService;

	@Autowired
	private StartQuizBean quizBean;

	@RequestMapping(value = "/setQuestData.do", method = RequestMethod.GET)
	public String setQuizData(HttpServletRequest request, Model model) {
		logger.info("Enter setQuizData method");
		String view = "error";
		Map<Integer, QuestionDTO> quizQuestions = new HashMap<>();
		String countStr = request.getParameter("count");
		String subject = request.getParameter("subject");
		if (quizQuestions.size() == 0) {
			logger.info("Initializing the Quiz Questions");
			try {
				quizQuestions.putAll(questAnsService.performFetchWithLimit(Long.valueOf(countStr), subject));
			} catch (NumberFormatException e) {
				model.addAttribute("errorMessage", "Invalid Count not a number!");
				model.addAttribute("showClose", true);
				logger.info("Number Format Exception occured not a valid number entered");
				return view;
			}

			logger.info("Fetched the Quiz Questions from Database");
			quizBean.setQuizQuestions(quizQuestions);
			quizBean.setTotalQuizQues(quizQuestions.size());
		}
		quizBean.setQuizQuestions(quizQuestions);
		quizBean.setUserResponse(new HashMap<Integer, String>());
		quizBean.setCurQuesNo(0);
		quizBean.setPointsAwarded(0);
		model.addAttribute("noOfTotalQuizQues", quizBean.getTotalQuizQues());
		if (quizBean.getTotalQuizQues() == 0) {
			model.addAttribute("isWindowMode", true);
			model.addAttribute("errorMessage", "No questions Found, Please upload to start the Quiz!");
			model.addAttribute("showClose", true);
		} else {
			model.addAttribute("isWindowMode", true);
			view = "quizDisclaimer";
		}
		logger.info("Exiting setQuizData method");
		return view;
	}

	@RequestMapping(value = "/startQuest.do", method = RequestMethod.POST)
	public String startQuest(Model model) {
		logger.info("Enter startQuest method");
		String view = "error";
		int curQuestNo = 0;
		quizBean.setCurQuesNo(curQuestNo);
		model.addAttribute("curQuestNo", curQuestNo);
		model.addAttribute("noOfTotalQuizQues", quizBean.getTotalQuizQues());
		model.addAttribute("isWindowMode", true);
		try {
			QuestionDTO question = quizBean.getQuizQuestions().get(curQuestNo);
			logger.info("Getting from Quiz Bean for Current Question No : " + curQuestNo);
			view = viewQuestion(question, model, curQuestNo);
		} catch (IndexOutOfBoundsException e) {
			logger.info("IndexOutOfBoundsException Occured " + e.getMessage());
		}
		logger.info("Exiting startQuest method");
		return view;
	}

	@RequestMapping(value = "/nextQuest.do")
	public String nextQuest(HttpServletRequest request, Model model) {
		logger.info("Entering nextQuest Method");
		String view = "quizComplete";
		int curQuestNo = quizBean.getCurQuesNo();
		String[] userAns = request.getParameterValues("selOpt");
		String ans = "";
		if (null != userAns) {
			// comma separating the user responses provided
			ans = getSubmittedAns(userAns);
			logger.info("User Selected Answers  " + ans);
			// Setting submitted user response for the Question to the Bean
			quizBean.getUserResponse().put(curQuestNo, ans);
			logger.info("Putting User Response to Quiz Bean for Current Question no :" + curQuestNo);
		}

		if (null != quizBean.getQuizQuestions().get(++curQuestNo)) {
			model.addAttribute("curQuestNo", curQuestNo);
			try {
				view = viewQuestion(quizBean.getQuizQuestions().get(curQuestNo), model, curQuestNo);
			} catch (IndexOutOfBoundsException e) {
				logger.info("IndexOutOfBoundsException Occured " + e.getMessage());
				model.addAttribute("errorMessage", "IndexOutOfBoundsException Occured");
				view = "error";
			}
		}
		model.addAttribute("noOfTotalQuizQues", quizBean.getTotalQuizQues());
		if (curQuestNo == 0) {
			model.addAttribute("isFirst", true);
		} else {
			model.addAttribute("isFirst", false);
		}
		if (view.equals("quizComplete")) {
			calculateScore();
		}
		model.addAttribute("score", quizBean.getPointsAwarded());
		model.addAttribute("prevAns", quizBean.getUserResponse().get(curQuestNo));
		model.addAttribute("isWindowMode", true);
		logger.info("Exiting nextQuest Method");
		return view;
	}

	@RequestMapping(value = "/prevQuest.do", method = RequestMethod.POST)
	public String prevQuest(HttpServletRequest request, Model model) {
		logger.info("Entering prevQuest Method");
		String view = "error";
		int curQuestNo = quizBean.getCurQuesNo();
		String[] userAns = request.getParameterValues("selOpt");
		String ans = "";
		if (null != userAns) {
			ans = getSubmittedAns(userAns);
			logger.info("User Selected Answers " + ans);
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
			logger.info("Getting previous question from Quiz Bean for current Question no: " + curQuestNo);
			model.addAttribute("curQuestNo", curQuestNo);
			model.addAttribute("isWindowMode", true);
			model.addAttribute("prevAns", quizBean.getUserResponse().get(curQuestNo));
			view = viewQuestion(question, model, curQuestNo);
		}
		model.addAttribute("noOfTotalQuizQues", quizBean.getTotalQuizQues());
		logger.info("Total No of Quiz Questions " + quizBean.getTotalQuizQues());
		logger.info("Exiting prevQuest Method");
		return view;
	}

	@RequestMapping(value = "/editQuestView.do", method = RequestMethod.GET)
	public String editQuestView(HttpServletRequest request, Model model) {
		logger.info("Entering editQuestView Method");
		String view = "editQuestView";
		String subject = request.getParameter("subject");
		List<QuestionDTO> allQuestions = questAnsService.performFetchListWithLimit(0L, subject);
		model.addAttribute("subject", subject);
		model.addAttribute("allQuestions", allQuestions);
		logger.info("Exiting editQuestView Method");
		return view;
	}

	@RequestMapping(value = "/editQuestSelect.do", method = RequestMethod.POST)
	public String editQuestSelect(HttpServletRequest request, Model model) {
		logger.info("Entering editQuestSelect Method");
		String idStr = request.getParameter("questionId");
		String subject = request.getParameter("subject");
		String view = "editQuestForm";
		QuestionDTO question = questAnsService.performFetchById(Long.valueOf(idStr));
		List<Subject> allSubjects = questAnsService.performFetchAllSubjects();
		model.addAttribute("question", question);
		model.addAttribute("subject", subject);
		model.addAttribute("allSubjects", allSubjects);
		logger.info("Exiting editQuestSelect Method");
		return view;
	}

	@RequestMapping(value = "/editQuestSave.do", method = RequestMethod.POST)
	public String editQuestSave(Model model, QuestionDTO dto, HttpServletRequest request) {
		logger.info("Entering editQuestSave Method");
		String[] ansOpts = request.getParameterValues("isAns");
		if (null != ansOpts) {
			String[] optionTxts = request.getParameterValues("optionTxt");
			String[] allOptionIds = request.getParameterValues("allOptionIds");
			if (!StringUtils.isEmpty(dto.getQuestionType())
					&& dto.getQuestionType().equals(EQuestType.TRUE_FALSE.name())) {
				optionTxts = new String[2];
				optionTxts[0] = "True";
				optionTxts[1] = "False";
			}
			List<OptionDTO> optDTOs = new ArrayList<>();
			if (optionTxts.length > ansOpts.length) {
				int count = 0;
				for (String x : optionTxts) {
					OptionDTO optDTO = new OptionDTO();
					optDTO.setOptionId(Long.valueOf(allOptionIds[count]));
					optDTO.setOptionTxt(x);
					for (String s : ansOpts) {
						if (allOptionIds[count].equals(s)) {
							optDTO.setIsAns("Y");
							break;
						} else {
							optDTO.setIsAns("N");
						}
					}
					optDTOs.add(optDTO);
					count++;
				}
			}
			String quesSubjectName = request.getParameter("quesSubjectName");
			Subject quesSubject = new Subject();
			quesSubject.setSubjectName(quesSubjectName);
			dto.setOptions(optDTOs);
			dto.setQuestionSubject(quesSubject);
			questAnsService.performUpdate(dto);
		}

		String subject = request.getParameter("subject");
		List<QuestionDTO> allQuestions = questAnsService.performFetchListWithLimit(0L, subject);
		model.addAttribute("allQuestions", allQuestions);
		model.addAttribute("subject", subject);
		logger.info("Exiting editQuestSave Method");
		return "redirect:editQuestView.do?subject=" + subject;
	}

	@RequestMapping(value = "/deleteQuest.do", method = RequestMethod.POST)
	public String deleteQuestConfirm(HttpServletRequest request, Model model) {
		logger.info("Entering deleteQuestConfirm Method");
		String questionIdStr = request.getParameter("questionId");
		questAnsService.performDelete(Long.valueOf(questionIdStr));
		String subject = request.getParameter("subject");
		List<QuestionDTO> allQuestions = questAnsService.performFetchListWithLimit(0L, subject);
		model.addAttribute("allQuestions", allQuestions);
		model.addAttribute("subject", subject);
		logger.info("Exiting deleteQuestConfirm Method");
		return "redirect:editQuestView.do?subject=" + subject;
	}

	private void calculateScore() {
		logger.info("Entering calculateScore Method");
		// Calculate points scored
		List<String> usrRspLst = new ArrayList<>(quizBean.getUserResponse().values());
		List<String> actRspList = new ArrayList<>();
		Set<Integer> questKey = quizBean.getUserResponse().keySet();
		for (Integer i : questKey) {
			String actOpt = "";
			List<OptionDTO> optionList = quizBean.getQuizQuestions().get(i).getOptions();
			int optIndex = 0;
			boolean firstIteration = true;
			for (OptionDTO option : optionList) {
				optIndex++;
				if (option.getIsAns().equals("Y")) {
					if (!firstIteration) {
						actOpt = actOpt + "," + option.getOptionId();
					} else {
						actOpt = actOpt + option.getOptionId();
					}
					firstIteration = false;
				}
				if (optionList.size() == optIndex) {
					actRspList.add(actOpt);
				}
			}
		}
		logger.info("User Selected Options from session" + usrRspLst);
		logger.info("Actual Options from DB " + actRspList);
		for (String usrAns : usrRspLst) {
			if (actRspList.contains(usrAns)) {
				quizBean.setPointsAwarded(quizBean.getPointsAwarded() + 1);
			}
		}
		logger.info("Total Points Awarded for the User : " + quizBean.getPointsAwarded());
		logger.info("Exiting calculateScore Method");
	}

	private String viewQuestion(QuestionDTO question, Model model, int curQuestNo) {
		logger.info("Entering viewQuestion Method");
		String view = null;
		if (question.getQuestionId() != null) {
			logger.info("Question type is :" + question.getQuestionType());
			if (EQuestType.MUL_ANS.name().equals(question.getQuestionType())) {
				view = "multipleQuestView";
			} else if (EQuestType.BEST_ANS.name().equals(question.getQuestionType())) {
				view = "bestAnsQuestView";
			} else if (EQuestType.TRUE_FALSE.name().equals(question.getQuestionType())) {
				view = "truFalseQuestView";
			}
			model.addAttribute("curQuestion", question);
			quizBean.setCurQuesNo(curQuestNo);
			logger.info("Returned View is :" + view);
		}
		logger.info("Exiting viewQuestion Method");
		return view;
	}

	private String getSubmittedAns(String[] userAns) {
		logger.info("Entering getSubmittedAns Method");
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
		logger.info("Comma seperated option ids from request object " + ans);
		logger.info("Exiting getSubmittedAns Method");
		return ans;
	}
}
