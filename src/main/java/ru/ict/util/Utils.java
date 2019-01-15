package ru.ict.util;


import ru.ict.protocol.IctClient;

/**
 * Created by artur, Date: 06.01.19, Time: 14:39
 */
public class Utils {
    public static byte getChecksum(byte fromCode) {
        switch (fromCode) {
            case 0x01:
                return (byte) 0x99;
            case 0x02:
                return (byte) 0x9A;
            case 0x03:
                return (byte) 0x9B;
            case 0x11:
                return (byte) 0xA9;
            default:
                System.out.println(">> UNKNOWN TYPE COMMAND");
                return 0;
        }
    }

    public static String createCase(byte[] message) {
        switch (message[2]) {
            case 0x01:
                return "Command to turn ON UCA     " + byteArrayToHex(message);
            case 0x02:
                return "Command to turn OFF UCA     " + byteArrayToHex(message);
            case 0x03:
                return "Request for version UCA     " + byteArrayToHex(message);
            case 0x11:
                return "Request for status     " + byteArrayToHex(message);
            default:
                return byteArrayToHex(message);
        }
    }

    public static String createResponse(byte command) {
        byte[] bytes = IctClient.receivedData;
        if (command == 0x03) {
            return "Версия UCA: " + allocate(bytes) + "      " + byteArrayToHex(bytes);
        } else {
            switch (bytes[2]) {
                case 0x11:
                    return "Status: Idling     " + byteArrayToHex(bytes);
                case 0x12:
                    return "Status: Accepting     " + byteArrayToHex(bytes);
                case 0x14:
                    return "Status: Disable     " + byteArrayToHex(bytes);
                case 0x16:
                    return "Status: SENSOR PROBLEMS!     " + byteArrayToHex(bytes);
                case 0x50:
                    return "(ACK)      " + byteArrayToHex(bytes);
                case 0x4B:
                    return "REJECT RESPONSE (NAK)!    " + byteArrayToHex(bytes);
                default:
                    return "Status undefined!     " + byteArrayToHex(bytes);
            }
        }
    }

    // первые 3 и последние 4 символа с getVersion системные, и их не нужно учитывать
    private static String allocate(byte[] version) {
        char[] chars = new char[version.length - 7];
        int index = 0;
        for (int i = 3; i < version.length - 4; ++i) {
            chars[index] = (char) (version[i] & 0xFF);
            index++;
        }
        return new String(chars);
    }

    private static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        sb.append("[");
        for (byte b : a)
            sb.append(String.format("%02xh ", b).toUpperCase());
        sb.deleteCharAt(sb.length() - 1).append("]");
        return sb.toString();
    }
}
