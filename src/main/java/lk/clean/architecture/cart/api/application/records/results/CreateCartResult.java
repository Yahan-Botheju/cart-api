package lk.clean.architecture.cart.api.application.records.results;

import java.util.UUID;

public record CreateCartResult(
        UUID cartId,
        String currency,
        boolean isCartEmpty
) {
    public CreateCartResult {

        if (cartId == null) {
            throw new IllegalArgumentException("cartId cannot be null");
        }

        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("currency cannot be blank");
        }
    }
}
