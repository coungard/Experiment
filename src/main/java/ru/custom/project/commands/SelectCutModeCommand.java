/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 *
 * Частичный отрез бумаги
 * 
 */
public class SelectCutModeCommand extends AbstractPrinterCommand {

	private boolean isTotalCut = true;
	
	public SelectCutModeCommand(boolean isTotalCut) {
		super();
		this.isTotalCut = isTotalCut;
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1D, 0x56};
	}

	@Override
	public byte[] getData() {
		return new byte[] {(isTotalCut ? (byte)0x0 : (byte)0x65)};
	}
}
