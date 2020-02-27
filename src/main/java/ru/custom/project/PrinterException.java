/**
 * 
 */
package ru.custom.project;

/**
 * @author denis
 * 
 * РћС€РёР±РєР° РІ СЂР°Р±РѕС‚Рµ СЃ РїСЂРёРЅС‚РµСЂРѕРј
 *
 */
public class PrinterException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2713321795974168983L;

	/**
	 * 
	 */
	public PrinterException() {
		super();
	}

	/**
	 * @param message
	 */
	public PrinterException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PrinterException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PrinterException(String message, Throwable cause) {
		super(message, cause);
	}

}
