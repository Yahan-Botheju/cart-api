package lk.clean.architecture.cart.api.web.webMappers;

import lk.clean.architecture.cart.api.application.records.commands.*;
import lk.clean.architecture.cart.api.application.records.results.*;
import lk.clean.architecture.cart.api.web.DTOs.cart.request.AddItemToCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.request.CreateCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.request.UpdateCartItemQuantityRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.response.*;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = MoneyMapper.class)
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


    /* __GET_CART__ */

    //to command
    GetCartCommand  toGetCartCommand(UUID cartId);

    //to response
    GetCartResponseDTO toGetCartResponseDTO(GetCartResult getCartResult);


    /* __UPDATE_CART_ITEM_QUANTITY__ */

    //to command
    UpdateCartItemQuantityCommand toUpdateCartItemQuantityCommand(
            UUID cartId,
            UUID productId,
            UpdateCartItemQuantityRequestDTO updateCartItemQuantityRequestDTO
    );

    //to response
    UpdateCartItemQuantityResponseDTO toUpdateCartItemQuantityResponseDTO(UpdateCartItemQuantityResult updateCartItemQuantityResult);


    /* __REMOVE_ITEM_FROM_CART__ */

    //to command
    RemoveItemFromCartCommand toRemoveItemFromCartCommand(UUID cartId, UUID productId);

    //to response
    RemoveItemFromCartResponseDTO toRemoveItemFromCartResponseDTO(RemoveItemFromCartResult removeItemFromCartResult);

    /* __CLEAR_CART__ */

    //to command
    ClearCartCommand toClearCartCommand(UUID cartId);

    //to response
    ClearCartResponseDTO toClearCartResponseDTO(ClearCartResult clearCartResult);

}
