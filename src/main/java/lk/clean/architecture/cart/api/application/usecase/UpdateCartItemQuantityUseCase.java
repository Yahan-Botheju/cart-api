package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;

import java.util.UUID;

public interface UpdateCartItemQuantityUseCase {
    //update cart item quantity
    Cart updateCartItemQuantity(UUID cartId, UUID productId, int quantity);
}
