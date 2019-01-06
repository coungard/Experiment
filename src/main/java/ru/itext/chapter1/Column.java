package ru.itext.chapter1;


import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Created by artur, Date: 21.12.18, Time: 15:09
 */
public class Column {

    /**
     * Path to the resulting PDF file.
     */
    public static final String RESULT = "src/main/resources/pdf/column.pdf";

    /**
     * Creates a PDF file: hello_column.pdf
     *
     * @param args no arguments needed
     */
    public static void main(String[] args) throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        // we set the compression to 0 so that we can read the PDF syntax
        writer.setCompressionLevel(0);
        // writes something to the direct content using a convenience method
        Phrase hello = new Phrase("Hello World");
        Phrase dude = new Phrase("Hello Coungard");
        PdfContentByte canvas = writer.getDirectContentUnder();
        canvas.setFontAndSize(BaseFont.createFont(), 58);

        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, hello, 40, 500, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, dude, 80, 300, 0);
        // step 5
        document.close();
    }
}