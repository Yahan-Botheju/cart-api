package lk.clean.architecture.cart.api.application.usecase.product;

import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.application.records.product.commands.UpdateProductCommand;
import lk.clean.architecture.cart.api.application.records.product.results.UpdateProductResult;

public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    //inject required dependencies
    private final ProductRepository productRepository;

    public UpdateProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public UpdateProductResult updateProduct(UpdateProductCommand updateProductCommand) {

        Product product = productRepository.findById(updateProductCommand.productId())
                .orElseThrow(() -> new RuntimeException("Product is not found!"));

        //update product through domain
        product.updateProduct(
                updateProductCommand.productName(),
                updateProductCommand.productDescription(),
                updateProductCommand.productPrice()
        );
        //set product available
        product.setProductAvailable();
        //save updated product
        Product updatedProduct = productRepository.save(product);

        return new  UpdateProductResult(
                updatedProduct.getProductId(),
                updatedProduct.getProductName(),
                updatedProduct.getProductDescription(),
                updatedProduct.getProductPrice(),
                updatedProduct.isProductAvailable()
        );
    }
}
