package lk.clean.architecture.cart.api.application.usecase.cart;

import lk.clean.architecture.cart.api.application.records.cart.commands.RemoveItemFromCartCommand;
import lk.clean.architecture.cart.api.application.records.cart.results.RemoveItemFromCartResult;


public interface RemoveItemFromCartUseCase {
    //remove items from cart
    RemoveItemFromCartResult removeItemFromCart(RemoveItemFromCartCommand removeItemFromCartCommand);
}
