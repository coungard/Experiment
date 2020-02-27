/**
 * 
 */
package ru.custom.project.commands;


/**
 * @author denis
 * 
 */
public class SetLineSpacingCommand extends AbstractPrinterCommand {

	private Byte range;
	
	public SetLineSpacingCommand(Byte range) {
		super();
		this.range = range;
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1B, 0x33};
	}

	@Override
	public byte[] getData() {
		return new byte[] {range != null ? range : (byte)0x40};
	}
}
