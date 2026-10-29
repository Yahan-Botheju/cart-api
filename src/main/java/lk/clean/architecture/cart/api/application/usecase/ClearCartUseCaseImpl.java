package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.repositories.CartRepository;

public class ClearCartUseCaseImpl implements ClearCartUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public ClearCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

}
