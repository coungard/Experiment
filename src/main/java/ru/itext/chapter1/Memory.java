package ru.itext.chapter1;

/**
 * Created by artur, Date: 21.12.18, Time: 14:44
 */
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Creates a PDF file in memory.
 */
public class Memory {

    /** Path to the resulting PDF file. */
    private static final String RESULT = "src/main/resources/pdf/memory.pdf";

    /**
     * Creates a PDF file: hello_memory.pdf
     * @param    args    no arguments needed
     */
    public static void main(String[] args) throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        // we'll create the file in memory
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World!"));
        // step 5
        document.close();

        // let's write the file in memory to a file anyway
        FileOutputStream fos = new FileOutputStream(RESULT);
        fos.write(baos.toByteArray());
        fos.close();
    }
}
