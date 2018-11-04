package pl.sdacademy.vending;

public enum UserMenuSelection {

    BUY_PRODUCT (1, "Buy product"),
    EXIT (9, "Exit");

    private final Integer optopnNumber;
    private final String optionText;

    UserMenuSelection(Integer OptopnNumber, String optionText) {

        optopnNumber = OptopnNumber;
        this.optionText = optionText;
    }

    public Integer getOptopnNumber() {
        return optopnNumber;
    }

    public String getOptionText() {
        return optionText;
    }
}
