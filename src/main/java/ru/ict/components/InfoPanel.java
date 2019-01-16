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
        setContentType("text/html");
    }

    public void appendRow(String message, Color color, boolean output) {
        setEditable(true);
        insertIcon(new ImageIcon(String.format("src/main/resources/ict/%s.png", output ? "output" : "input")));
        AttributeSet aset = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        aset = styleContext.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = styleContext.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        aset = styleContext.addAttribute(aset, StyleConstants.FontSize, 18);

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
        AttributeSet aset = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(56, 116, 111, 180));
        aset = styleContext.addAttribute(aset, StyleConstants.FontSize, 15);

//        StyledDocument doc = this.getStyledDocument();
//        SimpleAttributeSet right = new SimpleAttributeSet();
//        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
//        doc.setParagraphAttributes(len - dateFormat.format(date).length(), dateFormat.format(date).length(), right, false);

        int len = getDocument().getLength();
        setCaretPosition(len);
        setCharacterAttributes(aset, false);
        replaceSelection(dateFormat.format(date) + "\n");
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
        super.paintComponent(g);
    }
}
