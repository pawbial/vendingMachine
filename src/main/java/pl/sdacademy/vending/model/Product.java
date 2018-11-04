package pl.sdacademy.vending.model;

public class Product {

    private String name;

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
