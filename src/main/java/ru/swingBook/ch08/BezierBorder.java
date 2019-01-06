package ru.swingBook.ch08;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.CubicCurve2D;

public class BezierBorder implements Border {
    private Color color, shadow;
    private float thickness;

    // значения свойств передаются в конструкторе
    private BezierBorder(Color color, Color shadow, float thickness) {
        this.color = color;
        this.shadow = shadow;
        this.thickness = thickness;
    }

    // место, занимаемое рамкой
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(9, 9, 9, 9);
    }

    // наша рамка частями прозрачна
    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    // метод прорисовки рамки
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        // используем новый объект Graphics
        Graphics2D g2 = (Graphics2D) g.create();
        // настройка пера, координат и цвета
        g2.setStroke(new BasicStroke(thickness));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        x += 5;
        y += 5;
        width -= 10;
        height -= 10;
        g2.setColor(shadow);

        // прорисовываем тень и рамку
        for (int i = 0; i < 2; i++) {
            CubicCurve2D left = new CubicCurve2D.Double(x, y,
                    x - 5, y + height / 3,
                    x + 5, y + height / 3,
                    x, y + height);
            CubicCurve2D right = new CubicCurve2D.Double(x + width, y,
                    x + width - 5, y + height / 3,
                    x + width + 5, y + height * 2 / 3,
                    x + width, y + height);
            CubicCurve2D top = new CubicCurve2D.Double(x, y,
                    x + width / 3, y + 5,
                    x + width * 2 / 3, y - 5,
                    x + width, y);
            CubicCurve2D bottom = new CubicCurve2D.Double(x, y + height,
                    x + width / 3, y + height + 5,
                    x + width * 2 / 3, y + height + 5,
                    x + width, y + height);
            g2.draw(left);
            g2.draw(right);
            g2.draw(top);
            g2.draw(bottom);

            // на втором шаге рисуем саму рамку
            x--;
            y--;
            width--;
            height--;
            g2.setColor(color);
        }
        g2.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BezierBorder");

            // создаем панель с нашей рамкой
            JPanel p = new JPanel(new BorderLayout());
            Border b = new TitledBorder(new BezierBorder(Color.GREEN, Color.DARK_GRAY, 5f), "Bezier");
            p.setBorder(b);
            p.add(new JTextArea());

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.add(p);
            frame.setSize(200, 200);
            frame.setVisible(true);
        });
    }
}