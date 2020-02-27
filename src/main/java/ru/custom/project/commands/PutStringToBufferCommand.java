/**
 *
 */
package ru.custom.project.commands;

import java.io.UnsupportedEncodingException;

/**
 * @author denis
 *
 * Помещение текста в буффер
 *
 */
public class PutStringToBufferCommand extends AbstractPrinterCommand {

	private String text;

	public PutStringToBufferCommand(String text) {
		super();
		this.text = text;
	}

	@Override
	public byte[] getCode() {
		return null;
	}

	@Override
	public byte[] getData() {

		byte[] buf = null;
		try {
			buf = text.getBytes("Cp866");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			buf = text.getBytes();
		}

		return buf;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}



}
