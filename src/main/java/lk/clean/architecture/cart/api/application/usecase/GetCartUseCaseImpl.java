package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api.application.exception.CartNotFoundException;
import lk.clean.architecture.cart.api.application.records.commands.GetCartCommand;
import lk.clean.architecture.cart.api.application.records.results.CartItemSnapshotResult;
import lk.clean.architecture.cart.api.application.records.results.GetCartResult;

import java.util.List;


public class GetCartUseCaseImpl implements GetCartUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public GetCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public GetCartResult getCart(GetCartCommand getCartCommand) {
        Cart cart =  cartRepository.findCartById(getCartCommand.cartId())
                .orElseThrow(() -> new CartNotFoundException("Cart not found!"));

        //stream through new record class to prevent to web exposer
        List<CartItemSnapshotResult> cartItemSnapshotResults = cart.getCartItems().stream()
                .map(item -> new CartItemSnapshotResult(
                        item.productId(),
                        item.productName(),
                        item.productDescription(),
                        item.quantity(),
                        item.unitPrice(),
                        item.isAvailable(),
                        item.subTotal()
                )).toList();

        return new GetCartResult(
                cart.getCartId(),
                cart.getCurrency(),
                cartItemSnapshotResults,
                cart.calculateTotal(),
                cart.isCartEmpty()
        );
    }
}
