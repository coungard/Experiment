import java.util.ArrayList;
import java.util.List;

public class ByteToBinary {

    public static void main(String[] args) {

        byte aByte = (byte) -2;                                                         // -2 (signed) and 254 (unsigned)
        System.out.println("Input : " + aByte);

        // byte to an unsigned integer
        // & 0xff to prevent sign extension, no effect on positive
        int result = aByte & 0xff;

        System.out.println(result);                                                     // 254
        System.out.println(Integer.toBinaryString(result));                             // 1111 1110

        String resultWithPadZero = String.format("%32s", Integer.toBinaryString(result))
                .replace(" ", "0");

        System.out.println(resultWithPadZero);                                          // 00000000000000000000000011111110
        System.out.println(printBinary(resultWithPadZero, 8, "|"));                     // 00000000|00000000|00000000|11111110
    }

    // pretty print binary with separator
    public static String printBinary(String binary, int blockSize, String separator) {

        // split by blockSize
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return String.join(separator, result);
    }
}
