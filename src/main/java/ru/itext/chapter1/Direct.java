package ru.itext.chapter1;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by artur, Date: 21.12.18, Time: 14:49
 */
public class Direct {

    /**
     * Path to the resulting PDF file.
     */
    public static final String RESULT = "src/main/resources/pdf/direct.pdf";

    /**
     * Creates a PDF file: hello_direct.pdf
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
        PdfContentByte canvas = writer.getDirectContentUnder();
        writer.setCompressionLevel(0);
        canvas.saveState();                               // q
        canvas.beginText();                               // BT
        canvas.moveText(130, 480);                         // 36 788 Td
        canvas.setFontAndSize(BaseFont.createFont(), 58); // /F1 12 Tf
        canvas.showText("Hello World");                   // (Hello World)Tj
        canvas.endText();                                 // ET
        canvas.restoreState();                            // Q
        // step 5
        document.close();
    }
}
