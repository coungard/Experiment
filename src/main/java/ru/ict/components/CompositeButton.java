package ru.ict.components;


import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Самодельная кнопка с label и двумя фоновыми рисунками(backgroundImg).
 */
public class CompositeButton extends JLayeredPane {
    private JLabel bgImg;
    private JLabel bgImgPush;
    private JLabel textLabel;

    /**
     * @param img     первый backgroundImg
     * @param pushImg второй backgroundImg появляющийся во время нажатия
     */
    public CompositeButton(String img, String pushImg) {
        ImageIcon image = new ImageIcon(img);
        setSize(image.getIconWidth(), image.getIconHeight());
        setLayout(null);

        bgImg = new JLabel(image);
        bgImg.setBounds(getBounds());
        bgImgPush = new JLabel(new ImageIcon(pushImg));
        bgImgPush.setBounds(getBounds());
        bgImgPush.setVisible(false);
        add(bgImg, DEFAULT_LAYER);
        add(bgImgPush, DEFAULT_LAYER);
        bgImg.setDisabledIcon(bgImgPush.getIcon());
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isEnabled()) {
                    bgImg.setVisible(false);
                    bgImgPush.setVisible(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                bgImg.setVisible(true);
                bgImgPush.setVisible(false);
            }
        });
    }

    public JLabel getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(JLabel textLabel) {
        textLabel.setBounds(getBounds().x + 5, getBounds().y + 5, getBounds().width - 10, getBounds().height - 10);

        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.textLabel = textLabel;
        add(textLabel, PALETTE_LAYER);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        bgImg.setEnabled(enabled);
    }

    public void setText(String text) {
        if (textLabel != null) {
            textLabel.setText(text);
        }
    }
}
