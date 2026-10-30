package lk.clean.architecture.cart.api.application.records.results;

import java.util.UUID;

public record RemoveItemFromCartResult(
        UUID cartId,
        UUID removedProductId,
        boolean isCartEmpty
) {
    public RemoveItemFromCartResult {

        if (cartId == null) {
            throw new IllegalArgumentException("cartId cannot be null");
        }

        if (removedProductId == null) {
            throw new IllegalArgumentException("removeProductId cannot be null");
        }
    }
}
