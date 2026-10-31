package lk.clean.architecture.cart.api.domain.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.CartItem;
import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api.application.records.commands.UpdateCartItemQuantityCommand;
import lk.clean.architecture.cart.api.application.records.results.UpdateCartItemQuantityResult;
import lk.clean.architecture.cart.api.application.usecase.UpdateCartItemQuantityUseCaseImpl;
import lk.clean.architecture.cart.api.domain.repository.CartRepositoryTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

public class UpdateCartItemQuantityUseCaseImplTest {

    @Test
    void shouldUpdateCartItemQuantitySuccessfully() {

        //create mock repo and inject into actual usecase
        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        UpdateCartItemQuantityUseCaseImpl updateCartItemQuantityUseCase = new UpdateCartItemQuantityUseCaseImpl(cartRepositoryTest);

        Cart cart = new Cart("LKR");

        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100"), "LKR"));

        cart.addItem(new CartItem(product, 2));
        cartRepositoryTest.save(cart);

        //create actual command and result classes to test usecase
        UpdateCartItemQuantityCommand updateCartItemQuantityCommand = new UpdateCartItemQuantityCommand(
                cart.getCartId(),
                product.getProductId(),
                5
        );
        UpdateCartItemQuantityResult updateCartItemQuantityResult = updateCartItemQuantityUseCase.updateCartItemQuantity(updateCartItemQuantityCommand);

        Optional<Cart> result = cartRepositoryTest.findCartById(updateCartItemQuantityResult.cartId());

        //expectations
        assertEquals(cart.getCartId(), updateCartItemQuantityResult.cartId(), "Cart Ids should be matched");
        assertEquals(product.getProductId(), updateCartItemQuantityResult.productId(), "Product Ids should be matched");
        assertEquals(5, updateCartItemQuantityResult.quantity(), "Quantity should be 5");
        assertEquals(new Money(new BigDecimal("500"), "LKR"), updateCartItemQuantityResult.subtotal(), "Subtotal should be 500");
        assertTrue(result.isPresent(), "Cart Items should be present");
        assertEquals(1, result.get().getCartItems().size(), "CartItems count should be 1");
        assertFalse(false, "CartItems should not be empty");
    }
}
