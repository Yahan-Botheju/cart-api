package lk.clean.architecture.cart.api.web.webMappers;

import lk.clean.architecture.cart.api.application.records.product.commands.CreateProductCommand;
import lk.clean.architecture.cart.api.application.records.product.commands.UpdateProductCommand;
import lk.clean.architecture.cart.api.application.records.product.results.CreateProductResult;
import lk.clean.architecture.cart.api.application.records.product.results.UpdateProductResult;
import lk.clean.architecture.cart.api.web.DTOs.product.request.CreateProductRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.product.request.UpdateProductRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.product.response.CreateProductResponseDTO;
import lk.clean.architecture.cart.api.web.DTOs.product.response.UpdateProductResponseDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductWebMapper {

    /* __CREATE_PRODUCT__ */

    //to command
    CreateProductCommand toCreateProductCommand(CreateProductRequestDTO createProductRequestDTO);

    //to response
    CreateProductResponseDTO toCreateProductResponseDTO(CreateProductResult createProductResult);

    /* __UPDATE_PRODUCT__ */

    //to command
    UpdateProductCommand toUpdateProductCommand(UUID productId, UpdateProductRequestDTO updateProductRequestDTO);

    //to response
    UpdateProductResponseDTO toUpdateProductResponseDTO(UpdateProductResult updateProductResult);
}
