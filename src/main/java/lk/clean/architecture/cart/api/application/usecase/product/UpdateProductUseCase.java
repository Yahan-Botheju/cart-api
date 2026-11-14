package lk.clean.architecture.cart.api.application.usecase.product;

import lk.clean.architecture.cart.api.application.records.product.commands.UpdateProductCommand;
import lk.clean.architecture.cart.api.application.records.product.results.UpdateProductResult;

public interface UpdateProductUseCase {

    //update-product
    UpdateProductResult updateProduct(UpdateProductCommand updateProductCommand);
}
