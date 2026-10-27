package lk.clean.architecture.cart.api.domain.models;

import lk.clean.architecture.cart.api._domain.models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

class CartTest {

    //add valid Item to cart
    @Test
    void shouldCartItemAddSuccessful() {

        CartItem cartItem = new CartItem(new Product("Test-Product-01", "Test-Description-01", new Money(new BigDecimal(20), "LKR")), 2);
        Cart cart = new Cart("LKR");

        cart.addItem(cartItem);

        //check cart
        assertNotNull(cart.getCartId(), "CartId cannot be null");
        assertEquals("LKR", cart.getCurrency(), "Currency is not correct");
        assertEquals(1, cart.getCartItems().size(), "CartItems contain 1 item");

        //get return values through record class
        CartItemSnapshot checkAddItemDetails = cart.getCartItems().get(0);

        //check Product
        assertNotNull(checkAddItemDetails.productId(), "ProductId is not null");
        assertEquals("Test-Product-01", checkAddItemDetails.productName(), "Product name should be match");
        assertEquals("Test-Description-01", checkAddItemDetails.productDescription(), "Product description should be match");
        assertEquals(new BigDecimal("20"), checkAddItemDetails.unitPrice().amount(), "Unit price should be match");
        assertEquals("LKR", checkAddItemDetails.unitPrice().currency(), "Currency should be match");

        assertEquals(2, checkAddItemDetails.quantity(), "Quantity should be match");
    }

