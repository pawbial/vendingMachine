package pl.sdacademy.vending.util;

import java.util.Arrays;

public class StringUtil {

    public static String duplicateText (String text, Integer count) {
        StringBuilder duplicatedText = new StringBuilder();
        for (int i = 0; i < count; i++) {
            duplicatedText.append(text);
        }
        return duplicatedText.toString();
    }

    public static String adjustText(String text, Integer expectedLength) {

        while (text.length() < expectedLength) {
            text = " " + text + " ";
        }
        return  text.substring(0,expectedLength);

    }


    public static String formatMoney(Long amount) {
        return formatMoneyIntegrals(amount)
                + ","
                + formatMoneyDecimals(amount);
    }

    private static String formatMoneyIntegrals(Long amount) {
        String integrals = Long.toString(amount / 100);
        StringBuilder formattedMoney = new StringBuilder();
        Integer charactersTillLastSpace = 0;
        for (int charIndex = integrals.length() - 1; charIndex >= 0; charIndex--) {
            charactersTillLastSpace++;
            formattedMoney = formattedMoney.append(integrals.charAt(charIndex));
            if (charactersTillLastSpace >= 3) {
                formattedMoney = formattedMoney.append(" ");
                charactersTillLastSpace = 0;
            }
        }
        return formattedMoney.reverse().toString().trim();
    }

    private static String formatMoneyDecimals(Long amount) {
        String decimals = Long.toString(amount % 100);
        if (decimals.length() < 2) {
            decimals = "0" + decimals;
        }
        return decimals;
    }




    public static String formatMoneyMoje (Long amount) {

        String formated = String.valueOf(amount);
        String cents = formated.substring(formated.length()-2,formated.length());
        String dollars = formated.substring(0,formated.length()-2);

        char [] formatedDollars = new char[dollars.length()+dollars.length()/3];

        for (int i = 0; i < formatedDollars.length-1; i++) {
            formatedDollars [i] = dollars.toCharArray()[i];
        }

        return Arrays.toString(formatedDollars) +","+cents;

        //TODO
    }



    public static void main(String[] args) {


        System.out.println(formatMoney(123456L));
    }
}
