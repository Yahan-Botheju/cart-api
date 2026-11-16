package lk.clean.architecture.cart.api.web.webMappers;

import lk.clean.architecture.cart.api.application.records.product.commands.CreateProductCommand;
import lk.clean.architecture.cart.api.web.DTOs.product.response.CreateProductResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductWebMapper {

    /* __CREATE_PRODUCT__ */

    //to command
    CreateProductCommand toCreateProductCommand(CreateProductCommand createProductCommand);

    //to response
    CreateProductResponseDTO toCreateProductResponseDTO(CreateProductResponseDTO createProductResponseDTO);
}
