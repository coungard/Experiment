package ru.ict.pages;

import jssc.SerialPortException;
import ru.ict.Controller;
import ru.ict.components.BaseMouseListener;
import ru.ict.components.CompositeButton;
import ru.ict.components.InfoPanel;
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
    private JLabel portLabel;
    private JLabel coinImage;
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

        String[] images = new String[]{"src/main/resources/ict/commandButton.png", "src/main/resources/ict/commandButtonPress.png"};
        CompositeButton enableButton = new CompositeButton(images[0], images[1]);
        CompositeButton disableButton = new CompositeButton(images[0], images[1]);
        CompositeButton statusButton = new CompositeButton(images[0], images[1]);
        CompositeButton versionButton = new CompositeButton(images[0], images[1]);

        putUCAbutton(enableButton, 80, "unable coin");
        putUCAbutton(disableButton, 240, "disable coin");
        putUCAbutton(statusButton, 400, "status");
        putUCAbutton(versionButton, 560, "version");

        portLabel = new JLabel();
        portLabel.setForeground(Color.BLACK);
        portLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        portLabel.setHorizontalAlignment(SwingConstants.CENTER);
        portLabel.setBounds(0, 0, getWidth(), 50);
        add(portLabel);

        coinImage = new JLabel();
        coinImage.setIcon(new ImageIcon("src/main/resources/ict/coin.gif"));
        coinImage.setSize(coinImage.getIcon().getIconWidth(), coinImage.getIcon().getIconHeight());
        coinImage.setLocation(640, 415);
        coinImage.setVisible(false);
        add(coinImage);

        add(infoPanel);
        JScrollPane scrollPane = new JScrollPane(infoPanel);
        scrollPane.setBounds(10, 120, getWidth() - 20, 300);
        add(scrollPane);

        enableButton.addMouseListener(new BaseMouseListener() {
            @Override
            public void doPerformAction(MouseEvent e) {
                coinImage.setVisible(true);
            }
        });

        disableButton.addMouseListener(new BaseMouseListener() {
            @Override
            public void doPerformAction(MouseEvent e) {
                coinImage.setVisible(false);
            }
        });

        versionButton.addMouseListener(new BaseMouseListener() {
            @Override
            public void doPerformAction(MouseEvent e) {
                infoPanel.appendRow("Beta Version!", Color.BLACK, true);
            }
        });

        statusButton.addMouseListener(new BaseMouseListener() {
            @Override
            public void doPerformAction(MouseEvent e) {
                try {
                    String request = ictClient.sendPacket(new ICTCommand(ICTComandType.RequestStatus));
                    infoPanel.appendRow(request, new Color(95, 72, 105), false);
                    String response = ictClient.getResponse();

                    if (response != null)
                        infoPanel.appendRow(response, response.contains("!") ? Color.RED : Color.BLACK, true);
                    else
                        infoPanel.appendRow("Response = NULL", Color.RED, true);
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
        coinImage.setVisible(false);
        try {
            if (ictClient != null)
                ictClient.close();

            backButton.setVisible(true);
            portLabel.setText("portLabel: " + port);
            ictClient = new IctClient(port);
            infoPanel.setText("");

            init();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            ictClient.sendPacket(new ICTCommand(ICTComandType.RequestStatus));
            Thread.sleep(300);
            String response = ictClient.getResponse();
            if (response != null) {
                BaseMouseListener.setActive(true);
                infoPanel.appendRow("Coin machine founded succesfully! Port: " + port, new Color(16, 173, 129), true);
            } else {
                BaseMouseListener.setActive(false);
                infoPanel.appendRow("Coin machine not found! Port: " + port, Color.RED, true);
            }
        } catch (SerialPortException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
