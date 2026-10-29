package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;

import java.util.UUID;

public interface GetCartUseCase {
    //get cart
    Cart getCart(UUID cartId);
}
