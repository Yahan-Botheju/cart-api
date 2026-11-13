package lk.clean.architecture.cart.api.application.usecase.product;

import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;

public class CreateProductUseCaseImpl implements CreateProductUseCase {

    //inject required dependencies
    private final ProductRepository productRepository;

    public CreateProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
