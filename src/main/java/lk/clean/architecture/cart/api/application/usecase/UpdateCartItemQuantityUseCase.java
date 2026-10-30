package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api.application.records.commands.UpdateCartItemQuantityCommand;
import lk.clean.architecture.cart.api.application.records.results.UpdateCartItemQuantityResult;


public interface UpdateCartItemQuantityUseCase {
    //update cart item quantity
    UpdateCartItemQuantityResult updateCartItemQuantity(UpdateCartItemQuantityCommand updateCartItemQuantityCommand);
}
