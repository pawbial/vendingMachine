package pl.sdacademy.vending;

import pl.sdacademy.vending.controller.CustomerOperationsController;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.util.Configuration;

import java.util.Scanner;

public class Main {

    Configuration configuration = new Configuration();
    VendingMachine vendingMachine = new VendingMachine(configuration);
    CustomerOperationsController customerOperationsController = new CustomerOperationsController(vendingMachine);


    private void startApplication() {
        while (true) {
            customerOperationsController.printMachine();
            printMenu();
            UserMenuSelection userSelection = getUserSelection();

            switch (userSelection) {
                case BUY_PRODUCT:
                    break;
                case EXIT:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Ibvalid selection");
            }
        }
    }

    private void printMenu() {
        UserMenuSelection[] allPosiblleSelections = UserMenuSelection.values();

        for (UserMenuSelection menuOption : allPosiblleSelections) {
            System.out.println(menuOption.getOptopnNumber() + ". " + menuOption.getOptionText());
        }
    }

    private UserMenuSelection getUserSelection() {
        String userSelection = new Scanner(System.in).nextLine();
        try {
            Integer menuNumber = Integer.valueOf(userSelection);

        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    public static void main(String[] args) {

        new Main().startApplication();

    }

}
