package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.controller.service.CustomerService;
import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repository.VendingMachineRepository;
import pl.sdacademy.vending.util.StringUtil;

import java.util.Optional;
import java.util.Scanner;

public class CustomerOperationsController {


    private final Integer trayWidth = 12;
    private CustomerService customerService;


    public CustomerOperationsController(CustomerService customerService) {

        this.customerService = customerService;
    }

    public void buyProduct () {
        System.out.println("> Please select tray symbol: ");
        String userProductSelection = new Scanner(System.in).nextLine();
        Optional<Product> product = customerService.buyProductFromTray(userProductSelection);
        if (product.isPresent()) {
            System.out.println("Here is your product: " + product.get().getName());
        } else
            System.out.println("Out of stock");
    }

    public void printMachine() {
        Optional<VendingMachine> loadedMachine = customerService.loadMachineToPrint();
        if (!loadedMachine.isPresent()) {
            System.out.println("Vending machine out of service");
            return;
        }
        VendingMachine machine = loadedMachine.get();
        for (int rowNo = 0; rowNo < machine.rowsCount(); rowNo++) {
            for (int colNo = 0; colNo < machine.colsCount(); colNo++) {
                printUpperBoundary(machine,rowNo, colNo);
            }
            System.out.println();

            for (int colNo = 0; colNo < machine.colsCount(); colNo++) {
                printSymbol(machine,rowNo, colNo);
            }
            System.out.println();

            for (int colNo = 0; colNo < machine.colsCount(); colNo++) {
                printName(machine,rowNo, colNo);
            }
            System.out.println();

            for (int colNo = 0; colNo < machine.colsCount(); colNo++) {
                printPrice(machine,rowNo, colNo);
            }
            System.out.println();

            for (int colNo = 0; colNo < machine.colsCount(); colNo++) {
                printLowerBoundary(rowNo, colNo);
            }
            System.out.println();
        }
    }



    private void printUpperBoundary(VendingMachine machine,  int rowNo, int colNo) {
        System.out.print("+" + StringUtil.duplicateText("-",trayWidth) + "+");
    }

    private void printSymbol(VendingMachine machine,  int rowNo, int colNo) {
        Optional<Tray> tray = machine.getTrayAtPosition(rowNo, colNo);
        String traySymbol = tray.map(Tray::getSymbol).orElse("--");
        System.out.print("|" + StringUtil.adjustText(traySymbol,trayWidth) + "|");

    }

    private void printLowerBoundary(int rowNo, int colNo) {
        System.out.print("+" + StringUtil.duplicateText("-",trayWidth) + "+");
    }

    private void printName (VendingMachine machine,  int rowNo, int colNo) {

        Optional<String> productName = machine.productNameAtPosition(rowNo, colNo);
        String formattedName = productName.orElse("--");
        System.out.print("|" + StringUtil.adjustText(formattedName,trayWidth) + "|");
    }

    private void printPrice (VendingMachine machine,  int rowNo, int colNo) {
        Optional<Tray> tray = machine.getTrayAtPosition(rowNo, colNo);
        Long price = tray.map(Tray::getPrice).orElse(0L);
        String formattedMoney = StringUtil.formatMoney(price);
        String centeredMoney = StringUtil.adjustText(formattedMoney, trayWidth);
        System.out.print("|" + centeredMoney + "|");

    }

}
//    public void printMachine() {
//
//        char col = 'A';
//
//        for (int y = 1; y <= machine.rowCount(); y++) {
//            for (int frame = 1; frame <= machine.colsCount(); frame++) {
//                System.out.print("+--------+");
//            }
//            System.out.println();
//            for (int symbol = 1; symbol <= machine.colsCount(); symbol++) {
//                System.out.print("|   " + col + symbol + "   |");
//            }
//            System.out.println();
//            col++;
//
//        }
//        for (int frame = 1; frame <= machine.colsCount(); frame++) {
//            System.out.print("+--------+");
//        }
//    }
//
//
//
//
////
//    private void printUpperBound (int rowNum, int colNum) {
//        System.out.print("+--------+");
//    }
//
//    private void printSymbol (int rowNum, int colNum) {
//
//    }
//
//    private void printLowerBound (int rowNum, int colNum) {
//        System.out.print("+--------+");
//    }


