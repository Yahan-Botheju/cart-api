package lk.clean.architecture.cart.api.domain.models;

import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductTest {

    //test single instance
    @Test
    void createSingleProductSuccessfully() {

        String productName = "Test-Product-01";
        String productDescription = "Test-Description-01";
        Money productPrice = new Money(new BigDecimal(100), "LKR");

        Product product = new Product(productName, productDescription, productPrice);

        assertNotNull(product.getProductId(), "Product ID generated");
        assertEquals(productName, product.getProductName(), "Product name");
        assertEquals(productDescription, product.getProductDescription(), "Product description");
        assertEquals(productPrice, product.getProductPrice(), "Product price");
        assertTrue(product.isProductAvailable(), "Product availability");
    }

    //test multiple instance
    @Test
    void createMultipleProductSuccessfully() {
        String productName1 = "Test-Product-01";
        String productDescription1 = "Test-Description-01";
        Money productPrice1 = new Money(new BigDecimal(100), "LKR");

        String productName2 = "Test-Product-02";
        String productDescription2 = "Test-Description-02";
        Money productPrice2 = new Money(new BigDecimal(100), "LKR");

        Product product1 = new Product(productName1, productDescription1, productPrice1);
        Product product2 = new Product(productName2, productDescription2, productPrice2);

        assertNotNull(product1.getProductId(), "Product ID generated");
        assertEquals(productName1, product1.getProductName(), "Product name");
        assertEquals(productDescription1, product1.getProductDescription(), "Product description");
        assertEquals(productPrice1, product1.getProductPrice(), "Product price");

        assertNotNull(product2.getProductId(), "Product ID generated");
        assertEquals(productName2, product2.getProductName(), "Product name");
        assertEquals(productDescription2, product2.getProductDescription(), "Product description");
        assertEquals(productPrice2, product2.getProductPrice(), "Product price");

        assertNotEquals(product1.getProductId(), product2.getProductId(), "Product ID are unique");
    }

    //Product name is Null
    @Test
    void ShouldThrowExceptionWhenProductNameIsNull() {
        String productDescription = "Test-Description-01";
        Money productPrice = new Money(new BigDecimal(100), "LKR");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product(null, productDescription, productPrice);
        });
        assertEquals("Product name cannot be null or empty", exception.getMessage());
    }

    //product name is empty
    @Test
    void ShouldThrowExceptionWhenProductNameIsEmpty() {
        String productDescription = "Test-Description-01";
        Money productPrice = new Money(new BigDecimal(100), "LKR");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("", productDescription, productPrice);
        });
        assertEquals("Product name cannot be null or empty", exception.getMessage());
    }

    //product price is null
    @Test
    void ShouldThrowExceptionWhenProductPriceIsNull() {
        String productName = "Test-Product-01";
        String productDescription = "Test-Description-01";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product(productName, productDescription, null);
        });
        assertEquals("Product price cannot be negative", exception.getMessage());
    }

    //product price is negative
    @Test
    void ShouldThrowExceptionWhenProductPriceIsNegative() {
        String productName = "Test-Product-01";
        String productDescription = "Test-Description-01";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product(productName, productDescription, new Money(new BigDecimal(-100), "LKR"));
        });
        assertEquals("Product price cannot be negative", exception.getMessage());
    }

}
