package pl.sdacademy.vending;

import pl.sdacademy.vending.controller.CustomerOperationsController;
import pl.sdacademy.vending.controller.EmployeeOperationsController;
import pl.sdacademy.vending.controller.service.CustomerService;
import pl.sdacademy.vending.controller.service.EmployeeService;
import pl.sdacademy.vending.repository.HardDriveVendingMachineRepository;
import pl.sdacademy.vending.service.DefaultCustomerService;
import pl.sdacademy.vending.service.DefaultEmployeeService;
import pl.sdacademy.vending.service.repository.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;

import java.util.Scanner;

public class Main {

    Configuration configuration = new Configuration();
    VendingMachineRepository vendingMachineRepository = new HardDriveVendingMachineRepository(configuration);
    EmployeeService employeeService = new DefaultEmployeeService(vendingMachineRepository, configuration);
    CustomerService customerService = new DefaultCustomerService(vendingMachineRepository);
    CustomerOperationsController customerOperationsController = new CustomerOperationsController(customerService);
    EmployeeOperationsController employeeOperationsController = new EmployeeOperationsController(employeeService);

    private void startApplication() {
        while (true) {
            customerOperationsController.printMachine();
            printMenu();
            try {

                UserMenuSelection userSelection = getUserSelection();

                switch (userSelection) {
                    case BUY_PRODUCT:
                      customerOperationsController.buyProduct();
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
                    employeeOperationsController.addTray();
                    break;
                case REMOVE_TRAY:
                    System.out.println("Please select tray to remove");
                    employeeOperationsController.removeTray();

                    break;
                case ADD_PRODUCTS_FOR_TRAY:
                    System.out.println("Provide data for product to add");
                    employeeOperationsController.addProduct();

                    break;

                case REMOVE_PRODUCTS_FROM_TRAY:

                    break;

                case CHANGE_PRICE:
                    System.out.println("Set new price for defined tray");
                    employeeOperationsController.changePrice();
                    break;

                case EXIT:
                    System.out.println("Going back to user menu");
                  return;


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
