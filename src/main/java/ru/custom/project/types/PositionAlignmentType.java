/**
 * 
 */
package ru.custom.project.types;

/**
 * @author denis
 *
 */
public enum PositionAlignmentType {
	
	LEFT ((byte)0x00),
	CENTER ((byte)0x01),
	RIGHT ((byte)0x2);

	private byte code;
	
	private PositionAlignmentType(byte code) {
		this.code = code;
	}
	
	public byte getCode() {
		return code;
	}
	
	public static PositionAlignmentType getTypeByCode(byte code) {
		for (PositionAlignmentType obj : PositionAlignmentType.values()) {
			if (obj.getCode() == code)
				return obj;
		}
		return null;
	}
}
