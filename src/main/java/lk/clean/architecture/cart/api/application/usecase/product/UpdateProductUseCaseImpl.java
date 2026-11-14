package lk.clean.architecture.cart.api.application.usecase.product;

import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;

public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    //inject required dependencies
    private final ProductRepository productRepository;

    public UpdateProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
