/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 *
 * Полный отрез бумаги
 * 
 */
public class FullCutCommand extends AbstractPrinterCommand {

	@Override
	public byte[] getCode() {
		return new byte[] {0x1B, 0x69};
	}

}
