/**
 * 
 */
package ru.custom.project.commands;



import ru.custom.project.types.PrinterBarCodeType;

import java.io.UnsupportedEncodingException;

/**
 * @author denis
 * 
 * Печать штрих кода
 *
 */
public class PrintBarCodeCommand extends AbstractPrinterCommand {

	private String text;
//	private com.sotas.terminal.device.printer.custom.types.PrinterBarCodeType encoding;
	private PrinterBarCodeType encoding;

	public PrintBarCodeCommand(String text, PrinterBarCodeType encoding) {
		super();
		this.text = text != null ? text : "";
		this.encoding = encoding != null ? encoding : PrinterBarCodeType.CODE128;
		
		if (encoding == PrinterBarCodeType.CODE128)
			this.text = "{B" + this.text;
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1D, 0x6B, encoding.getCode(), (byte)text.length()};
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
