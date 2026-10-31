package lk.clean.architecture.cart.api.domain.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.CartItem;
import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api.application.exception.CartNotFoundException;
import lk.clean.architecture.cart.api.application.records.commands.AddItemToCartCommand;
import lk.clean.architecture.cart.api.application.records.results.AddItemToCartResult;
import lk.clean.architecture.cart.api.application.usecase.AddItemToCartUseCaseImpl;
import lk.clean.architecture.cart.api.domain.repository.CartRepositoryTest;
import lk.clean.architecture.cart.api.domain.repository.ProductRepositoryTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AddItemToCartUseCaseImplTest {

    //test item add to cart
    @Test
    void shouldSuccessfullyAddItemToCart() {

        //create mock repos
        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        ProductRepositoryTest productRepositoryTest = new ProductRepositoryTest();

        //inject dependencies
        AddItemToCartUseCaseImpl addItemToCartUseCaseImpl = new AddItemToCartUseCaseImpl(cartRepositoryTest, productRepositoryTest);

        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100"), "LKR"));

        Cart cart = new Cart("LKR");
        Cart savedCart = cartRepositoryTest.save(cart);

        productRepositoryTest.addProduct(product);

        //create addCartToItemUseCaseImpl and add values
        AddItemToCartCommand addItemToCartCommand = new AddItemToCartCommand(cart.getCartId(), product.getProductId(), 2);
        AddItemToCartResult cartResult = addItemToCartUseCaseImpl.addItemToCart(addItemToCartCommand);
        //check item existence
        Optional<Cart> findCart = cartRepositoryTest.findCartById(savedCart.getCartId());

        //expectations
        assertEquals(cart.getCartId(), cartResult.cartId(), "Created cartId and item added cart Id should be same");
        assertEquals(product.getProductId(), cartResult.productId(), "Created productId and added productId should be same");
        assertEquals(2, cartResult.quantity(), "Added quantity should be same");
        assertEquals(new Money(new BigDecimal("200"), "LKR"), cartResult.subtotal(), "Subtotal should be 200");
        assertEquals(1, findCart.get().getCartItems().size(), "CartItems count should be 1");
        assertFalse(cart.isCartEmpty(), "Cart should be empty");
    }

    //check exception throw when cart is not found
    @Test
    void shouldThrowCartNotFoundException() {
        //create mock repos
        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        ProductRepositoryTest productRepositoryTest = new ProductRepositoryTest();

        //create product and add into product repo
        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100"), "LKR"));

        productRepositoryTest.addProduct(product);

        //create actual usecase obj
        AddItemToCartUseCaseImpl addItemToCartUseCaseImpl = new AddItemToCartUseCaseImpl(cartRepositoryTest, productRepositoryTest);

        UUID randomCartId = UUID.randomUUID();
        //call usecase method
        AddItemToCartCommand addItemToCartCommand = new AddItemToCartCommand(randomCartId, product.getProductId(), 2);

        //throw exception
        CartNotFoundException expectedException = assertThrows(CartNotFoundException.class, () -> {
            addItemToCartUseCaseImpl.addItemToCart(addItemToCartCommand);
        });
        //expectation
        assertEquals("Cart not found!", expectedException.getMessage());
    }
}
