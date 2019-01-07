package ru.ict.pages;

import ru.ict.Controller;
import ru.ict.components.CompositeButton;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by artur, Date: 07.01.19, Time: 14:12
 */
public abstract class AbstractPage extends JLayeredPane {
    private AbstractPage previousPage;
    private AbstractPage nextPage;
    CompositeButton backButton = new CompositeButton("src/main/resources/images/backButton.png", "src/main/resources/images/backButtonPress.png");

    public AbstractPage getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(AbstractPage previousPage) {
        this.previousPage = previousPage;
    }

    public AbstractPage getNextPage() {
        return nextPage;
    }

    public void redraw() {
        backButton.setLocation(50, 400);
        add(backButton);
        backButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.showPrevious();
            }
        });
        backButton.setVisible(false);
    }

    public void setNextPage(AbstractPage nextPage) {
        this.nextPage = nextPage;
    }
}
