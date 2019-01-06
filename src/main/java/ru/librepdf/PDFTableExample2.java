package ru.librepdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import ru.librepdf.model.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.Month;
//import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PDFTableExample2 {

    public static void main(String[] args) {
        new PDFTableExample2().createTablePDF("src/main/resources/pdf/table.pdf");

    }
    private void createTablePDF(String PDFPath){
        try {
            Font font = new Font(Font.HELVETICA, 12, Font.BOLDITALIC);
            Document doc = new Document();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(PDFPath));
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            // setting column widths
            table.setWidths(new float[] {6.0f, 6.0f, 6.0f, 6.0f});
            PdfPCell cell = new PdfPCell();
            // table headers
            cell.setPhrase(new Phrase("First Name", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Last Name", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Email", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("DOB", font));
            table.addCell(cell);
            List<User> users = getListOfUsers();
            // adding table rows
            for(User user : users) {
                table.addCell(user.getFirstName());
                table.addCell(user.getLastName());
                table.addCell(user.getEmail());
                table.addCell(new SimpleDateFormat("dd/MM/yyyy").format(user.getDob()));
            }
            doc.open();
            // adding table to document
            doc.add(table);
            doc.close();
            writer.close();
            System.out.println("PDF using OpenPDF created successfully");
        } catch (DocumentException | FileNotFoundException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Dummy method for adding List of Users
    private List<User> getListOfUsers() throws ParseException {
        List<User> users = new ArrayList<User>();
        Calendar dob = Calendar.getInstance();
        dob.set(1975,6,12);
        users.add(new User("Jack", "Reacher", "abc@xyz.com", dob.getTime()));
        // Using LocalDate from new time&date API Java 8 onward
//        LocalDate date = LocalDate.of(2016, Month.APRIL, 28);
        Date date = new Date(2016, 03 , 28);
        users.add(new User("Remington", "Steele", "rs@cbd.com", dob.getTime()));
        dob.set(1965,12,6);
        users.add(new User("Jonathan", "Raven", "jr@sn.com", dob.getTime()));
        return users;
    }
}