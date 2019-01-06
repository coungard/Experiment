package ru.swingBook.ch09;

import javax.swing.*;
import java.awt.*;

public class ToggleButtons extends JFrame {
    // ссылки на используемые кнопки
    private JToggleButton button1, button2;

    private ToggleButtons() {
        super("ToggleButtons");
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        // используем последовательное расположение
        setLayout(new FlowLayout());
        // создадим пару кнопок JToggleButton
        button1 = new JToggleButton("Первая", true);
        button2 = new JToggleButton("Вторая", false);
        // добавим слушатель события о смене состояния
        button2.addItemListener(e -> button1.setSelected(!button2.isSelected()));
        add(button1);
        add(button2);
        // выводим окно на экран
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToggleButtons::new);
    }
}
