package ru.ict.protocol;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import ru.ict.util.Utils;

/**
 * Created by artur, Date: 06.01.19, Time: 13:56
 */
public class IctClient extends Thread {
    public static byte[] receivedData;
    private static final int POLL_TIMEOUT = 400;
    private SerialPort serialPort;
    private byte currentCommand;
    private String response;
    private String outputCommand;

    public IctClient(String port) throws SerialPortException, InterruptedException {
        serialPort = new SerialPort(port);
        serialPort.openPort();
        serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
    }

    public String sendPacket(ICTCommand command) throws SerialPortException, InterruptedException {
        serialPort.writeBytes(formPacket(command));
        sleep(POLL_TIMEOUT);
        return outputCommand;
    }

    private byte[] formPacket(ICTCommand command) {
        int dataSize = command.getData() != null ? command.getData().length : 0;
        byte[] message = new byte[1 + 1 + 1 + 1 + 1 + dataSize];

        // формируем сообщение
        message[0] = (byte) 0x90; //dest address
        message[1] = (byte) message.length;
        currentCommand = message[2] = command.getCommandType().getCode();
        message[3] = 0x03;
        message[4] = Utils.getChecksum(command.getCommandType().getCode());

        outputCommand = Utils.createCase(message);

        return message;
    }

    public void close() throws SerialPortException {
        if (serialPort != null)
            serialPort.closePort();
    }

    private class PortReader implements SerialPortEventListener {
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    receivedData = serialPort.readBytes();
                    Thread.sleep(POLL_TIMEOUT);
                    response = Utils.createResponse(currentCommand);
                } catch (SerialPortException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getResponse() {
        return response;
    }
}
