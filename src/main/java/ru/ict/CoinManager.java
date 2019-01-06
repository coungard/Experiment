package ru.ict;

import jssc.SerialPortException;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by artur, Date: 06.01.19, Time: 14:28
 */
public class CoinManager {
    public static void main(String[] args) throws SerialPortException, InterruptedException, InvocationTargetException {
        IctClient ictClient = new IctClient("/dev/ttyS0");
        ictClient.startAccept();
    }
}
