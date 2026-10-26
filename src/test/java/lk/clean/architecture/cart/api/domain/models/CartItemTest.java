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

        CartItem testCartItem = new CartItem(testProduct,  quantity);

        assertNotNull(testCartItem.getProduct().getProductId(), "Product Id is auto generated");
        assertEquals(productName, testCartItem.getProduct().getProductName(), "Product Name");
        assertEquals(productDescription, testCartItem.getProduct().getProductDescription(), "Product Description");
        assertEquals(productPrice, testCartItem.getProduct().getProductPrice(), "Product Price");

        assertEquals(quantity, testCartItem.getQuantity(), "Product Quantity");
    }


}
