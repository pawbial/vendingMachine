package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.util.StringUtil;

import java.util.Optional;

public class CustomerOperationsController {

    private final VendingMachine machine;
    private final Integer trayWidth = 12;


    public CustomerOperationsController(VendingMachine machine) {
        this.machine = machine;
    }

    public void printMachine() {
        for (int rowNo = 0; rowNo < machine.rowsCount(); rowNo++) {
            for (int colNo = 0; colNo < machine.colsCount(); colNo++) {
                printUpperBoundary(rowNo, colNo);
            }
            System.out.println();

            for (int colNo = 0; colNo < machine.colsCount(); colNo++) {
                printSymbol(rowNo, colNo);
            }
            System.out.println();

            for (int colNo = 0; colNo < machine.colsCount(); colNo++) {
                printName(rowNo, colNo);
            }
            System.out.println();

            for (int colNo = 0; colNo < machine.colsCount(); colNo++) {
                printPrice(rowNo, colNo);
            }
            System.out.println();

            for (int colNo = 0; colNo < machine.colsCount(); colNo++) {
                printLowerBoundary(rowNo, colNo);
            }
            System.out.println();
        }
    }

    private void printUpperBoundary(int rowNo, int colNo) {
        System.out.print("+" + StringUtil.duplicateText("-",trayWidth) + "+");
    }

    private void printSymbol(int rowNo, int colNo) {
        Optional<Tray> tray = machine.getTrayAtPosition(rowNo, colNo);
        String traySymbol = tray.map(Tray::getSymbol).orElse("--");
        System.out.print("|" + StringUtil.adjustText(traySymbol,trayWidth) + "|");

    }

    private void printLowerBoundary(int rowNo, int colNo) {
        System.out.print("+" + StringUtil.duplicateText("-",trayWidth) + "+");
    }

    private void printName (int rowNo, int colNo) {

        Optional<String> productName = machine.productNameAtPosition(rowNo, colNo);
        String formattedName = productName.orElse("--");
        System.out.print("|" + StringUtil.adjustText(formattedName,trayWidth) + "|");
    }

    private void printPrice (int rowNo, int colNo) {
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


