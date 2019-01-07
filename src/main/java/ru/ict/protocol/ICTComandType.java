package ru.ict.protocol;


public enum ICTComandType {
    EnableUCA(1),
    DisableUCA(2),
    GetUCAVersion(3),
    RequestStatus(0x11),              //11h
    StatusInsertCoin(0x12),             //18
    StatusDisable(0x14),
    RespACK(0x50),
    RespNAK(0x4B),
    StatusIdling(0x11),
    StatusSensorProblem(0x16),
    StatusFishing(0x17);


    private byte code;
    private byte nameVal;

    ICTComandType(int code) {
        this.code = (byte) (0xff & code);
    }

    public byte getCode() {
        return code;
    }

    public String getName() {
        return this.name();
    }

    public String toString() {
        return this.name() +
                '(' + getByteValue(code) + ')';
    }

    public static ICTComandType valueOf(byte b) {
        for (ICTComandType type : ICTComandType.values())
            if (type.code == b)
                return type;
        return null;
    }

    public static long getByteValue(byte b) {
        return 0xff & b;
    }
}

