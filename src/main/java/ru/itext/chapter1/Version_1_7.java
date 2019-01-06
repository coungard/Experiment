package ru.itext.chapter1;

/**
 * Created by artur, Date: 21.12.18, Time: 14:47
 */

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Creates a Hello World in PDF version 1.7
 */
public class Version_1_7 {

    /**
     * Path to the resulting PDF file.
     */
    public static final String RESULT = "src/main/resources/pdf/1_7.pdf";

    /**
     * Creates a PDF file: hello.pdf
     *
     * @param args no arguments needed
     */
    public static void main(String[] args) throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        // Creating a PDF 1.7 document
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World!"));
        // step 5
        document.close();
    }
}
