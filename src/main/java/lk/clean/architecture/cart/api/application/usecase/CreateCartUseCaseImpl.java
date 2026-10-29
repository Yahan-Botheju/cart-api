package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api.application.port.CartRepository;

public class CreateCartUseCaseImpl implements CartRepository {

    //inject required dependencies
    private final CartRepository cartRepository;

    public CreateCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}
