package lk.clean.architecture.cart.api.web.webMappers;

import lk.clean.architecture.cart.api.application.records.commands.AddItemToCartCommand;
import lk.clean.architecture.cart.api.application.records.commands.CreateCartCommand;
import lk.clean.architecture.cart.api.application.records.results.AddItemToCartResult;
import lk.clean.architecture.cart.api.application.records.results.CreateCartResult;
import lk.clean.architecture.cart.api.web.DTOs.request.AddItemToCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.request.CreateCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.response.AddItemToCartResponseDTO;
import lk.clean.architecture.cart.api.web.DTOs.response.CreateCartResponseDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CartWebMapper {

    /* __CREATE_CART__ */

    //to command
    CreateCartCommand toCreateCartCommand(CreateCartRequestDTO createCartRequestDTO);

    //to response
    CreateCartResponseDTO toCreateCartResponseDTO(CreateCartResult createCartResult);

    /* __ADD_ITEM_TO_CART__ */

    //to command
    AddItemToCartCommand toAddItemToCartCommand(UUID cartId, AddItemToCartRequestDTO addItemToCartRequestDTO);

    //to response
    AddItemToCartResponseDTO toAddItemToCartResponseDTO(AddItemToCartResult addItemToCartResult);
}
