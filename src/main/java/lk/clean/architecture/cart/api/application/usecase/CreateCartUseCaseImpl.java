package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;

public class CreateCartUseCaseImpl implements CreateCartUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public CreateCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    //create new cart
    @Override
    public Cart cart(String currency){
        //create cart model
        Cart createNewCart = new Cart(currency);
        //save and return
        return cartRepository.save(createNewCart);
    }
}
