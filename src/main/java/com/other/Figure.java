package com.other;


import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Figure extends JFrame{

    int lol=1, lal=1, kek=1;
    int[] arrayX1 = {50,250,270,70,50};
    int[] arrayY1 = {100,100,250,250,100};
    int[] arrayX2 = {50,200,200,270,200,200,50,50};
    int[] arrayY2 = {100,100,50,150,250,200,200,100};

    public Figure()  {
        super("Фигура");
        setSize(360, 340);
        JFrame myWindow = new JFrame("Настройки");
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Box mainBox = Box.createVerticalBox();
        Box box1 = Box.createVerticalBox();
        ButtonGroup bg = new ButtonGroup();

        JRadioButton r1 = new JRadioButton("Параллелограмм");
        r1.addActionListener((ActionEvent e) -> {
            lol=1;
        });
        JRadioButton r2 = new JRadioButton("Стрелка вправо");
        r2.addActionListener((ActionEvent e) -> {
            lol=2;
        });

        r1.setSelected(true);
        bg.add(r1); bg.add(r2);
        box1.add(r1);box1.add(r2);
        box1.setBorder(new TitledBorder("Фигура"));
        bg = new ButtonGroup();
        Box box2 = Box.createVerticalBox();

        JRadioButton r3 = new JRadioButton("Красный");
        r3.addActionListener((ActionEvent e) -> {
            lal=1;
        });
        JRadioButton r4 = new JRadioButton("Голубой");
        r4.addActionListener((ActionEvent e) -> {
            lal=2;
        });

        r3.setSelected(true);
        bg.add(r3); bg.add(r4);
        box2.add(r3);box2.add(r4);
        box2.setBorder(new TitledBorder("Цвет"));
        bg = new ButtonGroup();
        Box box3 = Box.createVerticalBox();
        JRadioButton r5 = new JRadioButton("Нужна");

        r5.addActionListener((ActionEvent e) -> {
            kek=1;
        });
        JRadioButton r6 = new JRadioButton("Не нужна");
        r6.addActionListener((ActionEvent e) -> {
            kek=2;
        });

        r5.setSelected(true);
        bg.add(r5); bg.add(r6);
        box3.add(r5);box3.add(r6);
        box3.setBorder(new TitledBorder("Заливка"));
        mainBox.add(box1);
        mainBox.add(box2);
        mainBox.add(box3);
        myWindow.setContentPane(mainBox);
        JButton button = new JButton("Нарисовать");
        button.addActionListener((ActionEvent e) -> {
            repaint();
            setVisible(true);
        });
        myWindow.add(button);
        myWindow.pack();
        myWindow.setVisible(true);
        myWindow.setLocationRelativeTo(null);
        myWindow.setPreferredSize(new Dimension(300,400));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paint ( Graphics g ) {
        Graphics2D graph = (Graphics2D) g;
        graph.setPaint(new Color(240,240,240));
        graph.fillRect(0, 0, 300, 300);
        if (lal==1){
            Color Color = new Color(255, 0, 0);
            g.setColor(Color);
        }else {
            Color Color = new Color(0, 255, 255);
            g.setColor(Color);
        }
        if(lol==1){
            Polygon poly = new Polygon(arrayX1, arrayY1, 5);
            graph.drawPolygon(poly);
            if(kek==1){
                graph.fillPolygon(poly);}
        }else{
            Polygon poly = new Polygon(arrayX2, arrayY2, 8);
            graph.drawPolygon(poly);
            if(kek==1){
                graph.fillPolygon(poly);}
        }
    }

    public static void main(String args[]) {
        new Figure();
    }
}
