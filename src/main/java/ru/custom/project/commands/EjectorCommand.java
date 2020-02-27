/**
 * 
 */
package ru.custom.project.commands;


import ru.custom.project.types.EjectorCommandType;

/**
 * @author denis
 *
 * Команда управления выдачей
 * 
 * 
 */
public class EjectorCommand extends AbstractPrinterCommand {

	private EjectorCommandType mode = null;
	
	
	public EjectorCommand(EjectorCommandType mode) {
		super();
		this.mode = mode;
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1D, 0x65};
	}

	@Override
	public byte[] getData() {
		return new byte[] {mode != null ? mode.getCode() : 0x00};
	}

	public EjectorCommandType getMode() {
		return mode;
	}

	public void setMode(EjectorCommandType mode) {
		this.mode = mode;
	}
	
	
}
