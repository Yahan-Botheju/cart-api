package lk.clean.architecture.cart.api.application.usecase.product;

import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.application.records.product.commands.CreateProductCommand;
import lk.clean.architecture.cart.api.application.records.product.results.CreateProductResult;

import java.math.BigDecimal;

public class CreateProductUseCaseImpl implements CreateProductUseCase {

    //inject required dependencies
    private final ProductRepository productRepository;

    public CreateProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //create new product
    @Override
    public CreateProductResult createProduct(CreateProductCommand createProductCommand) {
        //create new product
        Product product = new Product(
                createProductCommand.productName(),
                createProductCommand.productDescription(),
                new Money(
                        new BigDecimal(
                                String.valueOf(createProductCommand.productPrice().amount())),
                        createProductCommand.productPrice().currency())
        );
        //make product available
        product.setProductAvailable();
        //save product
        Product savedProduct = productRepository.save(product);

        return new CreateProductResult(
                savedProduct.getProductId(),
                savedProduct.getProductName(),
                savedProduct.getProductDescription(),
                new Money(savedProduct.getProductPrice().amount(), savedProduct.getProductPrice().currency()),
                savedProduct.isProductAvailable()
        );
    }
}
