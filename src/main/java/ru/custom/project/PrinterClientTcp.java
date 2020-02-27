/**
 * 
 */
package ru.custom.project;

import org.apache.log4j.Logger;
import ru.custom.project.commands.IPrinterCommand;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;

import static ru.custom.project.PrinterClient.byteArray2String;


/**
 * @author denis
 * 
 */
public class PrinterClientTcp implements IPrinterClient {
	
	private static Logger logger = org.apache.log4j.Logger.getLogger(PrinterClientTcp.class);

	private String ip;
	private Integer port;
    
	private Socket s = null;
	private OutputStream os;
    private InputStream is;
	
    private byte[] responseData;
    
    // время ожидания команды
    private static final long TIME_OUT = 500;
	
	
	public PrinterClientTcp(String ip, Integer port) {
		super();
		this.ip = ip;
		this.port = port;
		
		logger.debug("Printer client tcp initialized");
	}
	
	public void connect() throws PrinterException {
		
		logger.debug("Try open port " + ip + ":" + port);
		
		try {
			logger.debug("Try open port " + port);
			
			s = new Socket(ip, port);
			
			os = s.getOutputStream();
            is = s.getInputStream();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new PrinterException("port initialization error", e);
		}
	}
	
	
	/**
	 * 
	 */
	public void closeConnection() {
		try {
			if (s != null)
				s.close();
			if (os != null)
				os.close();
			if (is != null)
				is.close();
			
			Thread.sleep(50);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		s = null;
	}
	
	/**
	 * Остановка работы клиента (освобождение порта)
	 * 
	 */
	public void close() {
		closeConnection();
	}
	
	/**
	 * Отправка команды
	 * 
	 * @throws PrinterException 
	 */
	public synchronized byte[] sendCommand(IPrinterCommand command) throws PrinterException {
	
		responseData = null;
			
		try {
			
			int length = 0;
			if (command.getCode() != null) {
				length += command.getCode().length;
			}
			if (command.getData() != null) {
				length += command.getData().length;
			}
			
			byte[] buf = new byte[length];
			
			if (command.getCode() != null) {
				System.arraycopy(command.getCode(), 0, buf, 0, command.getCode().length);
			}
			if (command.getData() != null && command.getData().length > 0) {
				System.arraycopy(command.getData(), 0, buf, command.getCode() != null ? command.getCode().length : 0, command.getData().length);
			}

			logger.debug(">> " + byteArray2String(buf, 0, buf.length));
			
			os.write(buf);
			
			if (!command.isWaitResponse()) {
				return null;
			}
			
			responseData = waitResponse();
			
			if (responseData == null) {
				throw new PrinterException("Printer TimeOut");
			}
			else {
				return responseData;
			}

		} catch (Exception e) {
			if (e instanceof PrinterException)
				throw (PrinterException)e;
			
			logger.error(e.getMessage(), e);
			throw new PrinterException("Sending command error");
		}
	}

	@Override
	public void sendBytes(byte[] buffer) {

	}

	private byte[] waitResponse() throws InterruptedException, IOException {
		long start = Calendar.getInstance().getTimeInMillis();
		do  {
			if (is.available() > 0) {
				
				byte buffer[] = new byte[256];
				
				int r = is.read(buffer, 0, is.available());
				
				byte[] buf = new byte[r];
				System.arraycopy(buffer, 0, buf, 0, r);
				
				logger.debug("<< " + byteArray2String(buf, 0, buf.length));
				
				return buf;						
			}
			
			Thread.sleep(15);
		} 
		while (Calendar.getInstance().getTimeInMillis() - start < TIME_OUT && responseData == null);
		
		return null;
	}
	
}
