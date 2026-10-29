package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;

public interface CreateCartUseCase {

    //create new cart
    Cart createCart(String currency);
}
