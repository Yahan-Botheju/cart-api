package lk.clean.architecture.cart.api.web.webMappers;

import lk.clean.architecture.cart.api.application.records.cart.results.AddItemToCartResult;
import lk.clean.architecture.cart.api.application.records.cart.results.GetCartResult;
import lk.clean.architecture.cart.api.application.records.cart.results.UpdateCartItemQuantityResult;
import lk.clean.architecture.cart.api.application.records.separation_results.CartItemSnapshotResult;
import lk.clean.architecture.cart.api.application.records.separation_results.MoneyResult;
import lk.clean.architecture.cart.api.web.DTOs.cart.response.AddItemToCartResponseDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.response.GetCartResponseDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.response.UpdateCartItemQuantityResponseDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.response.separation_records.CartItemSnapshotDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.response.separation_records.MoneyResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoneyMapper {

    /* __MONEY_CLASSES__*/

    MoneyResponseDTO toMoney(MoneyResult moneyResult);


    /* __CART_ITEM_SNAPSHOT__*/

    CartItemSnapshotDTO toCartItemSnapshotDTO(CartItemSnapshotResult cartItemSnapshotResult);


    /* __REQUIRED_OBJECTS_MAPPING__ */

    //add-item-to-cart
    AddItemToCartResponseDTO addItemToCart_MoneyMap(AddItemToCartResult addItemToCartResult);

    //get-cart
    GetCartResponseDTO toCart_MoneyMap(GetCartResult getCartResult);

    //update-cart-item-quantity
    UpdateCartItemQuantityResponseDTO updateCartItemQuantity_MoneyMap(UpdateCartItemQuantityResult updateCartItemQuantityResult);


}
