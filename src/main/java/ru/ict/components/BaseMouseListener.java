package ru.ict.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by artur, Date: 16.01.19, Time: 17:36
 */
public abstract class BaseMouseListener implements MouseListener {

    private static boolean active = true;

    public static void setActive(boolean active) {
        BaseMouseListener.active = active;
    }

    public abstract void doPerformAction(MouseEvent e);

    @Override
    public void mouseClicked(MouseEvent e) {
        if(active){
            doPerformAction(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
