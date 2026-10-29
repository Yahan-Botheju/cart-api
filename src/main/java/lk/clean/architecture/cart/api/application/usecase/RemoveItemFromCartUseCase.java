package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;

import java.util.UUID;

public interface RemoveItemFromCartUseCase {
    //remove items from cart
    Cart removeItemFromCart(UUID cartId, UUID productId);
}
