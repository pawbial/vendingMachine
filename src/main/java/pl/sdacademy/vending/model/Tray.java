package pl.sdacademy.vending.model;

import java.util.ArrayDeque;
import java.util.Queue;

public class Tray {

    private String symbol;
    private Long price;
    private Queue <Product> products;


    public Long getPrice() {
        return price;
    }

    public String getSymbol() {

        return symbol;
    }

private Tray (Builder builder) {
        symbol = builder.symbol;
        price = builder.price;
        products = builder.products;
}

public static Builder builder (String symbol) {

        return new Builder(symbol);
}

    public static class Builder {
        private String symbol;
        private Long price;
        private Queue <Product> products;


        private Builder (String symbol) {

            this.symbol = symbol;
        }

        public Tray build () {
            if (price == null || price < 0) {
                price = 0L;
            }
            return new Tray(this);

        }

        public Builder product (Product product) {
            products.add(product);
            return this;
        }

        public Builder symbol(String symbol) {
            this.symbol = symbol;
            products = new ArrayDeque<>();
            return this;
        }

        public Builder price (Long price) {
            this.price = price;
            return this;
        }
    }
}
