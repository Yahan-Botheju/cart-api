package lk.clean.architecture.cart.api.domain.application.usecase;

import lk.clean.architecture.cart.api._domain.models.*;
import lk.clean.architecture.cart.api.application.exception.CartNotFoundException;
import lk.clean.architecture.cart.api.application.records.commands.GetCartCommand;
import lk.clean.architecture.cart.api.application.records.results.GetCartResult;
import lk.clean.architecture.cart.api.application.usecase.GetCartUseCaseImpl;
import lk.clean.architecture.cart.api.domain.repository.CartRepositoryTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class GetCartUseCaseImplTest {

    //test get cart details usecase
    @Test
    void shouldGetCartDetailsSuccessfully() {

        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        GetCartUseCaseImpl getCartUseCaseImpl = new GetCartUseCaseImpl(cartRepositoryTest);

        Cart cart = new Cart("LKR");

        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100"), "LKR"));

        cart.addItem(new CartItem(product, 2));

        cartRepositoryTest.save(cart);

        GetCartCommand getCartCommand = new GetCartCommand(cart.getCartId());
        GetCartResult  getCartResult = getCartUseCaseImpl.getCart(getCartCommand);

        Optional<Cart> result = cartRepositoryTest.findCartById(cart.getCartId());

        assertEquals(cart.getCartId(), getCartResult.cartId(), "Carts Ids should be the same");
        assertEquals("LKR", getCartResult.currency(), "Currencies should be LKR");
        assertTrue(result.isPresent(), "Carts should exist");
        assertEquals(1, getCartResult.cartItemSnapshots().size(), "CartItem count should be 1");
        assertEquals(new Money(new BigDecimal("200"), "LKR"), getCartResult.total(), "Cart total should be 200");
        assertFalse(getCartResult.isCartEmpty(), "Cart result should not be empty");
        assertEquals(2, getCartResult.cartItemSnapshots().get(0).quantity(), "CartItem quantity should be 2");
    }

    //test exception is  thrown when cart is not found
    @Test
    void shouldThrowExceptionWhenCartNotFound() {
        //create mock repo and inject
        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        GetCartUseCaseImpl  getCartUseCaseImpl = new GetCartUseCaseImpl(cartRepositoryTest);
        //mock cart id
        UUID mockCartId = UUID.randomUUID();
        //set to actual usecase
        GetCartCommand getCartCommand = new GetCartCommand(mockCartId);
        //expectations
        CartNotFoundException exception = assertThrows(CartNotFoundException.class, () -> {
            getCartUseCaseImpl.getCart(getCartCommand);
        });
        assertEquals("Cart not found!", exception.getMessage());
    }
}
