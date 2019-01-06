package ru.ict;

import ru.ict.types.ICTComandType;

/**
 * Created by artur, Date: 06.01.19, Time: 14:02
 */
public class ICTCommand {
    protected ICTComandType commandType;
    protected byte[] data;


    public ICTCommand(ICTComandType commandType) {
        this.commandType = commandType;
    }

    public ICTCommand(ICTComandType commandType, byte[] data) {
        this.commandType = commandType;
        this.data = data;
    }

    public ICTComandType getCommandType() {
        return commandType;
    }

    public void setCommandType(ICTComandType commandType) {
        this.commandType = commandType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Command: " + (commandType != null ? commandType : "null") +
                "; " +
                "Data: " + (data != null ? byteArray2String(data, 0, data.length) : "null");
    }

    public String dataToString() {
        return data != null ? new String(data) : "";
    }

    private static String byteArray2String(byte[] buf, int offset, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < count; i++)
            sb.append(getByteValue(buf[i])).append(" ");

        return sb.toString();
    }

    private static long getByteValue(byte b) {
        return 0xff & b;
    }
}

