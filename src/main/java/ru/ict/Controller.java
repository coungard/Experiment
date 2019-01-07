package ru.ict;

import ru.ict.pages.AbstractPage;
import ru.ict.pages.ManagerPage;
import ru.ict.pages.PortsPage;
import ru.ict.pages.StartPage;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by artur, Date: 07.01.19, Time: 14:00
 */
public class Controller {
    private static Map<String, AbstractPage> pages = new HashMap<>();
    private static final StartPage startPage = new StartPage();
    private static final PortsPage portsPage = new PortsPage();
    private static final ManagerPage managerPage = new ManagerPage();

    private static AbstractPage currentPage;

    static void startGui(JPanel panel) {
        panel.add(startPage);
        panel.add(portsPage);
        panel.add(managerPage);

        startPage.setNextPage(portsPage);
        portsPage.setPreviousPage(startPage);
        portsPage.setNextPage(managerPage);
        managerPage.setPreviousPage(portsPage);

        startPage.setVisible(true);
        currentPage = startPage;

        pages.put("StartPage", startPage);
        pages.put("PortsPage", portsPage);
        pages.put("ManagerPage", managerPage);
    }

    public static void showNext() {
        for (AbstractPage page : pages.values()) {
            if (page == currentPage) {
                page.setVisible(false);
                page.getNextPage().setVisible(true);
                currentPage = page.getNextPage();
                currentPage.redraw();
                break;
            }
        }
    }

    public static void showPrevious() {
        for (AbstractPage page : pages.values()) {
            if (page == currentPage) {
                page.setVisible(false);
                page.getPreviousPage().setVisible(true);
                currentPage = page.getPreviousPage();
                currentPage.redraw();
                break;
            }
        }
    }
}
