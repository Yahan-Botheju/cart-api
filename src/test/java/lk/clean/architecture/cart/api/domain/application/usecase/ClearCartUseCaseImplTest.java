package lk.clean.architecture.cart.api.domain.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.CartItem;
import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api.application.records.commands.ClearCartCommand;
import lk.clean.architecture.cart.api.application.records.results.ClearCartResult;
import lk.clean.architecture.cart.api.application.usecase.ClearCartUseCaseImpl;
import lk.clean.architecture.cart.api.domain.repository.CartRepositoryTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

public class ClearCartUseCaseImplTest {

    @Test
    void shouldRemoveAllItemsInTheCart() {
        //mock repo and inject to Clear-cart-use-case-impl
        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        ClearCartUseCaseImpl clearCartUseCaseImpl = new ClearCartUseCaseImpl(cartRepositoryTest);

        Cart cart = new Cart("LKR");

        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100"), "LKR"));

        cart.addItem(new CartItem(product, 2));

        Cart savedCart = cartRepositoryTest.save(cart);
        //test clear cart usecase impl
        ClearCartCommand clearCartCommand = new ClearCartCommand(cart.getCartId());
        ClearCartResult clearCartResult = clearCartUseCaseImpl.clearCart(clearCartCommand);

        Optional<Cart> result = cartRepositoryTest.findCartById(cart.getCartId());
        //expectations
        assertTrue(result.isPresent(), "Cart should be present");
        assertEquals(cart.getCartId(), result.get().getCartId(), "Cart Ids should be same");
        assertTrue(savedCart.isCartEmpty(), "Cart should be empty");
        assertTrue(clearCartResult.isCartEmpty(), "Cart should be empty");
        assertEquals(0, result.get().getCartItems().size(), "CartItems count should be 0");
    }
}
