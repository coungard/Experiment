/**
 * 
 */
package ru.custom.project.types;

/**
 * @author denis
 *
 */
public enum EjectorCommandType {
	
	RETRACT ((byte)0x02),
	EJECT ((byte)0x05),
	DISABLE_CONTINUOUS_MODE ((byte)0x12),
	ENABLE_CONTINUOUS_MODE ((byte)0x14);
	
	
	private byte code;
	
	private EjectorCommandType(byte code) {
		this.code = code;
	}
	
	public byte getCode() {
		return code;
	}
	
	public static EjectorCommandType getTypeByCode(byte code) {
		for (EjectorCommandType obj : EjectorCommandType.values()) {
			if (obj.getCode() == code)
				return obj;
		}
		return null;
	}
}
