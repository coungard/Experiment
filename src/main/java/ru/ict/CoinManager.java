package ru.ict;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by artur, Date: 06.01.19, Time: 14:28
 */
public class CoinManager extends JFrame {
    private static final String BG_PATH = "src/main/resources/images/bg.png";
    static JPanel mainPanel;

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                new CoinManager();
            }
        });
    }

    private CoinManager() {
        super("CoinManager");
        setSize(800,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        init();
    }

    private void init() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        Controller.startGui(mainPanel);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(BG_PATH));
        background.setBounds(0,0, background.getIcon().getIconWidth(), background.getIcon().getIconHeight());
        mainPanel.add(background, JLayeredPane.DEFAULT_LAYER);
        add(mainPanel);
    }
}
