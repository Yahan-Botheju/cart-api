package lk.clean.architecture.cart.api.domain.repository;

import lk.clean.architecture.cart.api._domain.models.Cart;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CartRepositoryImplTest {

    //test cart is saved successfully
    @Test
    void shouldSaveCartSuccessfully() {
        //create cart repo object
        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        Cart cart = new Cart("LKR");

        Cart savedCart = cartRepositoryTest.save(cart);

        assertNotNull(savedCart, "Cart should not be null");
        assertEquals(cart.getCartId(),savedCart.getCartId(), "Cart Id and returned cart Id should be same" );
    }

    //cart find by Id successfully
    @Test
    void shouldCartFindByIdSuccessfully() {

        CartRepositoryTest cartRepositoryTest = new CartRepositoryTest();
        Cart cart = new Cart("LKR");

        cartRepositoryTest.save(cart);
        Optional<Cart> getCartById = cartRepositoryTest.findCartById(cart.getCartId());

        assertTrue(getCartById.isPresent(), "Should cart can found");
    }
}
