package pl.sdacademy.vending;

import pl.sdacademy.vending.controller.CustomerOperationsController;
import pl.sdacademy.vending.controller.EmployeeController;
import pl.sdacademy.vending.controller.service.EmployeeService;
import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.repository.HardDriveVendingMachineRepository;
import pl.sdacademy.vending.service.DefaultEmployeeService;
import pl.sdacademy.vending.service.repository.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    Configuration configuration = new Configuration();
    VendingMachineRepository vendingMachineRepository = new HardDriveVendingMachineRepository(configuration);
    CustomerOperationsController customerOperationsController = new CustomerOperationsController(vendingMachineRepository);
    EmployeeService employeeService = new DefaultEmployeeService(vendingMachineRepository, configuration);
    EmployeeController employeeController = new EmployeeController(employeeService);

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
                        System.out.println("Please select tray symbol: ");
                        String userProductSelection = new Scanner(System.in).nextLine();
                        Optional<Product> product = customerOperationsController.buyProductForSymbol(userProductSelection);
                        if (product.isPresent()) {
                            System.out.println("Here is your product: " + product.get().getName());
                        } else
                            System.out.println("Out of stock");
                        break;
                    case EXIT:
                        System.out.println("Bye!");
                        return;
                    case SERVICE_MENU:
                        handleServiceUser();
                        break;
                    default:
                        System.out.println("Ibvalid selection");
                }
            } catch (IllegalArgumentException e) {
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

    private void handleServiceUser() {
        //Wyświetl menu użytkownika serwisowego (serwisanta)
        //Odczyt opcji wyboru
        //dopisać service menu selection
        //za pomocą switch obsłużyć
        //dla ADD_TRAY wywołać metodę addTray, z klasy kontroler serwisu
        while (true) {
            customerOperationsController.printMachine();
            printServiceMenu();
            ServiceMenuSelection serviceSelection = getServiceSelection();

            switch (serviceSelection) {
                case ADD_TRAY:
                    System.out.println("Please put tray at provided position symbol, and set price");
                    employeeController.addTray();
                    break;
                case REMOVE_TRAY:

                    break;
                case ADD_PRODUCTS_FOR_TRAY:

                    break;

                case REMOVE_PRODUCTS_FROM_TRAY:

                    break;

                case CHANGE_PRICE:

                    break;

                case EXIT:
                    startApplication();
                    break;

                default:
                    System.out.println("Ivalid selection");

            }

        }
    }

    private void printServiceMenu() {
        ServiceMenuSelection[] allPosiblleSelections = ServiceMenuSelection.values();
        for (ServiceMenuSelection menuOption : allPosiblleSelections) {
            System.out.println(menuOption.getOptionNumber() + ". " + menuOption.getOptionMessage());
        }
    }


    private ServiceMenuSelection getServiceSelection() {
        System.out.print("> Your selection: ");
        String userSelection = new Scanner(System.in).nextLine();
        try {
            Integer menuNumber = Integer.valueOf(userSelection);
            return ServiceMenuSelection.selevtionForOptionNumber(menuNumber);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid selection fomat");
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
