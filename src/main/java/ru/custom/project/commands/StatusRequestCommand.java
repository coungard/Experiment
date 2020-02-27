/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 *
 * Запрос статуса устройства
 * Отсылается байт текущего статуса устройства
 *
 */
public class StatusRequestCommand extends AbstractPrinterCommand {

	@Override
	public byte[] getCode() {
		return new byte[] {0x1B, 0x76};
	}

	@Override
	public boolean isWaitResponse() {
		return true;
	}

}
