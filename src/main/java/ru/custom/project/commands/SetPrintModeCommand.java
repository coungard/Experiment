/**
 * 
 */
package ru.custom.project.commands;


/**
 * @author denis
 *
 * Комплексная настройка режима печати
 * 
 * Установка текущей конфигурации печати. Выбранные настройки
 * распространяются на все символы, вводящиеся в текстовый буфер 
 * в период действия данных настроек, независимо от выбранного 
 * состояния на момент подачи команды печати. 
 * 
 */
public class SetPrintModeCommand extends AbstractPrinterCommand {

	private byte mode;
	
	public SetPrintModeCommand(byte mode) {
		super();
		this.mode = mode;
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1B, 0x21};
	}

	@Override
	public byte[] getData() {
		return new byte[] {mode};
	}

	public byte getMode() {
		return mode;
	}

	public void setMode(byte mode) {
		this.mode = mode;
	}
	
	
}
