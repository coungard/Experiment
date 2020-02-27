/**
 * 
 */
package ru.custom.project.commands;


/**
 * @author denis
 * 
 * Печать загруженной картинки
 *
 */
public class PrintDownloadedBitImgCommand extends AbstractPrinterCommand {

	public PrintDownloadedBitImgCommand() {
		super();
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1D, 0x2F, 0x00};
	}
}
