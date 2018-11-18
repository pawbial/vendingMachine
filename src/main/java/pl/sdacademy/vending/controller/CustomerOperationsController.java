package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repository.VendingMachineRepository;
import pl.sdacademy.vending.util.StringUtil;

import java.util.Optional;

public class CustomerOperationsController {


    private final Integer trayWidth = 12;
    private final VendingMachineRepository machineRepository;


    public CustomerOperationsController(VendingMachineRepository machineRepository) {

        this.machineRepository = machineRepository;
    }

    public void printMachine() {
        Optional<VendingMachine> loadedMachine = machineRepository.load();
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

    public Optional <Product> buyProductForSymbol (String traySymbol) {
        Optional<VendingMachine> loadedMachine = machineRepository.load();
        if (loadedMachine.isPresent()) {
            VendingMachine machine = loadedMachine.get();
            Optional<Product> boughtProduct = machine.buyProductWithSymbol(traySymbol);
            machineRepository.save(machine);
            return boughtProduct;
        } else {
            System.out.println("Vending Machone out of service");
            return Optional.empty();
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


