package lk.clean.architecture.cart.api.application.usecase.cart;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api.application.exception.CartNotFoundException;
import lk.clean.architecture.cart.api.application.records.cart.commands.GetCartCommand;
import lk.clean.architecture.cart.api.application.records.cart.results.separation_records.CartItemSnapshotResult;
import lk.clean.architecture.cart.api.application.records.cart.results.GetCartResult;
import lk.clean.architecture.cart.api.application.records.cart.results.separation_records.MoneyResult;

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
                        new MoneyResult(item.unitPrice().amount(), item.unitPrice().currency()),
                        item.isAvailable(),
                        new MoneyResult(item.subTotal().amount(), item.subTotal().currency())
                )).toList();

        return new GetCartResult(
                cart.getCartId(),
                cart.getCurrency(),
                cartItemSnapshotResults,
                new MoneyResult(cart.calculateTotal().amount(), cart.getCurrency()),
                cart.isCartEmpty()
        );
    }
}
