package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;

import java.util.Optional;

public class CustomerOperationsController {

    private VendingMachine machine;


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
                printLowerBoundary(rowNo, colNo);
            }
            System.out.println();
        }
    }

    private void printUpperBoundary(int rowNo, int colNo) {
        System.out.print("+--------+");
    }

    private void printSymbol(int rowNo, int colNo) {
        Optional<Tray> tray = machine.getTrayAtPosition(rowNo, colNo);
        String traySymbol = tray.map(Tray::getSymbol).orElse("--");
        System.out.print("|   " + traySymbol + "   |");
    }

    private void printLowerBoundary(int rowNo, int colNo) {
        System.out.print("+--------+");
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