    //add null item to cart
    @Test
    void shouldThrowIllegalArgumentException() {
        Cart cart = new Cart("LKR");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.addItem(null);
        });
        assertEquals("Items cannot be empty!", exception.getMessage());
    }

    //check unavailable product
    @Test
    void shouldThrowIllegalArgumentExceptionWhenProductIsNotAvailable() {
        Cart cart = new Cart("LKR");

        Product unavailableProduct = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("20"), "LKR"));

        //make product deactivate
        unavailableProduct.setProductUnavailable();

        CartItem cartItem = new CartItem(unavailableProduct, 2);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.addItem(cartItem);
        });

        assertEquals("Product is not available!", exception.getMessage());
        assertEquals(0, cart.getCartItems().size(), "Cart items count should be 0");
    }

    //check cart product currency compatible with cart currency
    @Test
    void shouldThrowIllegalStateExceptionWhenCurrencyIsNotMatching() {
        Cart cart = new Cart("LKR");

        CartItem cartItem = new CartItem(new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("20"), "EU")), 3);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            cart.addItem(cartItem);
        });

        assertEquals("Invalid currency!", exception.getMessage());
        assertEquals(0, cart.getCartItems().size(), "Cart items count should be 0");
    }

    //check cart quantity exceed validation
    @Test
    void shouldThrowIllegalStateExceptionWhenQuantityIsExceedAllowedQuantity() {
        Cart cart = new Cart("LKR");

        CartItem itemA = new CartItem(new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("20"), "LKR")), 10);

        CartItem itemB = new CartItem(new Product(
                "Test-Product-02",
                "Test-Description-02",
                new Money(new BigDecimal("30"), "LKR")), 91);

        cart.addItem(itemA);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            cart.addItem(itemB);
        });

        CartItemSnapshot availableItems = cart.getCartItems().get(0);

        assertEquals("Quantity exceeded!", exception.getMessage());
        assertEquals(10, availableItems.quantity(), "Existing Quantity state not be changed");
    }

    //check quantity increasing when same product adding but quantity should change
    @Test
    void shouldIncrementQuantityWhenAddSameProduct() {
        Cart cart = new Cart("LKR");

        Product product = new Product(
                "Test-Product-02",
                "Test-Description-02",
                new Money(new BigDecimal("20"), "LKR")
        );

        CartItem firstAdd = new CartItem(product, 2);
        CartItem secondAdd = new CartItem(product, 3);

        cart.addItem(firstAdd);
        cart.addItem(secondAdd);

        CartItemSnapshot addedItems = cart.getCartItems().get(0);

        assertEquals(1, cart.getCartItems().size(), "Cart items count should be 1");
        assertEquals(5, addedItems.quantity(), "Added quantity should be 5 by incrementing");
    }

    //add different products
    @Test
    void shouldSuccessfullyAddProducts() {
        Cart cart = new Cart("LKR");

        CartItem itemA = new CartItem(new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("20"), "LKR")), 2);

        CartItem itemB = new CartItem(new Product(
                "Test-Product-02",
                "Test-Description-02",
                new Money(new BigDecimal("30"), "LKR")), 4);

        cart.addItem(itemA);
        cart.addItem(itemB);

        CartItemSnapshot addedItems1 = cart.getCartItems().get(0);
        CartItemSnapshot addedItems2 = cart.getCartItems().get(1);

        assertEquals(2, cart.getCartItems().size(), "Cart items count should be 2");
        assertEquals(2, addedItems1.quantity(), "Product quantity should be 2");
        assertEquals(4, addedItems2.quantity(), "Product quantity should be 4");
    }

    // check allowed boundary exceed exception is thrown
    @Test
    void shouldThrowIllegalStateExceptionWhenExceedAllowedQuantityInSameProduct() {
        Cart cart = new Cart("LKR");

        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("20"), "LKR"));

        CartItem firstAdd = new CartItem(product, 60);
        CartItem secondAdd = new CartItem(product, 41);

        cart.addItem(firstAdd);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            cart.addItem(secondAdd);
        });

        assertEquals("Quantity exceeded!", exception.getMessage());
        assertEquals(1, cart.getCartItems().size(), "Cart items count should be 1");
        assertEquals(60, firstAdd.getQuantity(), "First Added quantity should not be changed");
    }

    /* __REMOVE_ITEM_FROM_CART__ */

    //remove item from cart
    @Test
    void shouldItemSuccessfullyRemovedFromCart() {
        Cart cart = new Cart("LKR");

        CartItem cartItemA = new CartItem(new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("20"), "LKR")), 2);

        CartItem cartItemB = new CartItem(new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("20"), "LKR")), 5);

        cart.addItem(cartItemA);
        cart.addItem(cartItemB);

        //remove item from cart
        cart.removeItem(cartItemA.getProduct().getProductId());

        CartItemSnapshot removedItems = cart.getCartItems().get(0);

        assertEquals(1, cart.getCartItems().size(), "Cart items count should be 1");
        assertEquals(5, removedItems.quantity(), "Available quantity should be 5");
    }

    //check unavailable product can be removed
    @Test
    void shouldThrowIllegalArgumentExceptionWhenRemoveItemIsNotAvailable() {
        Cart cart = new Cart("LKR");
        UUID mockProductId = UUID.randomUUID();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.removeItem(mockProductId);
        });
        assertEquals("There is no item to be removed!", exception.getMessage());
    }

    //check null product ID
    @Test
    void shouldThrowIllegalArgumentExceptionWhenNullProductId() {
        Cart cart = new Cart("LKR");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.removeItem(null);
        });

        assertEquals("There is no item to be removed!", exception.getMessage());
    }

    /* __CART_REPLACE_QUANTITY__ */

    //replace new quantity
    @Test
    void shouldReplaceQuantitySuccessful() {
        //new quantity
        int newQuantity = 5;

        Cart cart = new Cart("LKR");

        CartItem cartItem = new CartItem(new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("20"), "LKR")), 2);

        cart.addItem(cartItem);
        //replace with new quantity
        cart.replaceExistingQuantity(cartItem.getProduct().getProductId(), newQuantity);

        assertEquals(1, cart.getCartItems().size(), "Cart items count should be 1");
        assertEquals(5, cartItem.getQuantity(), "CartItem Quantity should be 1");
    }

    //check none exists product ID
    @Test
    void shouldThrowIllegalArgumentExceptionWhenInvalidProductId() {
        Cart cart = new Cart("LKR");
        UUID mockProductId = UUID.randomUUID();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.replaceExistingQuantity(mockProductId, 5);
        });
        assertEquals("There is no item to be removed!", exception.getMessage());
    }

    //check null product ID
    @Test
    void shouldThrowIllegalArgumentExceptionWhenProductIdIsNull() {
        Cart cart = new Cart("LKR");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.removeItem(null);
        });
        assertEquals("There is no item to be removed!", exception.getMessage());
    }

    //check with invalid quantity replace
    @Test
    void shouldThrowIllegalArgumentExceptionWhenQuantityInvalid() {
        Cart cart = new Cart("LKR");

        CartItem cartItem = new CartItem(new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("20"), "LKR")), 2);

        cart.addItem(cartItem);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.replaceExistingQuantity(cartItem.getProduct().getProductId(), -1);
        });

        assertEquals("Invalid quantity!", exception.getMessage());
        assertEquals(1,  cart.getCartItems().size(), "Cart items count should be 1" );
        assertEquals(2, cartItem.getQuantity(), "CartItem Quantity should be 2");
    }

    //check maximum quantity boundary exceed
    @Test
    void shouldThrowIllegalStateExceptionWhenReplaceQuantityExceedMaximumQuantityBondary() {
        Cart cart = new Cart("LKR");

        CartItem cartItem = new CartItem(new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("20"), "LKR")), 2);

        cart.addItem(cartItem);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            cart.replaceExistingQuantity(cartItem.getProduct().getProductId(), 101);
        });

        assertEquals("Quantity exceeded!", exception.getMessage());
        assertEquals(1,  cart.getCartItems().size(), "Cart items count should be 1" );
        assertEquals(2, cartItem.getQuantity(), "CartItem Quantity should be 2");
    }
}
