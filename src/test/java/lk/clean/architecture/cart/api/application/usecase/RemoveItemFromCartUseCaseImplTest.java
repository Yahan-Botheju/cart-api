package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.CartItem;
import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api.application.exception.CartNotFoundException;
import lk.clean.architecture.cart.api.application.records.cart.commands.RemoveItemFromCartCommand;
import lk.clean.architecture.cart.api.application.records.cart.results.RemoveItemFromCartResult;
import lk.clean.architecture.cart.api.application.usecase.cart.RemoveItemFromCartUseCaseImpl;
import lk.clean.architecture.cart.api.domain.repository.CartRepositoryTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class RemoveItemFromCartUseCaseImplTest {

    //test remove-item-from-cart
    @Test
    void shouldSuccessfullyRemoveItemFromCart() {
        //create mock repo
        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        //inject to actual UseCaseImpl
        RemoveItemFromCartUseCaseImpl removeItemFromCartUseCase = new RemoveItemFromCartUseCaseImpl(cartRepositoryTest);

        Cart cart = new Cart("LKR");

        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100"), "LKR"));

        cart.addItem(new CartItem(product, 2));
        Cart savedCart = cartRepositoryTest.save(cart);

        //create actual command and result record classes
        RemoveItemFromCartCommand removeItemFromCartCommand = new RemoveItemFromCartCommand(cart.getCartId(), product.getProductId());
        RemoveItemFromCartResult removeItemFromCartResult = removeItemFromCartUseCase.removeItemFromCart(removeItemFromCartCommand);

        Optional<Cart> cartLength = cartRepositoryTest.findCartById(cart.getCartId());

        //Expectation
        assertEquals(cart.getCartId(), removeItemFromCartResult.cartId(), "Cert Ids should be the same");
        assertEquals(product.getProductId(), removeItemFromCartResult.removedProductId(), "Product Ids should be the same");
        assertTrue(savedCart.isCartEmpty(), "Cart should be empty");
        assertTrue(cartLength.isPresent(), "Cart should be present");
        assertEquals(0, cartLength.get().getCartItems().size(), "Cart item count should be 0");
    }

    //test throw an error when cart is not found
    @Test
    void shouldThrowCartNotFoundException() {
        //initiate mock repos and inject to actual usecase
        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        RemoveItemFromCartUseCaseImpl removeItemFromCartUseCase = new RemoveItemFromCartUseCaseImpl(cartRepositoryTest);
        //create random Ids represent as mock components
        UUID mockCartId = UUID.randomUUID();
        UUID mockProductId = UUID.randomUUID();
        //set to actual usecase
        RemoveItemFromCartCommand removeItemFromCartCommand = new RemoveItemFromCartCommand(mockCartId, mockProductId);
        //expectations
        CartNotFoundException cartNotFoundException = assertThrows(CartNotFoundException.class, () -> {
            removeItemFromCartUseCase.removeItemFromCart(removeItemFromCartCommand);
        });
        assertEquals("Cart not found!", cartNotFoundException.getMessage());
    }
}
