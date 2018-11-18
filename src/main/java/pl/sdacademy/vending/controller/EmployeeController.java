package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.controller.service.EmployeeService;
import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.Tray;

import java.util.Optional;
import java.util.Scanner;

public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public void addTray () {
        //ask for traysymbol, ask for tray price,
        //build new tray,
        //delegate tray save to service
        // Print or error
        System.out.print("> Set tray symbol");
        Scanner employee = new Scanner(System.in);
        String traySymbol = employee.next();
        System.out.print("> Set price (cents) ");
        Long price = Long.valueOf(employee.next());
        Tray tray = Tray.builder(traySymbol).price(price).build();
        Optional<String> errorMessage = employeeService.addTray(tray);
        System.out.println(errorMessage.orElse("Tray has been added"));

    }
}
