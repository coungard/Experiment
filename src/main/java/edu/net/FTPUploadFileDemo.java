package edu.net;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A program that demonstrates how to upload files from local computer
 * to a remote FTP server using Apache Commons Net API.
 *
 * @author www.codejava.net
 */
public class FTPUploadFileDemo {

    public static void main(String[] args) {
        String server = "109.248.44.52";
        int port = 21;
        String user = "ftpadmin";
        String pass = "ftpAdm$2020";

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.LOCAL_FILE_TYPE);

            ftpClient.makeDirectory("photos/");
            Thread.sleep(1000);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            String photo = "photos/photo_passport_9285689999.jpg";
            String photoWithoutDir = photo.split("/")[1];

            // APPROACH #1: uploads first file using an InputStream
            File file = new File(photo);

            InputStream inputStream = new FileInputStream(file);

            String[] parts = photo.split("/");
            System.out.println("Start uploading first file " + parts[1]);
            boolean done = ftpClient.storeFile(photo, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The file + " + photo + " uploaded successfully!");
            } else {
                System.out.println("File is not uploaded!");
            }

//            // APPROACH #2: uploads second file using an OutputStream
//            File secondLocalFile = new File(photo2);
////            String secondRemoteFile = "photo_passport_9285680000.jpg";
//            inputStream = new FileInputStream(secondLocalFile);
//
//            System.out.println("Start uploading second file");
//            OutputStream outputStream = ftpClient.storeFileStream(photo2);
//            byte[] bytesIn = new byte[4096];
//            int read = 0;
//
//            while ((read = inputStream.read(bytesIn)) != -1) {
//                outputStream.write(bytesIn, 0, read);
//            }
//            inputStream.close();
//            outputStream.close();
//
//            boolean completed = ftpClient.completePendingCommand();
//            if (completed) {
//                System.out.println("The second file is uploaded successfully.");
//            }

        } catch (IOException | InterruptedException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}