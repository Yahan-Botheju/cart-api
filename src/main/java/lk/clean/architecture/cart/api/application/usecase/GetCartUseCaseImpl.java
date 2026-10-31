package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api.application.exception.CartNotFoundException;
import lk.clean.architecture.cart.api.application.records.commands.GetCartCommand;
import lk.clean.architecture.cart.api.application.records.results.GetCartResult;

import java.math.BigDecimal;

public class GetCartUseCaseImpl implements GetCartUseCase {

    //inject required dependencies
    private final CartRepository cartRepository;

    public GetCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public GetCartResult getCart(GetCartCommand getCartCommand) {
        Cart cart =  cartRepository.findCartById(getCartCommand.cartId())
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

        return new GetCartResult(
                cart.getCartId(),
                cart.getCurrency(),
                cart.getCartItems(),
                new Money(new BigDecimal(String.valueOf(cart.calculateTotal())), cart.getCurrency()),
                cart.isCartEmpty()
        );
    }
}
