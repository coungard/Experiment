/**
 * 
 */
package ru.custom.project.commands;

/**
 * @author denis
 * 
 * Команда работы с принтером
 * 
 */
public interface IPrinterCommand {

	/**
	 * 
	 * Взять код команды
	 * 
	 * @return
	 */
	public byte[] getCode();
	
	/**
	 * 
	 * Взять параметры команды
	 * 
	 * @return
	 */
	public byte[] getData();
	
	
	/**
	 * 
	 * Следует ли ждать ответа от принтера на данную команду
	 * 
	 * @return
	 */
	public boolean isWaitResponse();
	
}
