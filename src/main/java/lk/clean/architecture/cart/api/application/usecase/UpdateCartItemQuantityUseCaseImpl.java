package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.repositories.CartRepository;

public class UpdateCartItemQuantityUseCaseImpl implements UpdateCartItemQuantityUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public UpdateCartItemQuantityUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}
