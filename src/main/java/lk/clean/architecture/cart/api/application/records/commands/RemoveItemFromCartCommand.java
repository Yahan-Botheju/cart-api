package lk.clean.architecture.cart.api.application.records.commands;

import java.util.UUID;

public record RemoveItemFromCartCommand(
        UUID cartId,
        UUID productId
) {
    public RemoveItemFromCartCommand {

        if (cartId == null) {
            throw new IllegalArgumentException("cartId cannot be null");
        }

        if (productId == null) {
            throw new IllegalArgumentException("productId cannot be null");
        }
    }
}
