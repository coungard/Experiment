package ru.ict.pages;

import jssc.SerialPortException;
import ru.ict.Controller;
import ru.ict.components.CompositeButton;
import ru.ict.components.MyTextArea;
import ru.ict.protocol.ICTComandType;
import ru.ict.protocol.ICTCommand;
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
    private JLabel portLabel;
    private JTextArea info = new MyTextArea();
    private IctClient ictClient;
    private String port;

    public ManagerPage() {
        setVisible(false);
        setLayout(null);
        setSize(800, 600);

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

        portLabel = new JLabel();
        portLabel.setForeground(Color.BLACK);
        portLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        portLabel.setHorizontalAlignment(SwingConstants.CENTER);
        portLabel.setBounds(0, 0, getWidth(), 50);
        add(portLabel);

        info.setBounds(10, 120, getWidth() - 20, 300);
        add(info);

        versionButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                info.append("\ncommand sent for version reques, wait...");
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
        port = Controller.properties.get("port");
        try {
            if (ictClient != null)
                ictClient.close();

            backButton.setVisible(true);
            portLabel.setText("portLabel: " + port);
            ictClient = new IctClient(port);

            init();
        } catch (SerialPortException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            String response = ictClient.sendPacket(new ICTCommand(ICTComandType.RequestStatus));
            if (response != null) {
                info.setForeground(new Color(52, 7, 6));
                info.setText("Coin port " + port + " opened succesfully! " + response);
            } else {
                info.setForeground(Color.RED);
                info.setText("Port not responding!");
            }

        } catch (SerialPortException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
