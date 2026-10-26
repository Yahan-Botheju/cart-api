package lk.clean.architecture.cart.api.domain.models;

import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ProductTest {

    @Test
    void ProductCreateSuccessfully() {

        String productName = "Test-Product-01";
        String productDescription = "Test-Description-01";
        Money productPrice =  new Money(new BigDecimal(100), "LKR");

        Product product = new Product(productName, productDescription, productPrice);

        assertNotNull(product, "Product should not be null");
        assertEquals(productName, product.getProductName());
        assertEquals(productDescription, product.getProductDescription());
        assertEquals(productPrice, product.getProductPrice());
    }

    @Test
    void shouldThrowExceptionWhenProductNameIsNullOrEmpty() {

        String productDescription = "Test-Description-01";
        Money productPrice =  new Money(new BigDecimal(100), "LKR");

        assertThrows(IllegalArgumentException.class, () ->
                new Product(null, productDescription, productPrice)
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Product(" ", productDescription, productPrice)
        );
    }
}
