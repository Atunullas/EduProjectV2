package com.eduproject.printer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.eduproject.dto.PersonalityDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

@Component
public class PDFPersonPrinter extends AbstractITextPdfView {

	private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);

	private void printEachPerson(Document document, String index, PersonalityDTO person) throws DocumentException {
		Paragraph firstName = null;
		Paragraph lastName = null;
		Paragraph dob = null;
		Paragraph doe = null;
		Paragraph gender = null;
		Paragraph about = null;
		Paragraph paragraph = new Paragraph();
		addEmptyParagraph(new Paragraph("  "), 2);
		Font font = new Font();
		font.setStyle(Font.NORMAL);
		font.setSize(12);

		if (!StringUtils.isEmpty(person.getFirstName())) {
			firstName = new Paragraph();
			firstName.setFont(font);
			firstName.add("First Name : " + person.getFirstName());
			document.add(firstName);
		}
		if (!StringUtils.isEmpty(person.getLastName())) {
			lastName = new Paragraph();
			lastName.setFont(font);
			lastName.add("Last Name : " + person.getLastName());
			document.add(lastName);
		}
		if (!StringUtils.isEmpty(person.getPersonDOB())) {
			dob = new Paragraph();
			dob.setFont(font);
			dob.add("DOB : " + person.getPersonDOB());
			document.add(dob);
		}
		if (!StringUtils.isEmpty(person.getPersonDOE())) {
			doe = new Paragraph();
			doe.setFont(font);
			doe.add("DOE : " + person.getPersonDOE());
			document.add(doe);
		}
		if (!StringUtils.isEmpty(person.getPersonGender())) {
			gender = new Paragraph();
			gender.setFont(font);
			gender.add("Gender : " + person.getPersonGender());
			document.add(gender);
		}
		if (!StringUtils.isEmpty(person.getPersonAbout())) {
			about = new Paragraph();
			about.setFont(font);
			about.add("About : " + person.getPersonAbout());
			document.add(about);
			addEmptyParagraph(new Paragraph(), 2);
		}
		firstName.setAlignment(Element.ALIGN_LEFT);

		DottedLineSeparator dottedline = new DottedLineSeparator();
		dottedline.setOffset(2);
		dottedline.setGap(1f);
		paragraph.add(dottedline);
		document.add(paragraph);
	}

	private void setTitleOfDoc(Document document, String docTitle) throws DocumentException {
		Paragraph preface = new Paragraph();
		Paragraph title = new Paragraph(docTitle, titleFont);
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
		List<PersonalityDTO> personalities = (List<PersonalityDTO>) model.get("personalities");
		document.addAuthor("smartApp");
		if (personalities.size() > 0) {
			document.addTitle("About Personalities");
			document.addAuthor("smartApp");
			setTitleOfDoc(document, "Noble Personalities");
			int i = 0;
			for (PersonalityDTO eachPerson : personalities) {
				printEachPerson(document, ++i + ") ", eachPerson);
			}
		} else {
			setTitleOfDoc(document, "No Personalities Found, Please upload !");
		}
	}
}
