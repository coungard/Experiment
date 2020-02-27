/**
 * 
 */
package ru.custom.project.commands;


import ru.custom.project.types.PositionAlignmentType;

/**
 * @author denis
 *
 * Команда управления форматированием
 * 
 * 
 */
public class PositionAlignmentCommand extends AbstractPrinterCommand {

	private PositionAlignmentType mode = null;
	
	
	public PositionAlignmentCommand(PositionAlignmentType mode) {
		super();
		this.mode = mode;
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1B, 0x61};
	}

	@Override
	public byte[] getData() {
		return new byte[] {mode != null ? mode.getCode() : 0x00};
	}

	public PositionAlignmentType getMode() {
		return mode;
	}

	public void setMode(PositionAlignmentType mode) {
		this.mode = mode;
	}
	
	
}
