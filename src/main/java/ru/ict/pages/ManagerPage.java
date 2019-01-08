package ru.ict.pages;

import jssc.SerialPortException;
import ru.ict.Controller;
import ru.ict.components.CompositeButton;
import ru.ict.components.MyTextArea;
import ru.ict.protocol.IctClient;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by artur, Date: 07.01.19, Time: 15:37
 */
public class ManagerPage extends AbstractPage {
    private final String[] images = new String[]{"src/main/resources/images/commandButton.png", "src/main/resources/images/commandButtonPress.png"};
    private CompositeButton unableButton = new CompositeButton(images[0], images[1]);
    private CompositeButton disableButton = new CompositeButton(images[0], images[1]);
    private CompositeButton statusButton = new CompositeButton(images[0], images[1]);
    private CompositeButton versionButton = new CompositeButton(images[0], images[1]);
    private JLabel port;
    private JTextArea info = new MyTextArea();
    private IctClient ictClient;

    public ManagerPage() {
        setVisible(false);
        setLayout(null);
        setSize(800,600);

        backButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.showPrevious();
            }
        });

        putUCAbutton(unableButton, 80, "unable coin");
        putUCAbutton(disableButton, 240, "disable coin");
        putUCAbutton(statusButton, 400, "status");
        putUCAbutton(versionButton, 560, "version");

        port = new JLabel();
        port.setForeground(Color.BLACK);
        port.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        port.setHorizontalAlignment(SwingConstants.CENTER);
        port.setBounds(0,0,getWidth(),50);
        add(port);

        info.setBounds(10,120,getWidth() - 20,300);
        info.setBackground(new Color(152, 214, 211, 157));
        info.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        info.setForeground(Color.WHITE);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        add(info);

        versionButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                info.append("command sent for version request, wait...\n");
            }
        });
    }

    private void putUCAbutton(CompositeButton but, int x, String text) {
        but.setLocation(x, 70);
        JLabel label = new JLabel(text);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        label.setForeground(new Color(44, 45, 42, 242));
        but.setTextLabel(label);
        add(but);
    }

    @Override
    public void redraw() {
        backButton.setVisible(true);
        port.setText("port: " + Controller.properties.get("port"));
        info.setText("");
        try {
            ictClient = new IctClient(port.getText());
        } catch (SerialPortException e) {
            e.printStackTrace();
            info.append(e.getMessage() + ". Please check your port!");
        }
    }
}
