/**
 * 
 */
package ru.custom.project.types;

/**
 * @author denis
 *
 */
public enum StringFormatType {
	
	NORMAL ((byte)0x00),
	FONT_B ((byte)0x01),
	BOLD ((byte)0x08),
	DOUBLE_SIZE_Y ((byte)0x10),
	DOUBLE_SIZE_X ((byte)0x20),
	ITALIC ((byte)0x40),
	UNDERLINE  ((byte)0x80);
	
	
	private byte code;
	
	private StringFormatType(byte code) {
		this.code = code;
	}
	
	public byte getCode() {
		return code;
	}
	
	public static StringFormatType getTypeByCode(byte code) {
		for (StringFormatType obj : StringFormatType.values()) {
			if (obj.getCode() == code)
				return obj;
		}
		return null;
	}
}
