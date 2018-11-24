package pl.sdacademy.vending.model;

import pl.sdacademy.vending.util.Configuration;

import java.io.Serializable;
import java.util.Optional;
import java.util.Random;

public class VendingMachine implements Serializable {


    private long rowsCount;
    private long colsCount;
    private Tray[][] trays;
    public static final long serialVersionUID = 1L;

    public VendingMachine(Configuration configuration) {

        rowsCount = configuration.getLongProperty("machine.size.rows", 6L);
        colsCount = configuration.getLongProperty("machine.size.cols", 4L);

        if (rowsCount <= 0 || rowsCount > 26) {
            throw new IllegalArgumentException("Row count " + rowsCount + " is invalid");
        }

        if (colsCount <= 0 || colsCount > 9) {
            throw new IllegalArgumentException("Columns count " + colsCount + " is invalid");
        }
        trays = new Tray[(int) rowsCount][(int) colsCount];

//        Random random = new Random();
//        for (int rowNumber = 0; rowNumber < rowsCount; rowNumber++) {
//            for (int colNumber = 0; colNumber < colsCount; colNumber++) {
//                if (random.nextInt(10) < 8) {       // probability 0.8
//                    generateTrayAtPosition(rowNumber, colNumber);
//                }
//
//            }
//        }
    }

    //Only for overhaull tests of the application
    // method placeTray is dedicated to solve this problem
    private void generateTrayAtPosition(int rowNumber, int colNumber) {
        Random random = new Random();
        long price = random.nextInt(901) + 100;
        char symbolLetter = (char) ('A' + rowNumber);
        int symbolNumber = colNumber + 1;
        String symbol = "" + symbolLetter + symbolNumber;
        int productProbability = random.nextInt(10);
        if (productProbability < 1) {
            Tray tray = Tray
                    .builder(symbol)
                    .price(price)
                    .product(new Product("Product" + symbol))
                    .product(new Product("Product" + symbol))
                    .build();
            trays[rowNumber][colNumber] = tray;
        } else if (productProbability < 5) {
            Tray tray = Tray
                    .builder(symbol)
                    .price(price)
                    .product(new Product("Product" + symbol))
                    .build();
            trays[rowNumber][colNumber] = tray;

        } else {
            Tray tray = Tray
                    .builder(symbol)
                    .price(price)
                    .build();
            trays[rowNumber][colNumber] = tray;
// prob. 01 -> 2x product prob. 01 -> 2x product


        }
    }

    public boolean placeTray(Tray tray) {
        String symbol = tray.getSymbol();
        int rowNo = symbol.charAt(0) - 'A';
        int colNo = symbol.charAt(1) - '1';
        if (symbol.length() != 2) {
            return false;
        }
        if (rowNo < 0 || rowNo >= rowsCount || colNo < 0 || colNo >= colsCount) {
            return false;
        } else if (trays[rowNo][colNo] == null) {
            trays[rowNo][colNo] = tray;
            return true;
        } else {
            return false;
        }

    }

    public Optional<Tray> removeTrayWithSymbol(String traySymbol) {

        if (traySymbol.length() != 2) {
            return Optional.empty();
        }
        int rowNo = traySymbol.charAt(0) - 'A';
        int colNo = traySymbol.charAt(1) - '1';

        Optional<Tray> tray = getTrayAtPosition(rowNo, colNo);

        if (tray.isPresent()) {
            trays[rowNo][colNo] = null;
        }
        return tray;
    }

    public Optional<Tray> getTrayAtPosition(int rowNum, int colNum) {

        try {
            Tray tray = trays[rowNum][colNum];
            Optional<Tray> wrpappedTray = Optional.ofNullable(tray);
            return wrpappedTray;
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }

    }

    public long rowsCount() {

        return rowsCount;
    }

    public long colsCount() {

        return colsCount;
    }

    public Optional<String> productNameAtPosition(Integer rowNo, Integer colNo) {

        Optional<Tray> tray = getTrayAtPosition(rowNo, colNo);
        if (tray.isPresent()) {
            return tray.get().firstProductName();
        } else {
            return Optional.empty();
        }
    }

    public boolean addProductToTray(String traySymbol, Product product) {
        Optional<Tray> currentTray = getTrayForSymbol(traySymbol);
//        Jako Stream
//        currentTray.map(tray -> tray.addProduct(product)).orElse(false);
        if (currentTray.isPresent()) {
            currentTray.get().addProduct(product);
            return true;
        } else {
            return false;
        }

    }

    public Optional<Product> buyProductWithSymbol(String traySymbol) {


        for (int i = 0; i < trays.length; i++) {
            for (int j = 0; j < trays[i].length; j++) {
                if (trays[i][j] != null) {
                    if (traySymbol.equals(trays[i][j].getSymbol())) {
                        Tray tray = trays[i][j];
                        return tray.buyProduct();
                    }
                }
            }

        }
        return Optional.empty();
    }

    public Optional<Product> buyProductWithSymbol2(String traySymbol) {
        if (traySymbol.length() != 2) {
            return Optional.empty();
        }
        char symbolLetter = traySymbol.toUpperCase().charAt(0);
        char symbolNumber = traySymbol.charAt(1);

        int rowNo = symbolLetter - 'A';
        int colNo = symbolNumber - '1';

        if (rowNo < 0 || rowNo >= rowsCount || colNo < 0 || colNo >= colsCount) {
            return Optional.empty();
        }
        Tray tray = trays[rowNo][colNo];

        if (tray == null) {
            return Optional.empty();
        } else {
            return tray.buyProduct();
        }
    }

    public boolean updatePriceForSymbol(String symbol, Long price) {

        Optional<Tray> trayToChange = getTrayForSymbol(symbol);

        if (trayToChange.isPresent()) {
            Tray tray = trayToChange.get();
            tray.updatePrice(price);
            return true;
        }

        return false;
    }

    public VendingMachineSnapshot snapshot() {
        //pobrać buildera shapszotu
        // przejść po wszystkich tackach w automacie
        //wywołać na nich operację snapszot
        //zapisz snapshot tacki w builderze
        //zwróć obiekt

        VendingMachineSnapshot.Builder snapBuilder = VendingMachineSnapshot.build((int)rowsCount, (int)colsCount);
        for (int row = 0; row < rowsCount; row++) {
            for (int col = 0; col < colsCount; col++) {
                Tray tray = trays[row][col];
                if (tray != null) {
                    snapBuilder.tray(row, col,tray.snapshot());
                }

            }

        }
        return snapBuilder.build();
    }

    private Optional<Tray> getTrayForSymbol(String traySymbol) {
        if (traySymbol.length() != 2) {
            return Optional.empty();
        }
        int rowNo = traySymbol.charAt(0) - 'A';
        int colNo = traySymbol.charAt(1) - '1';

        return getTrayAtPosition(rowNo, colNo);
    }


}



