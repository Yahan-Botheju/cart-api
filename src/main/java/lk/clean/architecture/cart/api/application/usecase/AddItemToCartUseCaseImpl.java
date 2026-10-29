package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;

public class AddItemToCartUseCaseImpl implements AddItemToCartUseCase {

    //inject required dependence
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddItemToCartUseCaseImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }
}
