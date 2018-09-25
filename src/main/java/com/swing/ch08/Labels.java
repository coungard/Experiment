package com.swing.ch08;

import javax.swing.*;
import java.awt.*;

public class Labels extends JFrame implements SwingConstants {
    private Labels() {
        super("Labels");
        // при закрытии окна заканчиваем работу
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // самая простая надпись
        JPanel contents = new JPanel();
        JLabel l1 = new JLabel("Ваше имя:");
        JTextField name = new JTextField(20);
        contents.add(l1);
        contents.add(name);
        // надпись со значком
        JLabel l2 = new JLabel(new ImageIcon("src/main/resources/images/monkey.gif"));
        adjustLabel(l2);
        l2.setHorizontalAlignment(LEFT);
        contents.add(l2);
        // надпись с нестандартным выравниванием
        JLabel l3 = new JLabel("Текст и значок", new ImageIcon("src/main/resources/images/bulb.gif"), RIGHT);
        adjustLabel(l3);
        l3.setVerticalTextPosition(BOTTOM);
        l3.setHorizontalTextPosition(LEFT);
        contents.add(l3);
        // вывод окна на экран
        setContentPane(contents);
        setSize(320, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    // метод производит специальную настройку надписи
    private void adjustLabel(JLabel l) {
        l.setOpaque(true);
        l.setBackground(Color.white);
        l.setPreferredSize(new Dimension(250, 100));
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Labels::new);
    }
}
