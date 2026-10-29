package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;

import java.util.UUID;

public class ClearCartUseCaseImpl implements ClearCartUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public ClearCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart clearCart(UUID cartId) {

        Cart cart = cartRepository.findCartById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        //clear cart items through domain method
        cart.clearCart();

        return cartRepository.save(cart);
    }
}
