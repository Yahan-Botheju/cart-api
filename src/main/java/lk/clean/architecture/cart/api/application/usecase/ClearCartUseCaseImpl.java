package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api.application.exception.CartNotFoundException;
import lk.clean.architecture.cart.api.application.records.commands.ClearCartCommand;
import lk.clean.architecture.cart.api.application.records.results.ClearCartResult;

public class ClearCartUseCaseImpl implements ClearCartUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public ClearCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public ClearCartResult clearCart(ClearCartCommand clearCartCommand) {

        Cart cart = cartRepository.findCartById(clearCartCommand.cartId())
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
        //clear cart items through domain method
        cart.clearCart();

        Cart savedCart = cartRepository.save(cart);

        return new ClearCartResult(
                savedCart.getCartId(),
                savedCart.isCartEmpty()
        );
    }
}
