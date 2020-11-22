package edu.email;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class EmailTest {
    private final static String PROPS_FILE = "smtp/email.properties";
    private static String thema = "Платежная операция";
    private static String text = "{\"id\":\"0000000002\";\"sum\"=\"1050.00\";\"date\":\"22.05.2020-1:35\"}";

    public static void main(String[] args) {
        try {
            InputStream is = new FileInputStream(PROPS_FILE);
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
            Properties pr = new Properties();
            pr.load(reader);
            SendEmail.SMTP_SERVER = pr.getProperty("server");
            SendEmail.SMTP_Port = pr.getProperty("port");
            SendEmail.EMAIL_FROM = pr.getProperty("from");
            SendEmail.SMTP_AUTH_USER = pr.getProperty("user");
            SendEmail.SMTP_AUTH_PWD = pr.getProperty("pass");
//            SendEmail.FILE_PATH = PROPS_FILE;

            String emailTo = pr.getProperty("to");
            is.close();

            SendEmail se = new SendEmail(emailTo, thema);
            se.sendMessage(text);
            System.out.println("Сообщение отправлено");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}