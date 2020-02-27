/**
 * 
 */
package ru.custom.types;

/**
 * @author denis
 * 
 * РўРёРї РїСЂРёРЅС‚РµСЂР°
 *  
 */
public enum PrinterType
{
	/**
	 * SysFuture av-268
	 */
	AV268 ("av268"),
	
	/**
	 * Custom VKP-80 
	 */
	CUSTOM ("custom"),
	
	/**
	 * Citizen CT-S2000 
	 */
	CITIZEN ("Citizen"),
	
	/**
	 * Star TUP-900 ESC-POS 
	 */
	STARTUP ("startup");
	
	
	private String type;
	
	private PrinterType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public static PrinterType getTypeByCode(String type) {
		for (PrinterType obj : PrinterType.values()) {
			if (obj.getType().equalsIgnoreCase(type))
				return obj;
		}
		return null;
	}
}
