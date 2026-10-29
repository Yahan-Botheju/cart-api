package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;

import java.util.UUID;

public interface ClearCartUseCase {
    //clear items in cart
    Cart clearCart(UUID cartId);
}
