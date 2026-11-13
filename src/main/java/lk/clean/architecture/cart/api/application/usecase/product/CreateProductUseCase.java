package lk.clean.architecture.cart.api.application.usecase.product;

import lk.clean.architecture.cart.api.application.records.product.commands.CreateProductCommand;
import lk.clean.architecture.cart.api.application.records.product.results.CreateProductResult;

public interface CreateProductUseCase {
    //create-new-product
    CreateProductResult createProduct(CreateProductCommand createProductCommand);
}
