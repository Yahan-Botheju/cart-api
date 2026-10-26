package lk.clean.architecture.cart.api.domain.models;

import lk.clean.architecture.cart.api._domain.models.CartItem;
import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CartItemTest {

    //create cartItem
    @Test
    void createSuccessfulCartItem() {

        String productName = "Test-Product-01";
        String productDescription = "Test-Description-01";
        Money productPrice = new Money(new BigDecimal(100), "LKR");
        int quantity = 5;

        Product testProduct = new Product(productName, productDescription, productPrice);

        CartItem testCartItem = new CartItem(testProduct, quantity);

        assertNotNull(testCartItem.getProduct().getProductId(), "Product Id is auto generated");
        assertEquals(productName, testCartItem.getProduct().getProductName(), "Product Name");
        assertEquals(productDescription, testCartItem.getProduct().getProductDescription(), "Product Description");
        assertEquals(productPrice, testCartItem.getProduct().getProductPrice(), "Product Price");

        assertEquals(quantity, testCartItem.getQuantity(), "Product Quantity");
    }

    //check product null when instance create
    @Test
    void shouldThrowIllegalArgumentExceptionWhenProductIsNull() {
        int quantity = 5;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CartItem(null, quantity);
        });

        assertEquals("Product cannot be empty!", exception.getMessage());
    }

    //check quantity is zero
    @Test
    void shouldThrowIllegalArgumentExceptionWhenQuantityIsZero() {
        int quantity = 0;

        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal(100), "LKR")
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CartItem(product, quantity);
        });

        assertEquals("Quantity cannot be empty!", exception.getMessage());
    }

    //check when quantity is negative
    @Test
    void shouldThrowIllegalArgumentExceptionWhenQuantityIsNegative() {
        int quantity = -1;
        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal(100), "LKR")
        );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CartItem(product, quantity);
        });
        assertEquals("Quantity cannot be empty!", exception.getMessage());
    }

    //check when quantity is in MAX_ALLOWED boundary
    @Test
    void shouldSuccessWhenQuantityIsHundred() {
        String productName = "Test-Product-01";
        String productDescription = "Test-Description-01";
        Money productPrice = new Money(new BigDecimal(100), "LKR");
        int quantity = 100;

        Product product = new Product(productName, productDescription, productPrice);
        CartItem testCartItem = new CartItem(product, quantity);

        assertNotNull(testCartItem.getProduct().getProductId(), "Product Id is auto generated");
        assertEquals(productName, testCartItem.getProduct().getProductName());
        assertEquals(productDescription, testCartItem.getProduct().getProductDescription());
        assertEquals(productPrice, testCartItem.getProduct().getProductPrice());

        assertEquals(quantity, testCartItem.getQuantity(), "Product Quantity");
    }

    //check when quantity is exceeded 100 when cartItem instance is created
    @Test
    void shouldThrowIllegalExceptionWhenQuantityIsExceedHundred() {
        int quantity = 101;

        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal(100), "LKR")
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CartItem(product, quantity);
        });
        assertEquals("Quantity exceeded!", exception.getMessage());
    }

    //replace quantity method check with valid quantity
    @Test
    void shouldReplaceQuantitySuccessful() {

        int currentQuantity = 10;
        int replaceQuantity = 20;

        CartItem testCartItem = new CartItem(new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal(100), "LKR")),
                currentQuantity
        );
        testCartItem.replaceQuantity(replaceQuantity);

        assertEquals(20, testCartItem.getQuantity(), "Product Quantity");
    }
}
