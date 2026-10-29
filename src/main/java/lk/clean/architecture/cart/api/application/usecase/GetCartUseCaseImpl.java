package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.repositories.CartRepository;

public class GetCartUseCaseImpl implements GetCartUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public GetCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}
