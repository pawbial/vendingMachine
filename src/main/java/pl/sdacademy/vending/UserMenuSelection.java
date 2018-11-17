package pl.sdacademy.vending;

import java.util.Arrays;

public enum UserMenuSelection {

    BUY_PRODUCT(1, "Buy product"),
    EXIT(9, "Exit");

    private final Integer optopnNumber;
    private final String optionText;

    UserMenuSelection(Integer OptionNumber, String optionText) {

        optopnNumber = OptionNumber;
        this.optionText = optionText;
    }

    public static UserMenuSelection selectionForOptionNumber(Integer requestedOptionNumber) {
//        UserMenuSelection[] values = UserMenuSelection.values();
//        for (UserMenuSelection menuSelection : values) {
//            if (menuSelection.getOptionNumber().equals(requestedOptionNumber)) {
//                return menuSelection;
//            }
//    }
//    throw new IllegalArgumentException("Unknown option number: " + requestedOptionNumber);
//      Wersja z pętlą.

        return Arrays.stream(values())
                .filter(enumValue -> enumValue.getOptionNumber().equals(requestedOptionNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown option number: " + requestedOptionNumber));
    }

    public Integer getOptionNumber() {
        return optopnNumber;
    }

    public String getOptionText() {
        return optionText;
    }
}
