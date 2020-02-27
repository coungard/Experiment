/**
 * 
 */
package ru.custom.project.commands;


/**
 * @author denis
 * 
 * 
 */
public class SetBitImageModeCommand extends AbstractPrinterCommand {

	private int width;
	private byte[] buf;
	
	public SetBitImageModeCommand(byte[] buf, int width) {
		super();
		this.buf = buf;
		this.width = width;
	}

	@Override
	public byte[] getCode() {
		
		byte xL = (byte) (width % 256);
		byte xH = (byte) (width / 256);
		
		return new byte[] {0x1B, 0x2A, 0x00, (byte) xL, (byte) xH};
	}
	
	@Override
	public byte[] getData() {
		return buf;
	}
}
