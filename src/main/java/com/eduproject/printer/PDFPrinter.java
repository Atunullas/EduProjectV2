package com.eduproject.printer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.eduproject.dto.OptionDTO;
import com.eduproject.dto.QuestionDTO;
import com.eduproject.model.EQuestType;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDFPrinter extends AbstractITextPdfView {

	private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);

	private void printEachOptions(Document document, String opt) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		addEmptyParagraph(paragraph, 0);
		Font font = new Font();
		font.setSize(12);
		paragraph.setFont(font);
		paragraph.add(opt);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setIndentationLeft(20f);
		document.add(paragraph);
	}

	private void printEachQuestion(Document document, String quest, String questionType) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		addEmptyParagraph(paragraph, 1);
		Font font = new Font();
		font.setStyle(Font.BOLD);
		font.setSize(12);
		paragraph.setFont(font);
		paragraph.add(quest);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.add(new Chunk(questionType));
		document.add(paragraph);
	}

	private void setTitleOfDoc(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		Paragraph title = new Paragraph("Question Paper", titleFont);
		addEmptyParagraph(preface, 2);
		title.setAlignment(Element.ALIGN_CENTER);
		preface.add(title);
		document.add(preface);
	}

	private void addEmptyParagraph(Paragraph preface, int times) {
		for (int i = 0; i < times; i++)
			preface.add(new Paragraph(" "));
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		Map<Integer, QuestionDTO> questions = (Map<Integer, QuestionDTO>) model.get("questions");
		document.addTitle("Question Paper");
		document.addAuthor("smartApp");
		setTitleOfDoc(document);
		int i = 0;
		for (QuestionDTO eachQuest : questions.values()) {
			String questionType = "";
			if (eachQuest.getQuestionType().equals(EQuestType.BEST_ANS.name())) {
				questionType = " (Choose the right Answer)";
			} else if (eachQuest.getQuestionType().equals(EQuestType.MUL_ANS.name())) {
				questionType = " (Multiple Answers)";
			} else if (eachQuest.getQuestionType().equals(EQuestType.TRUE_FALSE.name())) {
				questionType = " (True or False)";
			} else {
				questionType = " (Unknown Question Type)";
			}

			printEachQuestion(document, ++i + ") " + eachQuest.getQuestionTxt(), questionType);
			for (OptionDTO opt : eachQuest.getOptions()) {
				printEachOptions(document, opt.getOptionTxt());
			}
		}
	}
}
