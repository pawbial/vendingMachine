package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.controller.service.EmployeeService;
import pl.sdacademy.vending.model.Tray;

import java.util.Optional;
import java.util.Scanner;

public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }


    public void addTray() {
        //ask for traysymbol, ask for tray price,
        //build new tray,
        //delegate tray save to service
        // Print or error
        String traySymbol = getTraySymbolFromUser();
        Long price = getTrayPriceFromUser();
        Tray tray = Tray.builder(traySymbol).price(price).build();
        Optional<String> errorMessage = employeeService.addTray(tray);
        System.out.println(errorMessage.orElse("Tray has been added"));

    }

    public void removeTray () {

        Optional<String> errorMessage =  employeeService.removeTrayWithSymbol(getTraySymbolFromUser());
        errorMessage.orElse("Tray has been removed");

    }



    private Long getTrayPriceFromUser() {
        Long price = null;
        while (price == null) {
            System.out.println("> Provide price (cents)");
          try {
              price = Long.parseLong(getUserInput());
          } catch (NumberFormatException e) {
              System.out.println("Ivalid price. Try again");
          }
        }

        return price;
    }

    private String getUserInput() {
        return new Scanner(System.in).nextLine();
    }

    private String getTraySymbolFromUser() {
        System.out.print("> Please provide tray symbol");
        return String.valueOf(getUserInput());
    }

    public void addProduct () {
        String traySymbol = traySymbolFromUser();
        String productName = productNameFromUser();
        Integer productQuantity = productQuantityFromUser();
        Optional<String> errorMessage = Optional.of(employeeService.addProduct(traySymbol, productName, productQuantity)
                .orElse("All products have been added"));
    }

    private Integer productQuantityFromUser() {
        return Integer.parseInt(getUserInput());
    }

    private String productNameFromUser() {
        String productName = getUserInput();
        System.out.println("> Please define product quantity");
        return productName;
    }

    private String traySymbolFromUser() {
        String traySymbol = getTraySymbolFromUser();
        System.out.println("> Please provide product name");
        return traySymbol;
    }


}
