package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api.application.records.commands.RemoveItemFromCartCommand;
import lk.clean.architecture.cart.api.application.records.results.RemoveItemFromCartResult;


public class RemoveItemFromCartUseCaseImpl implements RemoveItemFromCartUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public RemoveItemFromCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public RemoveItemFromCartResult removeItemFromCart(RemoveItemFromCartCommand removeItemFromCartCommand) {

        Cart cart = cartRepository.findCartById(removeItemFromCartCommand.cartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        //remove item using domain model method
        cart.removeItem(removeItemFromCartCommand.productId());

        Cart savedCart = cartRepository.save(cart);

        return new RemoveItemFromCartResult(
                savedCart.getCartId(),
                removeItemFromCartCommand.productId(),
                savedCart.isCartEmpty()
        );
    }
}
