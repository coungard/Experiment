package com.swing.ch08;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Borders extends JFrame {

    private Borders() {
        super("Borders");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // создаем панели со всевозможными рамками
        JPanel contents = new JPanel(new GridLayout(3, 2, 5, 5));
        contents.add(createPanel(new TitledBorder("Рамка с заголовком"), "TitledBorder"));
        contents.add(createPanel(new EtchedBorder(), "EtchedBorder"));
        contents.add(createPanel(new BevelBorder(BevelBorder.LOWERED), "BevelBorder"));
        contents.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED), "SoftBevelBorder"));
        contents.add(createPanel(new LineBorder(Color.BLACK, 5), "LineBorder"));
        contents.add(createPanel(new MatteBorder(
                new ImageIcon("src/main/resources/images/matte.gif")), "MatteBorder"));

        add(contents);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // метод создает панель с рамкой и надписью
    private JPanel createPanel(Border b, String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(text));
        panel.setBorder(new CompoundBorder(b, new EmptyBorder(30, 30, 30, 30)));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Borders::new);
    }
}
