package ru.ict.components;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artur, Date: 07.01.19, Time: 16:59
 */
public class ToggleCompositeButton extends JLayeredPane {
    private JLabel bgImgOff;
    private JLabel bgImgOn;
    /**
     * состояние
     */
    private boolean toggle;
    private JLabel textLabelOff;
    private JLabel textLabelOn;

    private static List<ToggleCompositeButton> instances = new ArrayList<>();
    public static boolean touched = false;
    public static String choosenPort;

    /**
     * @param imgOff путь к изображению для состояния toggle=true
     * @param imgOn  путь к изображению для состояние toggle = false
     */
    public ToggleCompositeButton(String imgOff, String imgOn) {
        ImageIcon image = new ImageIcon(imgOff);
        setSize(image.getIconWidth(), image.getIconHeight());
        setLayout(null);

        bgImgOff = new JLabel(image);
        bgImgOff.setBounds(getBounds());
        bgImgOn = new JLabel(new ImageIcon(imgOn));
        bgImgOn.setBounds(getBounds());
        bgImgOn.setVisible(false);
        add(bgImgOff, DEFAULT_LAYER);
        add(bgImgOn, DEFAULT_LAYER);

        MouseListener toggleListener = new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setChecked(!toggle);
            }
        };

        addMouseListener(toggleListener);
        instances.add(this);
    }

    public JLabel getTextLabelOn() {
        return textLabelOn;
    }

    /**
     * Установить текстовую метку для состояния toggle=true
     */
    public void setTextLabelOn(JLabel textLabel) {
        textLabel.setBounds(getBounds());
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.textLabelOn = textLabel;
        add(textLabel, PALETTE_LAYER);
        textLabel.setVisible(false);
    }

    public JLabel getTextLabelOff() {
        return textLabelOff;
    }

    /**
     * Установить текстовую метку для состояния toggle=false
     */
    public void setTextLabelOff(JLabel textLabel) {
        textLabel.setBounds(getBounds());
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.textLabelOff = textLabel;
        add(textLabel, PALETTE_LAYER);
    }

    /**
     * @return toggle
     */
    private boolean isChecked() {
        return toggle;
    }

    /**
     * Установить состояние
     *
     * @param f toggle=f
     */
    private void setChecked(boolean f) {
        if (f) {
            bgImgOff.setVisible(false);
            bgImgOn.setVisible(true);
            toggle = true;
            if (textLabelOn != null) {
                textLabelOff.setVisible(false);
                textLabelOn.setVisible(true);
                for (ToggleCompositeButton but : instances) {
                    if (but != this)
                        but.setChecked(false);
                }
                touched = true;
                choosenPort = textLabelOn.getText();
            }
        } else {
            bgImgOff.setVisible(true);
            bgImgOn.setVisible(false);
            toggle = false;
            if (textLabelOn != null) {
                textLabelOff.setVisible(true);
                textLabelOn.setVisible(false);
                touched = false;
                for (ToggleCompositeButton but : instances) {
                    if (but.isChecked()) {
                        touched = true;
                        break;
                    }
                }
            }
        }
    }
}
