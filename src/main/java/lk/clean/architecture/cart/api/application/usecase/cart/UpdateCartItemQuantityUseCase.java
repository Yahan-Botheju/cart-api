package lk.clean.architecture.cart.api.application.usecase.cart;

import lk.clean.architecture.cart.api.application.records.cart.commands.UpdateCartItemQuantityCommand;
import lk.clean.architecture.cart.api.application.records.cart.results.UpdateCartItemQuantityResult;


public interface UpdateCartItemQuantityUseCase {
    //update cart item quantity
    UpdateCartItemQuantityResult updateCartItemQuantity(UpdateCartItemQuantityCommand updateCartItemQuantityCommand);
}
