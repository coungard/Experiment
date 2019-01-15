package ru.ict.pages;

import ru.ict.Controller;
import ru.ict.components.CompositeButton;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by artur, Date: 07.01.19, Time: 13:05
 */
public class StartPage extends AbstractPage {
    public StartPage() {
        setLayout(null);
        setVisible(false);
        setSize(800, 600);

        CompositeButton startButton = new CompositeButton("src/main/resources/ict/startButton.png", "src/main/resources/ict/startButtonPress.png");
        startButton.setLocation(200, 150);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.showNext();
            }
        });
        add(startButton);

        CompositeButton exitButton = new CompositeButton("src/main/resources/ict/exitButton.png","src/main/resources/ict/exitButtonPress.png");
        exitButton.setLocation(5,500);
        exitButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);
    }
}
