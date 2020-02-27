/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 * Установка/отмена режима выделения
 * 
 * Установка текущей конфигурации печати: печать с выделением/печать без выделения.
 * Выбранная настройка распространяется на все символы, вводящиеся в текстовый буфер 
 * в период действия данных настроек, независимо от выбранного состояния на момент подачи 
 * команды печати. 
 * 
 */
public class SetBoldModeCommand extends AbstractPrinterCommand {

	boolean isBoldMode = false;
	
	public SetBoldModeCommand(boolean isBoldMode) {
		super();
		this.isBoldMode = isBoldMode;
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1B, 0x45};
	}

	@Override
	public byte[] getData() {
		return new byte[] {isBoldMode ? (byte)0x01 : (byte)0x00};
	}
	
	public boolean isBoldMode() {
		return isBoldMode;
	}

	public void setBoldMode(boolean isBoldMode) {
		this.isBoldMode = isBoldMode;
	}
}
