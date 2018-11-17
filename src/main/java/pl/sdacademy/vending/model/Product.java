package pl.sdacademy.vending.model;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    public static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public Product(String name) {
        this.name = name;
    }

//
//        private Product(Builder builder) {
//
//        name = builder.name;
//    }
//
//    public static Builder builder(String name) {
//        return new Builder(name);
//    }
//
//
//    public static class Builder {
//
//        private String name;
//
//        private Builder(String name) {
//
//            this.name = name;
//        }
//
//        public Product build() {
//
//            return new Product(this);
//        }
//
//    }

}
