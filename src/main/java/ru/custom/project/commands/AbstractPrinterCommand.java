/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 * 
 * Базовая абстрактная команда
 * Следует все команды наследовать от нее
 * 
 */
public abstract class AbstractPrinterCommand implements IPrinterCommand {

	abstract public byte[] getCode();

	public byte[] getData() {
		return null;
	}

	public boolean isWaitResponse() {
		return false;
	}
}
