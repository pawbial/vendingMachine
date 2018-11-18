package pl.sdacademy.vending.model;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class TrayTest {

    @Test
    public void shoudBeAbleToBuyLasAvialableProduct() {
        // Given
        Product definedProduct = new Product("P1");
        Tray tray = Tray.builder("A1").product(definedProduct).build();
        // When
        Optional<Product> boughtProduct = tray.buyProduct();
        // Then
        assertTrue(boughtProduct.isPresent());
        assertEquals(definedProduct,boughtProduct.get());

    }

    @Test
    public void shouldReturnTrueWhenAddingOneProduct() {
    // Given
        Product definedProduct = new Product("P1");
        Tray tray = Tray.builder("A1").build();

    // When
        boolean addProduct = tray.addProduct(definedProduct);

        // Then
       assertTrue(addProduct);

    }

    @Test
    public void shouldReturnFalseWhenAddingMoreThanTenProducts() {
        // Given
        Product definedProduct = new Product("P1");
        Tray tray = Tray.builder("A1").build();

        // When
        for (int productNumber = 0; productNumber < Tray.MAX_SIZE; productNumber++) {
            tray.addProduct(definedProduct);
        }
        boolean addProduct = tray.addProduct(definedProduct);
        // Then
        assertFalse(addProduct);

    }



}