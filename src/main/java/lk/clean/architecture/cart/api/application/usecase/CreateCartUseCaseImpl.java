package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api.application.records.commands.CreateCartCommand;
import lk.clean.architecture.cart.api.application.records.results.CreateCartResult;

public class CreateCartUseCaseImpl implements CreateCartUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public CreateCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    //create new cart
    @Override
    public CreateCartResult createCart(CreateCartCommand createCartCommand) {
        //create cart model
        Cart createNewCart = new Cart(createCartCommand.currency());
        //save
        Cart savedCart = cartRepository.save(createNewCart);

        return new CreateCartResult(
                savedCart.getCartId(),
                savedCart.getCurrency(),
                savedCart.isCartEmpty()
        );
    }
}
