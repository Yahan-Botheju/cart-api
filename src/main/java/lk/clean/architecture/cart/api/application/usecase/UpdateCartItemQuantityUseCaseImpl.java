package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;

import java.util.UUID;

public class UpdateCartItemQuantityUseCaseImpl implements UpdateCartItemQuantityUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public UpdateCartItemQuantityUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart updateCartItemQuantity(UUID cartId, UUID productId, int quantity) {

        Cart cart = cartRepository.findCartById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        //replace existing quantity with new quantity through domain model
        cart.replaceExistingQuantity(productId, quantity);

        return cartRepository.save(cart);
    }
}
