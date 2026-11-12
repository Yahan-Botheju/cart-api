package lk.clean.architecture.cart.api.application.usecase.cart;

import lk.clean.architecture.cart.api.application.records.cart.commands.GetCartCommand;
import lk.clean.architecture.cart.api.application.records.cart.results.GetCartResult;


public interface GetCartUseCase {
    //get cart
    GetCartResult getCart(GetCartCommand getCartCommand);
}
