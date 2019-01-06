package ru.itext.chapter1;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Hello World example using the paper size Letter.
 */
public class HelloWorldLetter {

    /**
     * Path to the resulting PDF file.
     */
    private static final String RESULT = "src/main/resources/pdf/hello_letter.pdf";

    /**
     * Creates a PDF file: hello_letter.pdf.
     *
     * @param args no arguments needed
     */
    public static void main(String[] args) throws DocumentException, IOException {
        // step 1
        // Specifying the page size
        Document document = new Document(PageSize.LETTER);
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World Letter"));
        // step 5
        document.close();
    }
}