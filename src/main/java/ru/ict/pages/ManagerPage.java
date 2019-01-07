package ru.ict.pages;

import javax.swing.*;

/**
 * Created by artur, Date: 07.01.19, Time: 15:37
 */
public class ManagerPage extends AbstractPage {

    public ManagerPage() {
        setVisible(false);
        setLayout(null);
        setSize(800,600);

        JLabel label = new JLabel("Here we go manage coin machine!!");
        label.setBounds(200,200,400,50);
        add(label);
    }
}
