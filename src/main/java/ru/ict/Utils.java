package ru.ict;

import org.apache.commons.codec.binary.Hex;

import java.nio.ByteBuffer;

/**
 * Created by artur, Date: 06.01.19, Time: 14:39
 */
class Utils {
    static byte getChecksum(byte fromCode) {
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

    static void createCase(byte[] message) {
        switch (message[2]) {
            case 0x01:
                System.out.println("Команда включить UCA     " + byteArrayToHex(message));
                break;
            case 0x02:
                System.out.println("Команда выключить UCA     " + byteArrayToHex(message));
                break;
            case 0x03:
                System.out.println("Команда на запрос версии UCA     " + byteArrayToHex(message));
                break;
            case 0x11:
                System.out.println("Запрос статуса     " + byteArrayToHex(message));
                break;
            default:
                System.out.println(byteArrayToHex(message));
        }
    }

    static void createResponse(byte command) {
        byte[] bytes = IctClient.receivedData;
        switch (command) {
            case 0x03:
                System.out.println("Версия UCA: " + allocate(bytes) + "      " + byteArrayToHex(bytes));
                break;
            case 0x11:
                switch (bytes[2]) {
                    case 0x11:
                        System.out.println("Статус: Idling     " + byteArrayToHex(bytes));
                        break;
                    case 0x12:
                        System.out.println("Статус: Accepting     " + byteArrayToHex(bytes));
                        break;
                    case 0x14:
                        System.out.println("Статус: Disable     " + byteArrayToHex(bytes));
                        break;
                }
                break;
            case 0x4B:
                System.out.println("REJECT RESPONSE!");
                break;
            default:
                System.out.println(byteArrayToHex(bytes));
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
        sb.append("]");
        return sb.toString();
    }
}
