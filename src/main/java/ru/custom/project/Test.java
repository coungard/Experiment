package ru.custom.project;

import ru.custom.project.types.PositionAlignmentType;
import ru.custom.types.PrintFormatType;

public class Test {

    private static String text = "|==============================|\n" +
            "|             ОТЧЕТ            |\n" +
            "|** 27.02.20       11:03:33  **|\n" +
            "|                              |\n" +
            "|ТЕСТ ПРЕДПРИЯТИЕ              |\n" +
            "|ЛЮБАЯ УЛИЦА 1                 |\n" +
            "|МОСКВА                        | \n" +
            "|T 19999303            B: 0112 |\n" +
            "|U 9903600181                  |\n" +
            "|                              |\n" +
            "|0052   25.02.20  15:07:07   01|\n" +
            "|*** VISA ***...9964           |\n" +
            "|53.00           (1) 308902   D|\n" +
            "|                              | \n" +
            "|0053   25.02.20  15:09:27   00|\n" +
            "|*** VISA ***...9964           |\n" +
            "|53.00           (1) 308902   D|\n" +
            "|                              |\n" +
            "|0054   25.02.20  15:17:56   01|\n" +
            "|*** VISA ***...9964           |\n" +
            "|43.00           (1) 308902   D| \n" +
            "|                              |\n" +
            "|0055   25.02.20  16:39:21   00|\n" +
            "|*** VISA ***...9964           |\n" +
            "|18.00           (1) 308902   D|\n" +
            "|                              |\n" +
            "|0056   25.02.20  16:41:35   01|\n" +
            "|*** VISA ***...9964           | \n" +
            "|71.00           (1) 308902   D|\n" +
            "|                              |\n" +
            "|0057   25.02.20  17:10:11   01|\n" +
            "|*** VISA ***...9964           |\n" +
            "|116.66          (1) 308902   D|\n" +
            "|                              |\n" +
            "|0058   26.02.20  10:36:19   01| \n" +
            "|*** VISA ***...9964           |\n" +
            "|91.66           (1) 308902   D|\n" +
            "|                              |\n" +
            "|0059   26.02.20  10:50:55   01|\n" +
            "|*** VISA ***...9964           |\n" +
            "|61.00           (1) 308902   D|\n" +
            "|                              | \n" +
            "|0060   26.02.20  10:54:27   01|\n" +
            "|*** VISA ***...9964           |\n" +
            "|291.66          (1) 308902   D|\n" +
            "|                              |\n" +
            "|0061   26.02.20  10:59:24   00|\n" +
            "|*** VISA ***...9964           |\n" +
            "|925.00          (1) 308902   D| \n" +
            "|                              |\n" +
            "|0062   26.02.20  11:01:18   00|\n" +
            "|*** VISA ***...9964           |\n" +
            "|708.33          (1) 308902   D|\n" +
            "|                              |\n" +
            "|0063   26.02.20  14:42:23   01|\n" +
            "|*** VISA ***...9964           | \n" +
            "|127.54          (1) 308902   D|\n" +
            "|                              |\n" +
            "|0064   26.02.20  15:31:48   01|\n" +
            "|*** VISA ***...9964           |\n" +
            "|68.30           (1) 308902   D|\n" +
            "|                              |\n" +
            "|ТИП ВАЛЮТЫ               РУБ. | \n" +
            "|                              |\n" +
            "|         *** VISA ***         |\n" +
            "|ПРОДАЖА                       |\n" +
            "|009      :              923.82|\n" +
            "|ОТМЕНА                        |\n" +
            "|004      :             1704.33|\n" +
            "|                              | \n" +
            "|ИТОГО                         |\n" +
            "|013      :              923.82|\n" +
            "|                              |\n" +
            "|                              |\n" +
            "|ОБЩАЯ СУММА                   |\n" +
            "|009                     923.82|\n" +
            "|                              | \n" +
            "|++++++++++++++++++++++++++++++|";

    public static void main(String[] args) throws PrinterException {
        printCheck();
    }

    private static void printCheck() throws PrinterException {
        PrinterManager printerManager = new PrinterManager("/dev/usb/lp0");


        printerManager.setPrintMode(new PrintFormatType[]{PrintFormatType.NORMAL});
        printerManager.setCyrillic();
//        printerManager.uploadUserFont(new byte[]{0x08});

        printerManager.setAlignment(PositionAlignmentType.CENTER);
        String[] parts = text.split("\n");

        for (String s : parts) {
            printerManager.appendString(s);
            printerManager.printString();
        }

        printerManager.cutPaper();
    }
}
