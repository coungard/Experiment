/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 *
 * Выбор таблицы кодировки
 * Необходимо установить кириллицу
 *  
 */
public class SelectCharCodeTable extends AbstractPrinterCommand {

	public byte[] getCode() {
		return new byte[] {0x1B, 0x74};
	}
	
	@Override
	public byte[] getData() {
		// cyrillic - code 0x11
		return new byte[] {0x11};
	}
}
