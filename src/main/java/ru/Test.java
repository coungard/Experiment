package ru;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        try {
            File file = new File("/home/artur/qrCode7.jpg");
            BufferedImage bi = ImageIO.read(file);

            ImageIO.write(bi, "bmp", new File("home/artur/qrCode7.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

