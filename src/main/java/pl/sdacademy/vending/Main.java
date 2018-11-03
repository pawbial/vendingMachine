package pl.sdacademy.vending;

import pl.sdacademy.vending.controller.CustomerOperationsController;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.util.Configuration;

public class Main {



    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        VendingMachine vendingMachine = new VendingMachine(configuration);
        CustomerOperationsController customerOperationsController = new CustomerOperationsController(vendingMachine);
        customerOperationsController.printMachine();
    }
}
