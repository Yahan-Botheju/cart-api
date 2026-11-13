package lk.clean.architecture.cart.api.application.usecase.cart;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.CartItemSnapshot;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api.application.exception.CartNotFoundException;
import lk.clean.architecture.cart.api.application.exception.ProductNotFoundException;
import lk.clean.architecture.cart.api.application.records.cart.commands.UpdateCartItemQuantityCommand;
import lk.clean.architecture.cart.api.application.records.cart.results.UpdateCartItemQuantityResult;
import lk.clean.architecture.cart.api.application.records.separation_results.MoneyResult;


public class UpdateCartItemQuantityUseCaseImpl implements UpdateCartItemQuantityUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public UpdateCartItemQuantityUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public UpdateCartItemQuantityResult updateCartItemQuantity(UpdateCartItemQuantityCommand updateCartItemQuantityCommand) {

        Cart cart = cartRepository.findCartById(updateCartItemQuantityCommand.cartId())
                .orElseThrow(() -> new CartNotFoundException("Cart not found!"));
        //replace existing quantity with new quantity through domain model
        cart.replaceExistingQuantity(updateCartItemQuantityCommand.productId(), updateCartItemQuantityCommand.quantity());

        //save cart after mutating
        Cart savedCart = cartRepository.save(cart);
        //get results by stream
        CartItemSnapshot results = savedCart.getCartItems().stream()
                .filter(item -> item.productId().equals(updateCartItemQuantityCommand.productId()))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));

        return new UpdateCartItemQuantityResult(
                savedCart.getCartId(),
                results.productId(),
                results.quantity(),
                new MoneyResult(results.subTotal().amount(), results.subTotal().currency())
        );
    }
}
