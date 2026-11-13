package lk.clean.architecture.cart.api.application.records.cart.results;

import lk.clean.architecture.cart.api.application.records.separation_results.CartItemSnapshotResult;
import lk.clean.architecture.cart.api.application.records.separation_results.MoneyResult;

import java.util.List;
import java.util.UUID;

public record GetCartResult(
        UUID cartId,
        String currency,
        List<CartItemSnapshotResult> cartItemSnapshotResults,
        MoneyResult calculateTotal,
        boolean isCartEmpty
) {
    public GetCartResult{
        if (cartId == null) {
            throw new IllegalArgumentException("cartId cannot be null");
        }

        if (currency == null) {
            throw new IllegalArgumentException("currency cannot be null");
        }

        if (cartItemSnapshotResults == null) {
            throw new IllegalArgumentException("Cart items cannot be null");
        }
        cartItemSnapshotResults = List.copyOf(cartItemSnapshotResults);

        if (calculateTotal == null) {
            throw new IllegalArgumentException("Total cannot be null");
        }
    }

}
