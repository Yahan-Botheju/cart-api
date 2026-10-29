package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;

import java.util.UUID;

public interface AddItemToCartUseCase {
    //add item to cart
    Cart addItemToCart(UUID cartId, UUID productId, int quantity);
}
