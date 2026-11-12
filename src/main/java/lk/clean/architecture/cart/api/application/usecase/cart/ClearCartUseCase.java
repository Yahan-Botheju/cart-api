package lk.clean.architecture.cart.api.application.usecase.cart;

import lk.clean.architecture.cart.api.application.records.commands.ClearCartCommand;
import lk.clean.architecture.cart.api.application.records.results.ClearCartResult;

public interface ClearCartUseCase {
    //clear items in cart
    ClearCartResult clearCart(ClearCartCommand clearCartCommand);
}
