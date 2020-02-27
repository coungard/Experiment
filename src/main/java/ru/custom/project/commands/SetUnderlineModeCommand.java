/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 *
 * Установка/отмена режима подчеркивания
 * 
 * Установка текущей конфигурации печати: печать с подчеркиванием/печать без подчеркивания.
 * Выбранная настройка распространяется на все символы, вводящиеся в текстовый буфер 
 * в период действия данных настроек, независимо от выбранного состояния на момент подачи 
 * команды печати. 
 * 
 */
public class SetUnderlineModeCommand extends AbstractPrinterCommand {

	boolean isUnderlineMode = false;
	
	public SetUnderlineModeCommand(boolean isUnderlineMode) {
		super();
		this.isUnderlineMode = isUnderlineMode;
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1B, 0x2D};
	}

	@Override
	public byte[] getData() {
		return new byte[] {isUnderlineMode ? (byte)0x01 : (byte)0x00};
	}
	
	public boolean isUnderlineMode() {
		return isUnderlineMode;
	}

	public void setUnderlineMode(boolean isUnderlineMode) {
		this.isUnderlineMode = isUnderlineMode;
	}

	
}
