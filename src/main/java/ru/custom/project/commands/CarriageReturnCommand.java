/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 *
 * Возврат каретки
 * 
 */
public class CarriageReturnCommand extends AbstractPrinterCommand {

	@Override
	public byte[] getCode() {
		return new byte[] {0x0D};
	}

}
