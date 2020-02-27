/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 * 
 * Команда выбора шрифта: заводской/пользовательский
 * 
 */
public class SelectFontCommand extends AbstractPrinterCommand {

	private boolean isUserFont = false;
	
	public SelectFontCommand(boolean isUserFont) {
		super();
		this.isUserFont = isUserFont;
	}

	@Override
	public byte[] getCode() {
		return new byte[] {0x1B, 0x25};
	}
	
	@Override
	public byte[] getData() {
		return new byte[] {isUserFont ? (byte)0x01 : (byte)0x00};
	}

	public boolean isUserFont() {
		return isUserFont;
	}

	public void setUserFont(boolean isUserFont) {
		this.isUserFont = isUserFont;
	}

}
