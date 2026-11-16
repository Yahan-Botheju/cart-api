package lk.clean.architecture.cart.api.web.controllers;

import jakarta.validation.Valid;
import lk.clean.architecture.cart.api.application.records.product.commands.CreateProductCommand;
import lk.clean.architecture.cart.api.application.records.product.commands.UpdateProductCommand;
import lk.clean.architecture.cart.api.application.records.product.results.CreateProductResult;
import lk.clean.architecture.cart.api.application.records.product.results.UpdateProductResult;
import lk.clean.architecture.cart.api.application.usecase.product.CreateProductUseCase;
import lk.clean.architecture.cart.api.application.usecase.product.UpdateProductUseCase;
import lk.clean.architecture.cart.api.web.DTOs.product.request.CreateProductRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.product.request.UpdateProductRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.product.response.CreateProductResponseDTO;
import lk.clean.architecture.cart.api.web.DTOs.product.response.UpdateProductResponseDTO;
import lk.clean.architecture.cart.api.web.webMappers.ProductWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

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

    //create-new-product
    @PostMapping("/create")
    public ResponseEntity<CreateProductResponseDTO> createProduct(
            @Valid @RequestBody CreateProductRequestDTO createProductRequestDTO
    ){
        CreateProductCommand productCommand =  productWebMapper.toCreateProductCommand(createProductRequestDTO);
        CreateProductResult  createProductResult = createProductUseCase.createProduct(productCommand);
        CreateProductResponseDTO createProductResponseDTO = productWebMapper.toCreateProductResponseDTO(createProductResult);

        return ResponseEntity.created(URI.create("/api/v1/products/create")).body(createProductResponseDTO);
    }

    //update-product
    @PutMapping
    public ResponseEntity<UpdateProductResponseDTO> updateProduct(
            @RequestParam UUID productId,
            @Valid @RequestBody UpdateProductRequestDTO updateProductRequestDTO
    ){
        UpdateProductCommand productCommand =  productWebMapper.toUpdateProductCommand(productId, updateProductRequestDTO);
        UpdateProductResult updateProductResult = updateProductUseCase.updateProduct(productCommand);
        UpdateProductResponseDTO updateProductResponseDTO = productWebMapper.toUpdateProductResponseDTO(updateProductResult);

        return ResponseEntity.status(HttpStatus.OK).body(updateProductResponseDTO);
    }
}
