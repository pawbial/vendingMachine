package pl.sdacademy.vending.util;

public class StringUtil {

    public static String adjustText(String text, Integer expectedLength) {

        while (text.length() < expectedLength) {
            text = " " + text + " ";
        }
        return  text.substring(0,expectedLength);

    }


    public static String formatMoney (Long amount) {
        return "";
    }
}
