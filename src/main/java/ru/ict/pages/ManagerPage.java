package ru.ict.pages;

import jssc.SerialPortException;
import ru.ict.Controller;
import ru.ict.components.CompositeButton;
import ru.ict.components.InfoPanel;
import ru.ict.protocol.ICTComandType;
import ru.ict.protocol.ICTCommand;
import ru.ict.protocol.IctClient;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by artur, Date: 07.01.19, Time: 15:37
 */
public class ManagerPage extends AbstractPage {
    private JLabel portLabel;
    private InfoPanel infoPanel = new InfoPanel();
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

        String[] images = new String[]{"src/main/resources/ict/commandButton.png", "src/main/resources/images/commandButtonPress.png"};
        CompositeButton unableButton = new CompositeButton(images[0], images[1]);
        CompositeButton disableButton = new CompositeButton(images[0], images[1]);
        CompositeButton statusButton = new CompositeButton(images[0], images[1]);
        CompositeButton versionButton = new CompositeButton(images[0], images[1]);
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

        add(infoPanel);
        JScrollPane scrollPane = new JScrollPane(infoPanel);
        scrollPane.setBounds(10, 120, getWidth() - 20, 300);
        add(scrollPane);

        versionButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                infoPanel.append("\nBeta Version!", Color.BLACK);
            }
        });

        statusButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String request = ictClient.sendPacket(new ICTCommand(ICTComandType.RequestStatus));
                    infoPanel.append("\n" + request, new Color(95, 72, 105));
                    String response = ictClient.getResponse();
                    infoPanel.append("\n" + response, response.contains("!") ? Color.RED : Color.BLACK);

                } catch (SerialPortException | InterruptedException ex) {
                    ex.printStackTrace();
                }
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
            infoPanel.setText("");

            init();
        } catch (SerialPortException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            String response = ictClient.sendPacket(new ICTCommand(ICTComandType.RequestStatus));
            if (response != null) {
                infoPanel.append("COIN MACHINE FOUND SUCCESFULLY! PORT: " + port, new Color(16, 173, 129));
            } else {
                infoPanel.append("COIN MACHINE NOT FOUND! PORT: " + port, Color.RED);
            }

        } catch (SerialPortException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
