package lk.clean.architecture.cart.api.application.records.results;

import java.util.UUID;

public record ClearCartResult(UUID cartId, boolean isCartEmpty) {

    public ClearCartResult {
        if (cartId == null) {
            throw new IllegalArgumentException("Cart id cannot be null");
        }
    }
}
