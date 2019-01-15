package ru.ict.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by artur, Date: 08.01.19, Time: 14:10
 */
public class InfoPanel extends JTextPane {
    private static final DateFormat dateFormat = new SimpleDateFormat("dd MMM HH:mm:ss");
    private Image img;
    private final StyleContext styleContext;

    public InfoPanel() {
        setOpaque(false); // без этого свойства картинка обесцвечивается
        setMargin(new Insets(5, 5, 5, 5));
        EmptyBorder eb = new EmptyBorder(new Insets(10, 10, 10, 10));
        setBorder(eb);
        try {
            img = ImageIO.read(new File("src/main/resources/ict/textArea.jpg"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        styleContext = StyleContext.getDefaultStyleContext();
    }

    public void append(String message, Color color) {
        setEditable(true);
        AttributeSet aset = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        aset = styleContext.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = styleContext.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        aset = styleContext.addAttribute(aset, StyleConstants.FontSize, 20);

        int len = getDocument().getLength();
        setCaretPosition(len);
        setCharacterAttributes(aset, false);
        replaceSelection(message);
        appendDate();
        setEditable(false);
    }

    // TODO КАК ПРИЖАТЬ ТЕКСТ С ДАТОЙ К ПРАВОМУ КРАЮ ???
    private void appendDate() {
        Date date = new Date();
        AttributeSet aset = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.GRAY);
        aset = styleContext.addAttribute(aset, StyleConstants.FontSize, 16);
        int len = getDocument().getLength();
        setCaretPosition(len);
        setCharacterAttributes(aset, false);
        replaceSelection("\t\t" + dateFormat.format(date));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
        super.paintComponent(g);
    }
}
