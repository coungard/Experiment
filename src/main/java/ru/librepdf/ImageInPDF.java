package ru.librepdf;

/**
 * Created by artur, Date: 06.01.19, Time: 17:36
 */
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfWriter;

public class ImageInPDF {

    public static void main(String[] args) {
        new ImageInPDF().setImageInPDF("src/main/resources/pdf/image.pdf");
    }

    private void setImageInPDF(String PDFPath){
        try {
            Font font = new Font(Font.HELVETICA, 12, Font.ITALIC, java.awt.Color.BLUE);
            Document doc = new Document();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(PDFPath));
            Image image = Image.getInstance("src/main/resources/images/netjs.png");
            doc.open();
            PdfContentByte canvas = writer.getDirectContentUnder();
            image.scaleAbsolute(300, 200);
            image.setAbsolutePosition(0, 600);
            canvas.saveState();
            PdfGState state = new PdfGState();
            state.setFillOpacity(0.1f);
            canvas.setGState(state);
            canvas.addImage(image);
            canvas.restoreState();
            doc.add(new Paragraph("Adding image to PDF Example", font));
            doc.close();
            writer.close();
        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
