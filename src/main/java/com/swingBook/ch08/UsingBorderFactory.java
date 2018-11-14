package com.swingBook.ch08;

import javax.swing.*;

public class UsingBorderFactory extends JFrame {

    private UsingBorderFactory() {
        super("UsingBorderFactory");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // рамка для панели содержимого
        JPanel cp = (JPanel) getContentPane();
        cp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),
                "Сделано на фабрике рамок"));

        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UsingBorderFactory::new);
    }
}
