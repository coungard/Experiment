package ru.custom.project;


import ru.custom.project.commands.IPrinterCommand;

public interface IPrinterClient {

	public void connect() throws PrinterException;
	public void closeConnection();
	
	public void close();
	public byte[] sendCommand(IPrinterCommand command) throws PrinterException;

	public void sendBytes(byte[] buffer);
	
}
