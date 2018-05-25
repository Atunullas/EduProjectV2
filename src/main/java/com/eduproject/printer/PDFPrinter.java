package com.eduproject.printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.eduproject.dto.QuizDTO;
import com.eduproject.model.EQuestType;
import com.eduproject.service.QuestAnsService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFPrinter {
	private static final String FILE_NAME = "C:\\EduProject\\QuestionPapers\\" + new Date();

	@Autowired
	private QuestAnsService service;

	public String writeUsingIText() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
			document.open();
			List<QuizDTO> questions = service.performFetchAll();
			int i = 0;
			for (QuizDTO eachQuest : questions) {
				printEachQuestion(document, ++i + eachQuest.getQuestion());
				if (!eachQuest.getQuestType().equals(EQuestType.TRUE_FALSE.name())) {
					printEachOptions(document, eachQuest.getOptionA());
					printEachOptions(document, eachQuest.getOptionB());
					printEachOptions(document, eachQuest.getOptionC());
					printEachOptions(document, eachQuest.getOptionD());
				} else {
					printEachOptions(document, eachQuest.getOptionA());
					printEachOptions(document, eachQuest.getOptionB());
				}
			}
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		return FILE_NAME;
	}

	private void printEachOptions(Document document, String opt) throws DocumentException {
		Paragraph option = new Paragraph();
		Font font = new Font();
		font.setSize(12);
		option.setFont(font);
		option.add(opt);
		option.setAlignment(Element.ALIGN_LEFT);
		document.add(option);
	}

	private void printEachQuestion(Document document, String quest) throws DocumentException {
		Paragraph question = new Paragraph();
		Font font = new Font();
		font.setStyle(Font.BOLD);
		font.setSize(12);
		question.setFont(font);
		question.add(question);
		question.setAlignment(Element.ALIGN_CENTER);
		document.add(question);
	}
}
