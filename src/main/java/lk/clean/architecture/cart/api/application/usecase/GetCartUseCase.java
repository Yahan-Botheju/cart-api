package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api.application.records.commands.GetCartCommand;
import lk.clean.architecture.cart.api.application.records.results.GetCartResult;


public interface GetCartUseCase {
    //get cart
    GetCartResult getCart(GetCartCommand getCartCommand);
}
