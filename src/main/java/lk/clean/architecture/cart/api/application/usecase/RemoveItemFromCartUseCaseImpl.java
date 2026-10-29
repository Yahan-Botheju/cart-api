package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;

import java.util.UUID;

public class RemoveItemFromCartUseCaseImpl implements RemoveItemFromCartUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public RemoveItemFromCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart removeItemFromCart(UUID cartId, UUID productId) {

        Cart cart = cartRepository.findCartById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        //remove item using domain model method
        cart.removeItem(productId);

        return cartRepository.save(cart);
    }
}
