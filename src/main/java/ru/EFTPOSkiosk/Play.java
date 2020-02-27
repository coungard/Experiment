package ru.EFTPOSkiosk;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class Play extends JFrame {
    private JPanel panel = new JPanel();
    private SerialPort serialPort;
    private String portName = "/dev/ttyACM0";
    JTextField outputArea = new JTextField();
    JLabel info = new JLabel();
    private static final byte DLE = (byte) 0x10;
    private static final byte STX = (byte) 0X02;
    private static final byte ETX = (byte) 0x03;
    private static final byte EOT = (byte) 0x04;
    private static final byte ENQ = (byte) 0x05;
    private static final byte ACK = (byte) 0x06;

    private Play() {
        super("UCS");
        this.setSize(800, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBounds(this.getBounds());

        outputArea.setBounds(20, 20, 500, 40);
        panel.add(outputArea);

        JButton send = new JButton("Send");
        send.setBounds(260, 120, 80, 40);
        send.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    String text = outputArea.getText();
                    outputArea.setText("");
                    info.setText("");
                    info.setForeground(Color.BLACK);
                    String[] parts = text.split(" ");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    baos.write(DLE);
                    baos.write(STX);
                    for (String s : parts) {
                        if (s.equals(""))
                            return;

                        char c = s.charAt(0);
                        if (Character.isDigit(c)) {
                            baos.write(Byte.parseByte(s));
                        } else {
                            baos.write(c);
                        }
                    }
                    // DLE STX {meETX {lrc}
                    baos.write(DLE);
                    baos.write(ETX);
                    byte[] message = baos.toByteArray();
                    byte crc = calcCRC(message, message.length);
                    baos.write(crc);
                    System.out.println(Arrays.toString(baos.toByteArray()));
                    info.setText(Arrays.toString(baos.toByteArray()));
                    serialPort.writeBytes(baos.toByteArray());
                } catch (NumberFormatException ex) {
                    String err = "put correct byte array in field! from -128 to 127";
                    System.out.println(err);
                    info.setForeground(Color.RED);
                    info.setText(err);
                } catch (SerialPortException ex) {
                    ex.printStackTrace();
                }
            }
        });

        info.setBounds(20, 200, getWidth(), 40);
        panel.add(send);
        panel.add(info);

        this.add(panel);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windows closing");
                if (serialPort.isOpened()) {
                    try {
                        serialPort.closePort();
                    } catch (SerialPortException ex) {
                        ex.printStackTrace();
                    }
                }
                System.exit(0);
            }
        });
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    private void start() throws SerialPortException {
        serialPort = new SerialPort(portName);
        serialPort.openPort();

        serialPort.setParams(SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
        serialPort.addEventListener(event -> {
            if (event.getEventType() == SerialPortEvent.RXCHAR && event.getEventValue() > 0) {
                try {
                    byte[] response = serialPort.readBytes();
                    System.out.println("input data << " + Arrays.toString(response));
                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
            }
        });
        serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
    }

    private byte calcCRC(byte[] buffer, int len) {
        byte lrc = 0;
        int i;

        for (i = 0; i < len; i++)
            lrc ^= buffer[i];
        return lrc;
    }

    public static void main(String[] args) throws SerialPortException {
        new Play().start();
    }
}
