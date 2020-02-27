/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 *
 * Установка/отмена режима инверсии
 * 
 * Установка текущей конфигурации печати: позитивная печать (черные буквы, белый фон)/
 * негативная печать (белые буквы, черный фон). Выбранная настройка распространяется 
 * на все символы, вводящиеся в текстовый буфер в период действия данных настроек, 
 * независимо от выбранного состояния на момент подачи команды печати. 
 * 
 */
public class SetInverseModeCommand extends AbstractPrinterCommand {

	boolean isInverseMode = false;
	
	public SetInverseModeCommand(boolean isInverseMode) {
		super();
		this.isInverseMode = isInverseMode;
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1D, 0x42};
	}

	@Override
	public byte[] getData() {
		return new byte[] {isInverseMode ? (byte)0x01 : (byte)0x00};
	}
	
	public boolean isInverseMode() {
		return isInverseMode;
	}

	public void setInverseMode(boolean isInverseMode) {
		this.isInverseMode = isInverseMode;
	}

	
}
