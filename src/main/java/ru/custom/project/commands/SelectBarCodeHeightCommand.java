/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 *
 * Установка высоты бар кода
 * 
 * Если передается height = 0, то выставляется дефолтная высота в 162 точки
 * 
 */
public class SelectBarCodeHeightCommand extends AbstractPrinterCommand {

	private byte height;
	
	public SelectBarCodeHeightCommand(byte height) {
		super();
		this.height = height;
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1D, 0x68};
	}
	
	@Override
	public byte[] getData() {
		return new byte[] {height > 0 ? height : (byte)162};
	}

	public byte getHeight()
	{
		return height;
	}

	public void setHeight(byte height)
	{
		this.height = height;
	}

}
