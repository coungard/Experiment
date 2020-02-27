/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 *
 * Печать содержимого текстового буфера с переводом строки
 * 
 */
public class PrintLNCommand extends AbstractPrinterCommand {

	public byte[] getCode() {
		return new byte[] {0x0A};
	}
}
