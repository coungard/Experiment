/**
 * 
 */
package ru.custom.project;


import ru.custom.types.BarCodeType;
import ru.custom.types.PrintFormatType;

/**
 * @author denis
 * 
 * 
 * 
 */
public interface IPrinterManager {
	
	/**
	 * Остановка менеджера, прерывает его работу
	 * 
	 */
	public void stopManager();
	
	/**
	 * 
	 * Добавление строки в буфер печати
	 * 
	 * @param str		- добавляемая строка
	 */
	public void appendString(String str) throws PrinterException;
	
	/**
	 * 
	 * Установка комбинированной конфигурации (формата) печати 
	 * 
	 * @param format - массив форматов, итоговый формат будет сложен из них 
	 */
	public void setPrintMode(PrintFormatType[] format) throws PrinterException;

	/**
	 * 
	 * Установка конфигурации (формата) печати 
	 * 
	 * @param format - формат
	 */
	public void setPrintMode(PrintFormatType format) throws PrinterException;
	
	/**
	 * Печать содержимого текстового буфера с переводом строки
	 * 
	 */
	public void printString() throws PrinterException;
	
	/**
	 * Отрезка бумаги, выдача чека
	 * 
	 */
	public void cutPaper() throws PrinterException;
	
	
	/**
	 * Очистка текстового буфера, установка всех параметров в 
	 * значения по умолчанию
	 * 
	 */
	public void resetSettings() throws PrinterException;
	
	
	
	/**
	 * 
	 * Проверка наличия бумаги, рекомендуется выполнять 
	 * каждый раз перед печатью
	 * 
	 * Если возвращается true - все хорошо
	 * Если false - нет бумаги
	 * Если вылетает PrinterException - принтер не работает
	 * 
	 * ! Некоторые принтеры не работают без бумаги, поэтому
	 * ! при ее отсутствии так же вылетит эксепшен
	 * 
	 */
	public boolean checkPaper() throws PrinterException;

	/**
	 * Печать штрих кода. 
	 * Добавляет бар код в буффер, осуществляет печать и перевод строки
	 * 
	 * @param str - текст
	 * @param barCodeType - кодировка баркода
	 * @param height - высота, если 0 - то дефолтная
	 */
	public void printBarCode(String str, BarCodeType barCodeType, byte height) throws PrinterException;
	
	/**
	 * Печать загруженной растровой картинки
	 * 
	 */
	public void printDownloadedBitImg() throws PrinterException;
	
	/**
	 * 
	 * Порт на котором работает принтер
	 * 
	 */
	public String getPort();

	public void startOperation() throws PrinterException;
	public void stopOperation();
	
	public void printImage(String base64) throws PrinterException;
	
	public void printQRCode(String str) throws PrinterException;
	public void printQRCodeByGeneration(String str) throws PrinterException;
}
