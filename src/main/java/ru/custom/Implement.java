package ru.custom;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Implement {
    public static void main(String[] args) throws IOException {
        ESCPOSApi escposApi = new ESCPOSApi("/dev/usb/lp0");
        escposApi.printImage(ImageIO.read(new File("/home/artur/qrCode7.bmp")));
    }
}
