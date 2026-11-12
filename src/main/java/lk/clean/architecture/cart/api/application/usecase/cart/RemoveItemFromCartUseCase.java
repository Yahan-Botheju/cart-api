package lk.clean.architecture.cart.api.application.usecase.cart;

import lk.clean.architecture.cart.api.application.records.commands.RemoveItemFromCartCommand;
import lk.clean.architecture.cart.api.application.records.results.RemoveItemFromCartResult;


public interface RemoveItemFromCartUseCase {
    //remove items from cart
    RemoveItemFromCartResult removeItemFromCart(RemoveItemFromCartCommand removeItemFromCartCommand);
}
