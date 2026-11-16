package lk.clean.architecture.cart.api.web.controllers;

import lk.clean.architecture.cart.api.application.usecase.product.CreateProductUseCase;
import lk.clean.architecture.cart.api.application.usecase.product.UpdateProductUseCase;
import lk.clean.architecture.cart.api.web.webMappers.ProductWebMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    //inject required dependencies
    private final CreateProductUseCase  createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final ProductWebMapper productWebMapper;

    public ProductController(
            CreateProductUseCase createProductUseCase,
            UpdateProductUseCase updateProductUseCase,
            ProductWebMapper productWebMapper
    ) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.productWebMapper = productWebMapper;
    }
}
