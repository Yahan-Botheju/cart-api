package lk.clean.architecture.cart.api.application.records.commands;

import java.util.UUID;

public record AddItemToCartCommand(
        UUID cartId,
        UUID productId,
        int quantity
) {

    public AddItemToCartCommand{

        if (cartId == null) {
            throw new IllegalArgumentException("cartId cannot be null");
        }

        if (productId == null) {
            throw new IllegalArgumentException("productId cannot be null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity cannot be negative");
        }
    }
}
