/**
 * 
 */
package ru.custom.project.types;


//import com.sotas.terminal.device.printer.custom.types.BarCodeType;

import ru.custom.types.BarCodeType;

/**
 * @author denis
 *
 */
public enum PrinterBarCodeType {
	
	JAN13 ((byte)0x43, BarCodeType.JAN13),
	JAN8 ((byte)0x44, BarCodeType.JAN8),
	CODE39 ((byte)0x45, BarCodeType.CODE39),
	CODE128 ((byte)0x49, BarCodeType.CODE128);
	
	private byte code;
	private BarCodeType encoding;
	
	private PrinterBarCodeType(byte code, BarCodeType encoding) {
		this.code = code;
		this.encoding = encoding;
	}
	
	public byte getCode() {
		return code;
	}
	
	public static PrinterBarCodeType getTypeByCode(byte code) {
		for (PrinterBarCodeType obj : PrinterBarCodeType.values()) {
			if (obj.getCode() == code)
				return obj;
		}
		return null;
	}
	
	public static PrinterBarCodeType getTypeByEncoding(BarCodeType type) {
		for (PrinterBarCodeType obj : PrinterBarCodeType.values()) {
			if (obj.getEncoding() == type)
				return obj;
		}
		return null;
	}
	
	public BarCodeType getEncoding()
	{
		return encoding;
	}
}
