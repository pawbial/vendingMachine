package pl.sdacademy.vending;

import pl.sdacademy.vending.controller.CustomerOperationsController;
import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.util.Configuration;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    Configuration configuration = new Configuration();
    VendingMachine vendingMachine = new VendingMachine(configuration);
    CustomerOperationsController customerOperationsController = new CustomerOperationsController(vendingMachine);


    private void startApplication() {
        while (true) {
            customerOperationsController.printMachine();
            printMenu();
            try {

                UserMenuSelection userSelection = getUserSelection();

                switch (userSelection) {
                    case BUY_PRODUCT:
                        //1. pobrać symbol tacki
                        //2. wywołać odpowiednią metodę z controlera (Optional buy product for symbol (String traySymbol)
                        //.3 Jeżeli udało się kupić produkt to wypisujemy, że udało się kupić oraz nazwę
                        //4. Jeżeli nit ro wyświetlamy brak produktu
                        System.out.println("Please select tray symbol");
                        String userProductSelection = new Scanner(System.in).nextLine();
                        Optional<Product> product = customerOperationsController.buyProductForSymbol(userProductSelection);
                        if (product.isPresent()) {
                            System.out.println("Sales completed: " + product.get().getName());
                        } else
                            System.out.println("Out of product");

                        break;
                    case EXIT:
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Ibvalid selection");
                }
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void printMenu() {
        UserMenuSelection[] allPosiblleSelections = UserMenuSelection.values();

        for (UserMenuSelection menuOption : allPosiblleSelections) {
            System.out.println(menuOption.getOptionNumber() + ". " + menuOption.getOptionText());
        }
    }

    private UserMenuSelection getUserSelection() {
        System.out.print("> Your selection: ");
        String userSelection = new Scanner(System.in).nextLine();
        try {
            Integer menuNumber = Integer.valueOf(userSelection);
            return UserMenuSelection.selectionForOptionNumber(menuNumber);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid selection fomat");
        }

    }

    public static void main(String[] args) {

        new Main().startApplication();

    }

}
