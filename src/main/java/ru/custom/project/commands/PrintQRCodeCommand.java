/**
 * 
 */
package ru.custom.project.commands;

import java.io.UnsupportedEncodingException;

/**
 * @author denis
 * 
 * Печать QR кода
 *
 */
public class PrintQRCodeCommand extends AbstractPrinterCommand {

	private String text;
	
	public PrintQRCodeCommand(String text) {
		super();
		this.text = text != null ? text : "";
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1B, 0x71, 
					4, // S - количество точек 1-20, чтение не гарантировано при количестве 1,2,3
					2, // E - уровень корректировки ошибок 0-3
					0, // V - номер модели. 0 - автоматически, допустимо 0-40, чем больше - тем больше код
					0, // M - шаблон маски, 0-8. 0 - выберется оптимальный вроде
					(byte)text.length(), 0 // n1 n2:The amount of data Byte. ・Select the amount of data Byte(n2*256+n1).
					};
	}
	
	@Override
	public byte[] getData() {
		
		byte[] buf = null;
		
		try {
			buf = text.getBytes("IBM437");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			buf = text.getBytes();
		}
		
		return buf;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
