/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 *
 * Прогон листа - печать, отрез, выдача
 * 
 */
public class FormFeedCommand extends AbstractPrinterCommand {

	@Override
	public byte[] getCode() {
		return new byte[] {0x1B, 0x69, 0x0C};
	}

}
