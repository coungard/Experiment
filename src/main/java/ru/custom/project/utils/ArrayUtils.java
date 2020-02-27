package ru.custom.project.utils;

import org.apache.log4j.Logger;

public class ArrayUtils 
{
	private static final Logger logger = Logger.getLogger(ArrayUtils.class);
	
	public static byte getBitFromArray(byte[] array, int index)
    {
        return getBitFromByte(array[index/8],index%8);
    }

    public static byte getBitFromByte(byte b, int index)
    {
        return (byte)(b>>index&1);
    }

    public static void setBitInArray(byte[] array, int index, byte value)
    {        
        array[index/8] = setBitInByte(array[index/8],index%8,value);
    }

    public static byte setBitInByte(byte b, int index, byte value)
    {
        if(value==1)
            return (byte)(b|(1<<index));
        else
            return (byte)(b&~(1<<index));
    }

    public static void printByte(byte b)
    {
        StringBuffer buf = new StringBuffer();
    	for(int i=7;i>=0;i--)
            buf.append((b>>i)&1);
    	logger.debug(buf.toString());
    }

    public static void printArray(byte[] array)
    {
        for(int j=0; j<array.length; j++)
        {
            byte b = array[j];
            printByte(b);
            //logger.debug(" ");
        }
    }
}
