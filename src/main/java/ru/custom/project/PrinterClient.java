/**
 * 
 */
package ru.custom.project;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.apache.log4j.Logger;
import ru.custom.project.commands.IPrinterCommand;

import java.util.Calendar;
import java.util.Formatter;

/**
 * @author denis
 * 
Клиент для работы с принтером.
 * Осуществляет непосредственную работу - подключается к указанному порту, 
 * работает с потоками. Занимается отправкой команд, читает ответные.
 * 
 */
public class PrinterClient implements SerialPortEventListener, IPrinterClient {
	
	private static Logger logger = Logger.getLogger(PrinterClient.class);

	private String port;
	private SerialPort serialPort;
    
    private byte[] responseData;
    
    // время ожидания команды
    private static final long TIME_OUT = 500;
	
	
	public  PrinterClient(String port, Integer speed)
	{
		super();
		this.port = port;
		init(speed);

		// Задержка на инициализацию принтера
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			logger.debug(e.getMessage(), e);
		}
		
		logger.debug("Initialized");
	}
	
	/**
	 * Метод инициализации
	 * Ищем нужный порт среди списка доступных. Если находим открываем порт и настраиваем
	 * для работы
	 * Если не находим завершаем работу и выводим в лог сообщение
	 * 
	 */
	private void init(Integer speed)
	{
		boolean portFound = false;
	
		
		logger.debug("Try create port " + port);
		
		serialPort = new SerialPort(port);
				    
		try 
		{
			logger.debug("Try open port " + port);
			
			serialPort.openPort();

		    serialPort.setParams(speed != null ? speed : 115200, 
			 	SerialPort.DATABITS_8,
			  	SerialPort.STOPBITS_1,
		   		SerialPort.PARITY_NONE);
		   		
		   		
//            //Включаем аппаратное управление потоком
//            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
//                                          SerialPort.FLOWCONTROL_RTSCTS_OUT);

		    serialPort.addEventListener(this, SerialPort.MASK_RXCHAR);
            
	   		serialPort.setRTS(true);
	   		Thread.sleep(100);
	   		serialPort.setRTS(false);
	   		Thread.sleep(3000);
	   	
	   		portFound = true;
	   		
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	    
	    
		if (!portFound) {
		    logger.error("port " + port + " not found.");
		} 
		
	}
	
	
	/**
	 * Закрыть клиента (закрываем порт)
	 * 
	 */
	public void close()
	{
		if (serialPort != null) {
			try {
				serialPort.closePort();
			} catch (SerialPortException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		serialPort = null;
	}

	public static String byteArray2String(byte[] buf, int offset, int count) {
		Formatter formatter = new Formatter();
		for (int i = offset; i < count; i++)
			formatter.format("%02X ", buf[i]);

		return formatter.toString();
	}

	public synchronized void sendBytes(byte[] buffer) {
		responseData = null;

		logger.debug(">> " + byteArray2String(buffer, 0, buffer.length));
		try {
			serialPort.writeBytes(buffer);

			// ожидаем ответа
			long start = Calendar.getInstance().getTimeInMillis();

//			logger.debug("start time = " + start + " timeOut = " + TIME_OUT);
			do
			{
				if (responseData == null)
					Thread.sleep(100);

//				logger.debug("::: " + (Calendar.getInstance().getTimeInMillis() - start) + " " + (Calendar.getInstance().getTimeInMillis() - start < TIME_OUT));
			}
			while (Calendar.getInstance().getTimeInMillis() - start < TIME_OUT &&
					responseData == null);

//			logger.debug("respDataLength = " + (responseData == null ? "null" : responseData.length));

			if (responseData == null)
				throw new PrinterException("Printer TimeOut!");
		} catch (SerialPortException | InterruptedException | PrinterException e) {
			e.printStackTrace();
		}


	}


	/**
	 * Отправка команды на принтер
	 * 
	 * @param command - отправляемая команда
	 * @return byte[] - ответ принтера
	 * 
	 * @throws PrinterException 
	 */
	public synchronized byte[] sendCommand(IPrinterCommand command) throws PrinterException
	{
		responseData = null;
		
		try {
			
			int count = 0;
			if (command.getCode() != null)
				count += command.getCode().length; 
			if (command.getData() != null)
				count += command.getData().length;
			
			byte[] buf = new byte[count];
			int index = 0;
			
			if (command.getCode() != null)
			{
				for (byte b : command.getCode()) {
					buf[index++] = b;
				}
			}
			
			if (command.getData() != null)
			{
				for (byte b : command.getData()) {
					buf[index++] = b;
				}
			}

			logger.debug(">> " + byteArray2String(buf, 0, count));
			
			serialPort.writeBytes(buf);
			
			if (!command.isWaitResponse())
				return null;
			
			// ожидаем ответа 
			long start = Calendar.getInstance().getTimeInMillis();
			
//			logger.debug("start time = " + start + " timeOut = " + TIME_OUT);
			do 
			{
				if (responseData == null)
					Thread.sleep(100);
				
//				logger.debug("::: " + (Calendar.getInstance().getTimeInMillis() - start) + " " + (Calendar.getInstance().getTimeInMillis() - start < TIME_OUT));
			} 
			while (Calendar.getInstance().getTimeInMillis() - start < TIME_OUT &&
					responseData == null);
			
//			logger.debug("respDataLength = " + (responseData == null ? "null" : responseData.length));
			
			if (responseData == null)
				throw new PrinterException("Printer TimeOut!");
			else
				return responseData;

		} catch (Exception e) {
			if (e instanceof PrinterException)
				throw (PrinterException)e;
			
			logger.error(e.getMessage(), e);
			throw new PrinterException("Sending command error!");
		}
	}
	
	/* (non-Javadoc)
	 * @see gnu.io.SerialPortEventListener#serialEvent(gnu.io.SerialPortEvent)
	 * 
	 * Слушаем данные от принтера. Как только появляется событие DATA_AVAILABLE,
	 * так начинаем читать данные из потока и разбирать	 * 
	 * 
	 */
	public void serialEvent(SerialPortEvent event)
	{
				
		switch (event.getEventType()) {
	
			case SerialPortEvent.CTS:
				
			case SerialPortEvent.DSR:
				break;
		
			case SerialPortEvent.RXCHAR:
			try {
				
				if (event.getEventValue() > 0)
				{
					byte[] buf = serialPort.readBytes();
					
					logger.debug("<< " + byteArray2String(buf, 0, buf.length));
					
					responseData = buf;
				}

		     } catch (Exception e) {
		    	 logger.error(e.getMessage(), e);
		     }
		}
    }

	@Override
	public void connect() throws PrinterException {
	}

	@Override
	public void closeConnection() {
	}
}
