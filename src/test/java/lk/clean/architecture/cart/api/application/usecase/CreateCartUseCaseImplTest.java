package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api.application.exception.CartNotFoundException;
import lk.clean.architecture.cart.api.application.records.commands.ClearCartCommand;
import lk.clean.architecture.cart.api.application.records.commands.CreateCartCommand;
import lk.clean.architecture.cart.api.application.records.results.CreateCartResult;
import lk.clean.architecture.cart.api.application.usecase.ClearCartUseCase;
import lk.clean.architecture.cart.api.application.usecase.ClearCartUseCaseImpl;
import lk.clean.architecture.cart.api.application.usecase.CreateCartUseCase;
import lk.clean.architecture.cart.api.application.usecase.CreateCartUseCaseImpl;
import lk.clean.architecture.cart.api.domain.repository.CartRepositoryTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateCartUseCaseImplTest {

    //test Create-Cart-Use-Case-Impl
    @Test
    void shouldSuccessfullyCreateCart() {

        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        CreateCartUseCase createCartUseCase = new CreateCartUseCaseImpl(cartRepositoryTest);

        //call command record class
        CreateCartCommand cartCommand = new CreateCartCommand("LKR");
        //call usecase
        CreateCartResult cartResult = createCartUseCase.createCart(cartCommand);
        //use check created cart is saved
        Optional<Cart> savedCart = cartRepositoryTest.findCartById(cartResult.cartId());

        //expectations
        assertNotNull(cartResult.cartId(), "Cart Id cannot be null");
        assertEquals("LKR", cartResult.currency(), "Cart currency should be LKR");
        assertTrue(cartResult.isCartEmpty(), "Cart should be empty");
        assertTrue(savedCart.isPresent(), "Cart should be present");
    }

    //test exception throws when cart is not found
    @Test
    void shouldThrowCartNotFoundException() {
        //instantiated mock repos
        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        ClearCartUseCase clearCartUseCase = new ClearCartUseCaseImpl(cartRepositoryTest);
        //mock cartId
        UUID mockCartId = UUID.randomUUID();
        //use actual usecase
        ClearCartCommand clearCartCommand = new ClearCartCommand(mockCartId);
        //expectations
        CartNotFoundException exception = assertThrows(CartNotFoundException.class, () -> {
            clearCartUseCase.clearCart(clearCartCommand);
        });
        assertEquals("Cart not found!", exception.getMessage());
    }
}
