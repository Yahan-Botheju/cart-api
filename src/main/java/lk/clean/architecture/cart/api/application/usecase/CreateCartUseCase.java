package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api.application.records.commands.CreateCartCommand;
import lk.clean.architecture.cart.api.application.records.results.CreateCartResult;

public interface CreateCartUseCase {

    //create new cart
    CreateCartResult createCart(CreateCartCommand createCartCommand);
}
