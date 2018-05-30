package com.eduproject.printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduproject.dto.OptionDTO;
import com.eduproject.dto.QuestionDTO;
import com.eduproject.service.QuestAnsService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDFPrinter {
	private static final String FILE_NAME = "C:\\EduProject\\QuestionPapers\\" + new Date().getTime() + ".pdf";

	@Autowired
	private QuestAnsService service;

	public String writeUsingIText() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
			document.open();
			Map<Integer, QuestionDTO> questions = service.performFetchAll();
			int i = 0;
			for (QuestionDTO eachQuest : questions.values()) {
				printEachQuestion(document, ++i + eachQuest.getQuestionTxt());
				for (OptionDTO opt : eachQuest.getOptions())
					printEachOptions(document, opt.getOptionTxt());
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
