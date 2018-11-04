package pl.sdacademy.vending.model;

import pl.sdacademy.vending.util.Configuration;

import java.util.Optional;
import java.util.Random;

public class VendingMachine {

    private Configuration configuration;
    private long rowsCount;
    private long colsCount;
    private Tray[][] trays;

    public VendingMachine(Configuration configuration) {

        this.configuration = configuration;
        rowsCount = configuration.getLongProperty("machine.size.rows", 6L);
        colsCount = configuration.getLongProperty("machine.size.cols", 4L);

        if (rowsCount <= 0 || rowsCount > 26) {
            throw new IllegalArgumentException("Row count " + rowsCount + " is invalid");
        }

        if (colsCount <= 0 || colsCount > 9) {
            throw new IllegalArgumentException("Columns count " + colsCount + " is invalid");
        }
        trays = new Tray[(int) rowsCount][(int) colsCount];

        Random random = new Random();
        for (int rowNumber = 0; rowNumber < rowsCount; rowNumber++) {
            for (int colNumber = 0; colNumber < colsCount; colNumber++) {
                if (random.nextInt(10) < 8) {       // probability 0.8
                    generateTrayAtPosition(rowNumber, colNumber);
                }

            }
        }
    }

    private void generateTrayAtPosition(int rowNumber, int colNumber) {
        Random random = new Random();
        long price = random.nextInt(901) + 100;
        int symbolNumber = rowNumber + 1;
        char symbolLetter = (char) ('A' + colNumber);
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
}

