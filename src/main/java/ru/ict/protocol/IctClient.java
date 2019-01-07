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
    private static final int POLL_TIMEOUT = 1000;
    private SerialPort serialPort;
    private byte currentCommand;

    IctClient(String port) throws SerialPortException {
        serialPort = new SerialPort(port);

        serialPort.openPort();
        serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
    }

    private void sendPacket(ICTCommand command) throws SerialPortException, InterruptedException {
        serialPort.writeBytes(formPacket(command));
        Thread.sleep(4000);
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

        Utils.createCase(message);

        return message;
    }

    private class PortReader implements SerialPortEventListener {
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    Thread.sleep(POLL_TIMEOUT);
                    receivedData = serialPort.readBytes();

                    if (receivedData.length == 0) {
                        throw new RuntimeException("Несоответствие контрольной суммы полученного сообщения. " +
                                "Возможно устройство не подключено к COM-порту. Проверьте настройки подключения.");
                    }
                    Utils.createResponse(currentCommand);
                } catch (SerialPortException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
