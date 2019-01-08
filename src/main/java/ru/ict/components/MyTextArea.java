package ru.ict.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by artur, Date: 08.01.19, Time: 14:10
 */
public class MyTextArea extends JTextArea {

    private Image img;

    public MyTextArea() {
        setOpaque(false); // без этого свойства картинка обесцвечивается
        try{
            img = ImageIO.read(new File("src/main/resources/images/textArea.jpg"));
        } catch(IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img,0,0,null);
        super.paintComponent(g);
    }
}
