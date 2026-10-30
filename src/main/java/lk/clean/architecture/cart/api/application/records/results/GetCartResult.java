package lk.clean.architecture.cart.api.application.records.results;

import lk.clean.architecture.cart.api._domain.models.CartItemSnapshot;
import lk.clean.architecture.cart.api._domain.models.Money;

import java.util.List;
import java.util.UUID;

public record GetCartResult(
        UUID cartId,
        String currency,
        List<CartItemSnapshot> cartItemSnapshots,
        Money total,
        boolean isCartEmpty
) {
    public GetCartResult{
        if (cartId == null) {
            throw new IllegalArgumentException("cartId cannot be null");
        }

        if (currency == null) {
            throw new IllegalArgumentException("currency cannot be null");
        }

        if (cartItemSnapshots == null) {
            throw new IllegalArgumentException("Cart items cannot be null");
        }
        cartItemSnapshots = List.copyOf(cartItemSnapshots);

        if (total == null) {
            throw new IllegalArgumentException("Total cannot be null");
        }
    }

}
