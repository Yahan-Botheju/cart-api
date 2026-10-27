package lk.clean.architecture.cart.api.domain.models;

import lk.clean.architecture.cart.api._domain.models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

class CartTest {

    //add valid Item to cart
    @Test
    void shouldCartItemAddSuccessful(){

        CartItem cartItem  = new CartItem(new Product("Test-Product-01","Test-Description-01", new Money(new BigDecimal(20), "LKR")), 2);
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
        assertEquals("Test-Description-01",  checkAddItemDetails.productDescription(), "Product description should be match");
        assertEquals(new BigDecimal("20"), checkAddItemDetails.unitPrice().amount(), "Unit price should be match");
        assertEquals("LKR", checkAddItemDetails.unitPrice().currency(), "Currency should be match");

        assertEquals(2, checkAddItemDetails.quantity(), "Quantity should be match");

    }

    //add null item to cart
    @Test
    void shouldThrowIllegalArgumentException(){
        Cart cart = new Cart("LKR");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
             cart.addItem(null);
        });
        assertEquals("Items cannot be empty!", exception.getMessage());
    }

    //check unavailable product
    @Test
    void shouldThrowIllegalArgumentExceptionWhenProductIsNotAvailable(){
        Cart cart = new Cart("LKR");

        Product unavailableProduct = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal(20), "LKR"));

        //make product deactivate
        unavailableProduct.setProductUnavailable();

        CartItem cartItem = new CartItem(unavailableProduct, 2);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.addItem(cartItem);
        });
        assertEquals("Product is not available!", exception.getMessage());
    }

    //check cart product currency compatible with cart currency
    @Test
    void shouldThrowIllegalStateExceptionWhenCurrencyIsNotMatching(){
        Cart cart = new Cart("LKR");

        CartItem cartItem = new CartItem(new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal(20), "EU")), 3);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            cart.addItem(cartItem);
        });

        assertEquals("Invalid currency!", exception.getMessage());
    }

}
