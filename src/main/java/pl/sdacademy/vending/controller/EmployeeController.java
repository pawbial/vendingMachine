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
        System.out.print("> Provide tray symbol");
        return String.valueOf(getUserInput());
    }



}
