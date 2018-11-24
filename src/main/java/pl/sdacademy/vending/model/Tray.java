package pl.sdacademy.vending.model;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public class Tray implements Serializable {

    private String symbol;
    private Long price;
    private Queue<Product> products;
    public static final long serialVersionUID = 1L;
    public static final int MAX_SIZE = 10;


    public Long getPrice() {
        return price;
    }

    public String getSymbol() {

        return symbol;
    }



    private Tray(Builder builder) {
        symbol = builder.symbol;
        price = builder.price;
        products = builder.products;
    }

    public static Builder builder(String symbol) {

        return new Builder(symbol);
    }

    public  Optional<String> firstProductName () {

        return Optional.ofNullable(products.peek()).map(Product::getName);

    }

    public Optional<Product> buyProduct() {

        return Optional.ofNullable(products.poll());
    }

    public boolean addProduct (Product product) {

        if (this.products.size()== MAX_SIZE) {
            return false;
        }
        this.products.add(product);

        return true;
    }

    public void updatePrice (Long price) {
        this.price = price;
    }

    public TraySnapshot snapshot () {
        return new TraySnapshot(symbol,price,firstProductName().orElse("--"));
    }



    public static class Builder {
        private String symbol;
        private Long price;
        private Queue<Product> products;


        private Builder(String symbol) {

            this.symbol = symbol;
            this.products = new ArrayDeque<>();
        }

        public Tray build() {
            if (price == null || price < 0) {
                price = 0L;
            }
            return new Tray(this);

        }

        public Builder product(Product product) {
            products.add(product);
            return this;
        }

        public Builder symbol(String symbol) {
            this.symbol = symbol;
            products = new ArrayDeque<>();
            return this;
        }

        public Builder price(Long price) {
            this.price = price;
            return this;
        }
    }
}
