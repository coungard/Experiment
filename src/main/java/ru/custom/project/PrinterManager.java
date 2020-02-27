/**
 *
 */
package ru.custom.project;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.log4j.Logger;
import ru.custom.project.commands.*;
import ru.custom.project.types.PositionAlignmentType;
import ru.custom.project.types.StringFormatType;
import ru.custom.types.BarCodeType;
import ru.custom.types.PrintFormatType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

/**
 * @author denis
 *
 * Менеджер позволяющий работать с принтером, выполнять определенные действия
 * Обладает набором методов необходимых для полноценной работы
 */
public class PrinterManager implements IPrinterManager {
    private static Logger logger = Logger.getLogger(PrinterManager.class);

    private String port = "COM1";
    private IPrinterClient printerClient;

    public PrinterManager(String port, Integer speed, String transport, String printerTcpIp, Integer printerTcpPort) {
        super();

        logger.debug("Init printer manager");

        if (port != null)
            this.port = port;

        if (transport != null && transport.equals("tcp")) {
            printerClient = new PrinterClientTcp(printerTcpIp, printerTcpPort);
        } else if (port != null) {
            if (port.startsWith("/dev/usb/") || port.equalsIgnoreCase("/dev/printer")) {
                printerClient = new PrinterFOSClient(port);
            } else {
                printerClient = new PrinterClient(port, speed);
            }
        }
        try {
            startOperation();
            resetSettings();
        } catch (PrinterException e) {
            logger.error(e.getMessage(), e);
        } finally {
            stopOperation();
        }
    }

    public PrinterManager(String port) {
        this(port, null, null, null, null);
    }

    /**
     * Остановка менеджера, прерывает его работу
     */
    public void stopManager() {
        logger.debug("Stop printer manager");

        if (printerClient != null)
            printerClient.close();
    }

    /**
     * Добавление строки в буфер печати
     *
     * @param str        - добавляемая строка
     */
    public void appendString(String str) throws PrinterException {
        printerClient.sendCommand(new PutStringToBufferCommand(str));
    }

    /**
     * Установка комбинированной конфигурации (формата) печати
     *
     * @param format - массив форматов, итоговый формат будет сложен из них
     */
    public void setPrintMode(PrintFormatType[] format) throws PrinterException {
        byte sum = 0;
        boolean isInverse = false;

        if (format != null) {
            for (PrintFormatType type : format) {
                if (type != null) {
                    int code = 0;

                    switch (type) {
                        case FONT_B:
                            code = StringFormatType.FONT_B.getCode();
                            break;
                        case DOUBLE_SIZE_X:
                            code = StringFormatType.DOUBLE_SIZE_X.getCode();
                            break;
                        case DOUBLE_SIZE_Y:
                            code = StringFormatType.DOUBLE_SIZE_Y.getCode();
                            break;
                        case BOLD:
                            code = StringFormatType.BOLD.getCode();
                            break;
                        case INVERSE:
                            isInverse = true;
                            break;
                        case UNDERLINE:
                            code = StringFormatType.UNDERLINE.getCode();
                            break;

                        default:
                            break;
                    }
                    sum += code;
                }
            }
        }
        printerClient.sendCommand(new SetInverseModeCommand(isInverse));
        printerClient.sendCommand(new SetPrintModeCommand(sum));
    }

    /**
     * Установка конфигурации (формата) печати
     *
     * @param format - формат
     */
    public void setPrintMode(PrintFormatType format) throws PrinterException {
        setPrintMode(new PrintFormatType[]{format});
    }

    /**
     * Печать содержимого текстового буфера с переводом строки
     */
    public void printString() throws PrinterException {
        printerClient.sendCommand(new PrintLNCommand());
    }

    /**
     * Отрезка бумаги
     */
    public void cutPaper() throws PrinterException {
        printerClient.sendCommand(new FormFeedCommand());
    }


    /**
     * Очистка текстового буфера, установка всех параметров в значения по умолчанию
     */
    public void resetSettings() throws PrinterException {
        printerClient.sendCommand(new InitializationCommand());
        printerClient.sendCommand(new SelectCharCodeTable());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Загрузка символов пользовательского шрифта
     *
     * Описание формата:
     * @see UploadUserFontCommand
     *
     * @param fontData - загружаемые данные (шрифт)
     */
    public void uploadUserFont(byte[] fontData) throws PrinterException {
        printerClient.sendCommand(new UploadUserFontCommand(fontData));
    }


    /**
     * Команда выбора шрифта: заводской/пользовательский
     *
     * @param isUserFont - выбор шрифта, пользовательский или нет
     */
    public void selectFont(boolean isUserFont) throws PrinterException {
        printerClient.sendCommand(new SelectFontCommand(isUserFont));
    }


    /**
     * Проверка наличия бумаги
     */
    public boolean checkPaper() throws PrinterException {
        byte[] status = printerClient.sendCommand(new StatusRequestCommand());
        if (status != null && status.length > 0) {
            if ((status[0] & 0x0C) == 0)
                return true;
        }
        return false;
    }

    public void setCyrillic() throws PrinterException {
        printerClient.sendCommand(new SelectCharCodeTable());
    }

    /**
     * Печать загруженной растровой картинки
     */
    public void printDownloadedBitImg() throws PrinterException {
        printerClient.sendCommand(new PrintDownloadedBitImgCommand());
    }

    /**
     * Порт на котором работает принтер
     */
    public String getPort() {
        return port;
    }


    @Override
    public void startOperation() throws PrinterException {
        printerClient.connect();
    }

    @Override
    public void stopOperation() {
        printerClient.closeConnection();
    }

    public void setAlignment(PositionAlignmentType position) throws PrinterException {
        printerClient.sendCommand(new PositionAlignmentCommand(position));
    }

    @Override
    public void printBarCode(String str, BarCodeType barCodeType, byte height) throws PrinterException {

    }

    @Override
    public void printImage(String base64) throws PrinterException {

    }

    @Override
    public void printQRCode(String str) throws PrinterException {

    }

    @Override
    public void printQRCodeByGeneration(String str) throws PrinterException {

    }
}
 