package lk.clean.architecture.cart.api.application.usecase.cart;

import lk.clean.architecture.cart.api.application.records.cart.commands.ClearCartCommand;
import lk.clean.architecture.cart.api.application.records.cart.results.ClearCartResult;

public interface ClearCartUseCase {
    //clear items in cart
    ClearCartResult clearCart(ClearCartCommand clearCartCommand);
}
