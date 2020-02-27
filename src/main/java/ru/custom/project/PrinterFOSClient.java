/**
 *
 */
package ru.custom.project;

import org.apache.log4j.Logger;
import ru.custom.project.commands.IPrinterCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

import static ru.custom.project.PrinterClient.byteArray2String;

/**
 * @author denis
 */
public class PrinterFOSClient extends Thread implements IPrinterClient {
    private static Logger logger = Logger.getLogger(PrinterFOSClient.class);
    private RandomAccessFile raf;

    private byte[] responseData;
    private static final long TIME_OUT = 500;

    public PrinterFOSClient(String path) {
        try {
            File printer = new File(path);
            raf = new RandomAccessFile(printer, "rw");

            start();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
        logger.debug("Initialized");
    }

    public void close() {
        logger.debug("Random access file close");
        try {
            raf.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public synchronized byte[] sendCommand(IPrinterCommand command) throws PrinterException {
        responseData = null;
        try {
            int count = 0;
            if (command.getCode() != null)
                count += command.getCode().length;
            if (command.getData() != null)
                count += command.getData().length;

            byte[] buf = new byte[count];
            int index = 0;

            if (command.getCode() != null) {
                for (byte b : command.getCode()) {
                    buf[index++] = b;
                }
            }

            if (command.getData() != null) {
                for (byte b : command.getData()) {
                    buf[index++] = b;
                }
            }
            logger.debug(">> " + byteArray2String(buf, 0, count));
            raf.write(buf);

            if (!command.isWaitResponse())
                return null;

            long start = Calendar.getInstance().getTimeInMillis();
            do {
                if (responseData == null)
                    Thread.sleep(10);
            } while (Calendar.getInstance().getTimeInMillis() - start < TIME_OUT &&
                    responseData == null);

            if (responseData == null)
                throw new PrinterException("Printer TimeOut!");
            else
                return responseData;

        } catch (Exception e) {
            if (e instanceof PrinterException)
                throw (PrinterException) e;
            logger.error(e.getMessage(), e);
            throw new PrinterException("Sending command error!");
        }
    }

    @Override
    public void sendBytes(byte[] buffer) {
        responseData = null;

        logger.debug(">> " + byteArray2String(buffer, 0, buffer.length));
        try {
            raf.write(buffer);

            // ожидаем ответа
            long start = Calendar.getInstance().getTimeInMillis();

            do {
                if (responseData == null)
                    Thread.sleep(100);
            } while (Calendar.getInstance().getTimeInMillis() - start < TIME_OUT &&
                    responseData == null);

            if (responseData == null)
                throw new PrinterException("Printer TimeOut!");
        } catch (InterruptedException | PrinterException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (raf != null) {
                    byte[] buf = new byte[4];
                    buf[0] = (byte) raf.read();

                    logger.debug("<< " + byteArray2String(buf, 0, buf.length));
                    responseData = buf;
                }
                Thread.sleep(100);
            } catch (InterruptedException | IOException e) {
                logger.error(e.getMessage(), e);
                break;
            }
        }
    }

    @Override
    public void connect() {
    }

    @Override
    public void closeConnection() {
    }

}
