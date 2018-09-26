package com.swing.ch08;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class PaintingWithBorders extends JFrame {
    private PaintingWithBorders() {
        super("PaintingWithBorders");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // добавим к нашему компоненту рамку
        CustomComponent cc = new CustomComponent();
        cc.setBorder(BorderFactory.createTitledBorder("Рамка!"));
        // добавим компонент в окно
        getContentPane().add(cc);
        setSize(400,250);
        setVisible(true);
    }

    private class CustomComponent extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            // получаем подходящий прямоугольник
            Rectangle rect = AbstractBorder.getInteriorRectangle(this, getBorder(), 0, 0, getWidth(), getHeight());
            // рисуем в нем
            g.setColor(Color.white);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PaintingWithBorders::new);
    }
}
