package ru.itext.chapter1;

/**
 * Created by artur, Date: 21.12.18, Time: 10:51
 */

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A possible way to create a document in the landscape format.
 */
public class Landscape2 {

    /** Path to the resulting PDF file. */
    private static final String RESULT = "src/main/resources/pdf/landscape2.pdf";

    /**
     * Creates a PDF file: hello_landscape2.pdf
     * @param    args    no arguments needed
     */
    public static void main(String[] args) throws DocumentException, IOException {
        // step 1
        // landscape format because width > height
        Document document = new Document(new Rectangle(792, 612));
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World"));
        // step 5
        document.close();
    }
}
