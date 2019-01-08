package ru.ict.pages;

import ru.ict.components.CompositeButton;

import javax.swing.*;

/**
 * Created by artur, Date: 07.01.19, Time: 14:12
 */
public abstract class AbstractPage extends JLayeredPane {
    private AbstractPage previousPage;
    private AbstractPage nextPage;
    CompositeButton backButton = new CompositeButton("src/main/resources/images/backButton.png", "src/main/resources/images/backButtonPress.png");
    CompositeButton nextButton = new CompositeButton("src/main/resources/images/nextButton.png", "src/main/resources/images/nextButtonPress.png");

    AbstractPage() {
        backButton.setLocation(25, 450);
        nextButton.setLocation(670, 450);
        add(backButton);
        add(nextButton);

        backButton.setVisible(false);
        nextButton.setVisible(false);
    }

    public void redraw() {
    }

    public AbstractPage getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(AbstractPage previousPage) {
        this.previousPage = previousPage;
    }

    public void setNextPage(AbstractPage nextPage) {
        this.nextPage = nextPage;
    }

    public AbstractPage getNextPage() {
        return nextPage;
    }
}
