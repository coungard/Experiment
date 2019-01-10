package ru.ict.pages;

import jssc.SerialPortList;
import ru.ict.Controller;
import ru.ict.components.ToggleCompositeButton;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by artur, Date: 07.01.19, Time: 12:58
 */
public class PortsPage extends AbstractPage {
    private JPanel portsPanel = new JPanel();

    public PortsPage() {
        setVisible(false);
        setSize(800, 600);
        JLabel title = new JLabel("Select the com port connected to the coin");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        title.setForeground(new Color(105, 65, 73));
        title.setBounds(20, 20, getWidth(), 60);
        add(title);

        nextButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!ToggleCompositeButton.touched)
                    JOptionPane.showMessageDialog(null, "Select port!", "message", JOptionPane.INFORMATION_MESSAGE);
                else {
                    Controller.properties.put("port", ToggleCompositeButton.choosenPort);
                    Controller.showNext();
                }
            }
        });

        backButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.showPrevious();
            }
        });

        showPorts();
    }

    private void showPorts() {
        String[] ports = SerialPortList.getPortNames();
        portsPanel.setLayout(null);
        portsPanel.setSize(300, 500);
        for (int i = 0; i < ports.length; i++) {
            ToggleCompositeButton but = new ToggleCompositeButton("src/main/resources/images/selectButton.png", "src/main/resources/images/selectButtonPress.png");
            JLabel label = new JLabel(ports[i]);
            but.setTextLabelOff(label);
            JLabel label2 = new JLabel(ports[i]);
            label2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
            but.setTextLabelOn(label2);
            but.setLocation(0, i * 90);
            portsPanel.add(but);
        }
        portsPanel.setLocation(250, 100);
        portsPanel.setOpaque(false);
        add(portsPanel);
    }

    @Override
    public void redraw() {
        backButton.setVisible(true);
        nextButton.setVisible(true);
    }
}
