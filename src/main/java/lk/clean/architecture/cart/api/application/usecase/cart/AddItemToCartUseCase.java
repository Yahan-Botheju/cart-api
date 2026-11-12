package lk.clean.architecture.cart.api.application.usecase.cart;

import lk.clean.architecture.cart.api.application.records.cart.commands.AddItemToCartCommand;
import lk.clean.architecture.cart.api.application.records.cart.results.AddItemToCartResult;


public interface AddItemToCartUseCase {
    //add item to cart
    AddItemToCartResult addItemToCart(AddItemToCartCommand addItemToCartCommand);
}
